import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Clases.Usuario;
import Dao.UsuarioDAO;

public class UserSettingsDialog extends JDialog {

    private Usuario usuario;

    public UserSettingsDialog(Frame owner, Usuario usuario) {
        super(owner, "Configuración de Usuario", true);
        this.usuario = usuario;
        getContentPane().setBackground(new Color(128, 128, 128));
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 0, 0, 0)); // Transparente
        panel.setBounds(10, 11, 1154, 743);
        getContentPane().add(panel);
        panel.setLayout(null);

        JButton closeButton = new JButton(new ImageIcon(getClass().getResource("/imagenes/cerrar (1).png")));
        closeButton.setBounds(1109, 0, 45, 37);
        closeButton.addActionListener(e -> dispose());
        panel.add(closeButton);

        // Agregar campos para editar la información del usuario
        panel.add(createLabel("Nombre de usuario:", 30, 50, 300, 30));
        JTextField userNameField = createTextField(usuario.getNombreUsuario(), 30, 80, 300, 30);
        panel.add(userNameField);

        //  contraseña no se muestra por razones de seguridad
        panel.add(createLabel("Contraseña:", 30, 130, 300, 30));
        JPasswordField passwordField = createPasswordField("", 30, 160, 300, 30);
        panel.add(passwordField);

        // el correo electrónico no se puede cambiar
        panel.add(createLabel("Correo electrónico (no modificable):", 30, 210, 300, 30));
        JLabel emailLabel = createLabel(usuario.getCorreo(), 30, 240, 300, 30);
        panel.add(emailLabel);
        
        // Domicilio
        panel.add(createLabel("Domicilio:", 30, 120, 300, 30));
        JTextField addressField = createTextField(usuario.getDomicilio(), 30, 150, 300, 30);
        panel.add(addressField);

        // Otros campos que pueden ser modificables
        // ...

        JButton saveButton = createButton("Guardar", 30, 300, 100, 30);
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para actualizar la información del usuario
                String newPassword = new String(passwordField.getPassword());
                if (!newPassword.isEmpty()) {
                    usuario.setContrasenna(newPassword);
                }
                // Actualizar otros campos del usuario según sea necesario

                // Guardar en base de datos
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                if(usuarioDAO.actualizar(usuario, usuario.getId())) {
                    JOptionPane.showMessageDialog(owner, "Datos actualizados correctamente.");
                } else {
                    JOptionPane.showMessageDialog(owner, "Error al actualizar los datos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
                dispose(); // Cerrar el diálogo después de guardar
            }
        });
        panel.add(saveButton);

        setUndecorated(true);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1174, 765);
        setLocationRelativeTo(owner);
    }

    private JLabel createLabel(String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setBounds(x, y, width, height);
        return label;
    }

    private JTextField createTextField(String text, int x, int y, int width, int height) {
        JTextField textField = new JTextField(text);
        textField.setForeground(Color.WHITE);
        textField.setBackground(Color.DARK_GRAY);
        textField.setBounds(x, y, width, height);
        return textField;
    }

    private JPasswordField createPasswordField(String text, int x, int y, int width, int height) {
        JPasswordField passwordField = new JPasswordField(text);
        passwordField.setForeground(Color.WHITE);
        passwordField.setBackground(Color.DARK_GRAY);
        passwordField.setBounds(x, y, width, height);
        return passwordField;
    }

    private JButton createButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setForeground(Color.ORANGE);
        button.setBackground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBounds(x, y, width, height);
        return button;
    }
}
