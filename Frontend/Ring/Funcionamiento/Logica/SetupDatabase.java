package Logica;

import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import com.github.javafaker.Faker;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;

import Clases.Compannia;
import Clases.Consola;
import Clases.Juego;
import Clases.Usuario;
import Dao.CompaniaDAO;
import Dao.ConsolaDAO;
import Dao.JuegoDAO;
import Dao.UsuarioDAO;

public class SetupDatabase {

    public static void main(String[] args) {
        //insertarJuegosPredeterminados();
        //insertarUsuariosFicticios();
        //insertarCompaniasFicticias();
    	insertarConsolas();
    }

    private static void insertarJuegosPredeterminados() {
        Faker faker = new Faker(new Locale("es"));
        String basePath = "/C:/Users/Usuario/Documents/GitHub/Trabajo_Final_AD/Frontend/Ring/src/main/java/imagenes/juegos/";


        String[] nombresArchivos = {
        	    "age-of-empires-iv-the-sultans-ascend-pc-juego-steam-cover.jpg",
        	    "balator-pc-juego-steam-cover.jpg",
        	    "banishers-ghosts-of-new-eden-pc-juego-steam-cover.jpg",
        	    "destiny-2-la-forma-final-pase-anual-dlc-season-pc-juego-steam-cover.jpg",
        	    "destroyer-the-u-boat-hunter-pc-juego-steam-cover.jpg",
        	    "dragon-s-dogma-2-pc-juego-steam-cover.jpg",
        	    "elden-ring-shadow-of-the-erdtree-edition-shadow-of-the-erdtree-edition-pc-juego-steam-cover.jpg",
        	    "helldivers-2-pc-juego-steam-cover.jpg",
        	    "horizon-forbidden-west-complete-edition-complete-edition-pc-juego-steam-cover.jpg",
        	    "jurassic-world-evolution-2-secret-species-pack-pc-juego-steam-cover.jpg",
        	    "lords-of-the-fallen-pc-juego-steam-cover.jpg",
        	    "outcast-a-new-beginning-pc-juego-steam-cover.jpg",
        	    "ready-or-not-pc-juego-steam-cover.jpg",
        	    "star-wars-battlefront-classic-collection-pc-juego-steam-cover.jpg",
        	    "tekken-8-pc-juego-steam-cover.jpg",
        	    "terratech-worlds-pc-juego-steam-cover.jpg",
        	    "the-outlast-trials-pc-juego-steam-cover.jpg",
        	    "tomb-raider-ii-remastered-starring-lara-croft-pc-juego-steam-cover.jpg",
        	    "total-war-warhammer-trilogy-pc-mac-juego-steam-cover.jpg",
        	    "warhammer-40-000-rogue-trader-pc-juego-steam-cover.jpg"
        	};

        

        for (String nombreArchivo : nombresArchivos) {
            Session session = null;
            Transaction tx = null;
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                tx = session.beginTransaction();
                
                Juego juego = new Juego();
                juego.setNombre(faker.gameOfThrones().character());
                juego.setPrecio(faker.number().randomDouble(2, 10, 60));
                java.util.Date fecha = faker.date().birthday();
                juego.setAnnoDeSalida(new java.sql.Date(fecha.getTime()));
                juego.setGenero(faker.book().genre());
                juego.setStock(faker.number().numberBetween(1, 100));
                juego.setDescuento(faker.number().randomDouble(2, 0, 25));

                String rutaCompleta = basePath + nombreArchivo;
                File imgFile = new File(rutaCompleta);
                byte[] imageData = Files.readAllBytes(imgFile.toPath());
                juego.setImagen(imageData);

                session.persist(juego);
                tx.commit();
            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }
                System.err.println("Error al insertar juego: " + nombreArchivo + " - " + e.getMessage());
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
    }
    
