package Autentificador;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.UserCredentials;

import Clases.Usuario;
import Dao.UsuarioDAO;
import util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class GoogleAuth {

    private static final String CLIENT_ID = "tu-client-id";
    private static final String CLIENT_SECRET = "tu-client-secret";
    private static final String REDIRECT_URI = "tu-uri-de-redireccionamiento";

    public void authenticate(String authorizationCode) {
        try {
            // Paso 1: Intercambiar el código de autorización por un token de acceso y actualización
            GoogleTokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(
                    new NetHttpTransport(),
                    JacksonFactory.getDefaultInstance(),
                    "https://oauth2.googleapis.com/token",
                    CLIENT_ID,
                    CLIENT_SECRET,
                    authorizationCode,
                    REDIRECT_URI)
                    .execute();

            // Obtener el idToken y payload para acceder al correo
            GoogleIdToken idToken = tokenResponse.parseIdToken();
            GoogleIdToken.Payload payload = idToken.getPayload();
            
            // Paso 2: Crear y/o actualizar usuario en la base de datos
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();

                // Obtener el correo del payload del token
                String email = payload.getEmail();

                // Buscar usuario por correo electrónico
                Usuario usuario = UsuarioDAO.buscarPorCorreo(email);

                if (usuario == null) {
                    // Si el usuario no existe, crear uno nuevo con la información disponible de Google
                    usuario = new Usuario();
                    usuario.setNombreUsuario(email); // Usar el correo como nombre de usuario si no tienes otro
                    usuario.setCorreo(email);
                    usuario.setNombre(payload.get("given_name").toString()); // Estos campos dependen del scope de tu petición OAuth
                    usuario.setApellidos(payload.get("family_name").toString());
                    // Establecer valores predeterminados o nulos para los campos restantes
                    usuario.setContrasenna(null); // Podrías generar una contraseña aleatoria o dejarla para que el usuario la establezca más adelánte
                    usuario.setDomicilio(null);
                    usuario.setTelefono(null);
                    usuario.setFechaNac(null); // Puede ser un campo opcional hasta que el usuario decida proporcionarlo
                    // No establecer juegos ya que es una colección que inicialmente estará vacía

                    // Guardar el nuevo usuario en la base de datos
                    session.save(usuario);
                } else {
                    // Si el usuario existe, puedes actualizar su última sesión u otros datos aquí
                    // usuario.setUltimaSesion(new Date()); // Por ejemplo
                    session.update(usuario);
                }

                transaction.commit();
            }
            // Paso 3: Aquí podrías iniciar una sesión de usuario, devolver el token de acceso al cliente, etc.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


