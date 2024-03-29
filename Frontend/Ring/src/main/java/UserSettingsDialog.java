import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Optional;

import Clases.Usuario;
import Dao.UsuarioDAO;

public class UserSettingsDialog extends JDialog {
    private final UsuarioDAO usuarioDAO;
    private Usuario usuario;
    private JTextField nombreField;
    private JTextField domicilioField;
    private JTextField telefonoField;
    private JTextField fechaNacField;

    public UserSettingsDialog(Frame owner, Usuario usuario) {
        super(owner, "Configuración de Usuario", true);
        getContentPane().setBackground(new Color(0, 0, 0));
        this.usuario = usuario;
        this.usuarioDAO = new UsuarioDAO();

        setSize(1174, 765);
        getContentPane().setLayout(new GridLayout(0, 1));
        setLocationRelativeTo(owner);
        setUndecorated(true);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));
        panel.setBackground(new Color(0, 0, 0, 0)); // Transparente
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        nombreField = createTextField(Optional.ofNullable(usuario.getNombre()).orElse(""));
        domicilioField = createTextField(Optional.ofNullable(usuario.getDomicilio()).orElse(""));
        telefonoField = createTextField(Optional.ofNullable(usuario.getTelefono()).orElse(""));
        fechaNacField = createTextField(Optional.ofNullable(usuario.getFechaNac()).map(Date::toString).orElse(""));

        panel.add(createLabel("Nombre:"));
        panel.add(nombreField);
        panel.add(createLabel("Domicilio:"));
        panel.add(domicilioField);
        panel.add(createLabel("Teléfono:"));
        panel.add(telefonoField);
        panel.add(createLabel("Fecha de Nac (AAAA-MM-DD):"));
        panel.add(fechaNacField);
        panel.add(createLabel("Correo electrónico (no modificable):"));
        panel.add(createLabel(usuario.getCorreo()));

        JButton cancelButton = createButton("Cancelar", e -> dispose());
        panel.add(cancelButton);

        JButton saveButton = createButton("Guardar Cambios", e -> guardarCambios());
        panel.add(saveButton);

        getContentPane().add(panel);

        JLabel backgroundLabel = new JLabel(new ImageIcon(ContactDialog.class.getResource("/imagenes/gifRegistrogif.gif")));
        backgroundLabel.setSize(getSize());
        getContentPane().add(backgroundLabel);

        // Move the background to the back
        getContentPane().setComponentZOrder(backgroundLabel, getContentPane().getComponentCount() - 1);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Tahoma", Font.BOLD, 12));
        label.setForeground(new Color(255, 128, 0));
        return label;
    }

    private JTextField createTextField(String text) {
        JTextField textField = new JTextField(text);
        textField.setForeground(Color.WHITE);
        textField.setBackground(Color.DARK_GRAY);
        return textField;
    }

    private JButton createButton(String text, ActionListener action) {
        JButton button = new JButton(text);
        button.setForeground(Color.ORANGE);
        button.setBackground(Color.BLACK);
        button.setFocusPainted(false);
        button.addActionListener(action);
        return button;
    }

    private void guardarCambios() {
        // Actualizar los datos del usuario con los valores de los campos
        usuario.setNombre(nombreField.getText());
        usuario.setDomicilio(domicilioField.getText());
        usuario.setTelefono(telefonoField.getText());

        try {
            Date fechaNac = Date.valueOf(fechaNacField.getText());
            usuario.setFechaNac(fechaNac);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Formato de fecha incorrecto.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (usuarioDAO.actualizar(usuario, usuario.getId())) {
            JOptionPane.showMessageDialog(this, "Información actualizada con éxito.");
            dispose();
            Home home = (Home)getOwner();
            home.dispose(); // Cierra la ventana principal
            Home newHomeFrame = new Home(usuario); // Crea y muestra una nueva ventana con la info actualizada
            newHomeFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar la información.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