    private static void insertarUsuariosFicticios() {
        Faker faker = new Faker(new Locale("es"));
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        for (int i = 0; i < 20; i++) {
            Usuario usuario = new Usuario();
            usuario.setNombreUsuario(faker.name().username());
            usuario.setContrasenna(faker.internet().password());
            usuario.setNombre(faker.name().firstName());
            usuario.setApellidos(faker.name().lastName());
            usuario.setDomicilio(faker.address().streetAddress());
            usuario.setTelefono(faker.phoneNumber().phoneNumber());
            usuario.setCorreo(faker.internet().emailAddress());
            // Faker por defecto devuelve java.util.Date, así que lo convertimos a java.sql.Date
            usuario.setFechaNac(new Date(faker.date().birthday().getTime()));
            usuario.setJuegos(new HashSet<>()); // Suponiendo que inicialmente no tiene juegos
            
            boolean exito = usuarioDAO.crear(usuario);
            
            if (exito) {
                System.out.println("Usuario ficticio creado: " + usuario.getNombreUsuario());
            } else {
                System.out.println("No se pudo crear el usuario ficticio.");
            }
        }
    }
    
    private static void insertarCompaniasFicticias() {
        Faker faker = new Faker(new Locale("es"));
        CompaniaDAO companiaDAO = new CompaniaDAO();

        for (int i = 0; i < 20; i++) {
            Compannia compannia = new Compannia();
            compannia.setNombre(faker.company().name());
            java.util.Date fecha2 = faker.date().birthday();
            compannia.setAnnoDeFundacion(new java.sql.Date(fecha2.getTime()));

            boolean exito = companiaDAO.crear(compannia);
            if (exito) {
                System.out.println("Compañía ficticia creada: " + compannia.getNombre());
            } else {
                System.out.println("No se pudo crear la compañía ficticia.");
            }
        }
    }
    private static void insertarConsolas() {
		Faker faker = new Faker(new Locale("es"));
		ConsolaDAO consolaDAO = new ConsolaDAO();
		JuegoDAO juegoDAO = new JuegoDAO();
		ArrayList<Juego> listaJuegos = (ArrayList<Juego>) juegoDAO.listar();

		Consola consola1 = new Consola();
		consola1.setNombre("Nintendo switch");
		consola1.setAnnoSalida(new Date(faker.date().birthday().getTime()));
		HashSet<Juego> juegosN = new HashSet<Juego>();
		juegosN.add(listaJuegos.get(0));
		juegosN.add(listaJuegos.get(4));
		juegosN.add(listaJuegos.get(11));
		juegosN.add(listaJuegos.get(9));
		consola1.setJuegos(juegosN);

		Consola consola2 = new Consola();
		consola2.setNombre("PlayStation 5");
		consola2.setAnnoSalida(new Date(faker.date().birthday().getTime()));
		HashSet<Juego> juegosPS5 = new HashSet<Juego>();
		juegosPS5.add(listaJuegos.get(0));
		juegosPS5.add(listaJuegos.get(1));
		juegosPS5.add(listaJuegos.get(2));
		juegosPS5.add(listaJuegos.get(3));
		juegosPS5.add(listaJuegos.get(5));
		juegosPS5.add(listaJuegos.get(4));
		juegosPS5.add(listaJuegos.get(7));
		juegosPS5.add(listaJuegos.get(11));
		consola2.setJuegos(juegosPS5);

		Consola consola3 = new Consola();
		consola3.setNombre("PC");
		consola3.setAnnoSalida(new Date(faker.date().birthday().getTime()));
		HashSet<Juego> juegosPC = new HashSet<Juego>();
		juegosPC.add(listaJuegos.get(0));
		juegosPC.add(listaJuegos.get(12));
		juegosPC.add(listaJuegos.get(2));
		juegosPC.add(listaJuegos.get(10));
		juegosPC.add(listaJuegos.get(5));
		juegosPC.add(listaJuegos.get(4));
		juegosPC.add(listaJuegos.get(8));
		juegosPC.add(listaJuegos.get(6));
		juegosPC.add(listaJuegos.get(11));
		juegosPC.add(listaJuegos.get(13));
		consola3.setJuegos(juegosPC);

		boolean exito = consolaDAO.crear(consola1);
		boolean exito1 = consolaDAO.crear(consola2);
		boolean exito2 = consolaDAO.crear(consola3);

		if (exito1) {
			System.out.println("Consola creada: " + consola2.getNombre());
		}
		if (exito2) {
			System.out.println("Consola creada: " + consola3.getNombre());
		}
		if (exito) {
			System.out.println("Consola creada: " + consola1.getNombre());
		} else {
			System.out.println("No se pudo crear el usuario ficticio.");
		}

	}
}
