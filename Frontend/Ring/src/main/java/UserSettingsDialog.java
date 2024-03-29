import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
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
        setUndecorated(true);
        setResizable(false);
        setSize(1174, 765);
        setLocationRelativeTo(owner);
        
        this.usuario = usuario;
        this.usuarioDAO = new UsuarioDAO(); // Inicializa tu DAO        

        getContentPane().setLayout(new GridLayout(5, 2, 5, 5)); // Layout en grid con espaciado
        
        // Inicializar campos
        nombreField = new JTextField(usuario.getNombre());
        domicilioField = new JTextField(usuario.getDomicilio());
        telefonoField = new JTextField(usuario.getTelefono());
        fechaNacField = new JTextField(Optional.ofNullable(usuario.getFechaNac())
                                            .map(Date::toString).orElse(""));

        // Agregar campos al layout
        getContentPane().add(createLabel("Nombre:"));
        getContentPane().add(nombreField);
        getContentPane().add(createLabel("Domicilio:"));
        getContentPane().add(domicilioField);
        getContentPane().add(createLabel("Teléfono:"));
        getContentPane().add(telefonoField);
        getContentPane().add(createLabel("Fecha de Nac (AAAA-MM-DD):"));
        getContentPane().add(fechaNacField);
        
        // Botón para guardar los cambios
        JButton saveButton = new JButton("Guardar Cambios");
        saveButton.addActionListener(e -> guardarCambios());
        getContentPane().add(saveButton);

        pack();
        setLocationRelativeTo(owner);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(JLabel.RIGHT);
        return label;
    }
    
    private void guardarCambios() {
        // Actualizar los datos del usuario con los valores de los campos
        usuario.setNombre(nombreField.getText());
        usuario.setDomicilio(domicilioField.getText());
        usuario.setTelefono(telefonoField.getText());

        try {
            // Formato de fecha (Asegúrate de que cumple con el formato esperado por tu base de datos)
            Date fechaNac = Date.valueOf(fechaNacField.getText());
            usuario.setFechaNac(fechaNac);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Formato de fecha incorrecto.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Llamar al DAO para actualizar
        if (usuarioDAO.actualizar(usuario, usuario.getId())) {
            JOptionPane.showMessageDialog(this, "Información actualizada con éxito.");

            // Cerrar el JDialog de configuración y la ventana principal actual
            dispose(); // Cerrar el diálogo de configuración

            // Cerrar la ventana principal y abrir una nueva con la información actualizada
            Home home = (Home)getOwner();
            home.dispose();
            
            Home newHomeFrame = new Home(usuario);
            newHomeFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar la información.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
