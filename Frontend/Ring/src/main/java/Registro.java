import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Registro extends JDialog {

    private JPanel contenedorRegistro;
    private JTextField textFieldUsuario;
    private JPasswordField passwordField;
    private JPasswordField passwordFieldConfirmacion;

    public Registro(Frame owner) {
        super(owner);
        setTitle("Registro de Usuario");
        setModal(true); // El diálogo bloqueará la entrada a otras ventanas hasta que se cierre
        setUndecorated(true); // Quitar decoración para emular el JFrame principal
        setSize(1401, 863); // Mismo tamaño que el JFrame principal
        setLocationRelativeTo(owner); // Centrar respecto al JFrame propietario

        contenedorRegistro = new JPanel();
        contenedorRegistro.setLayout(null);
        contenedorRegistro.setBackground(new Color(82, 82, 82));
        setContentPane(contenedorRegistro);

        // Agrega aquí los componentes para el registro, por ejemplo:
        textFieldUsuario = new JTextField();
        textFieldUsuario.setBounds(50, 100, 300, 30);
        contenedorRegistro.add(textFieldUsuario);

        passwordField = new JPasswordField();
        passwordField.setBounds(50, 150, 300, 30);
        contenedorRegistro.add(passwordField);

        passwordFieldConfirmacion = new JPasswordField();
        passwordFieldConfirmacion.setBounds(50, 200, 300, 30);
        contenedorRegistro.add(passwordFieldConfirmacion);

        // Botón para registrar al usuario
        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(50, 250, 100, 30);
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica de registro
            }
        });
        contenedorRegistro.add(btnRegistrar);
        
        JLabel label = new JLabel("New label");
        label.setBounds(10, 11, 46, 14);
        contenedorRegistro.add(label);

        // Repite la lógica de los botones de minimizar y cerrar como en tu JFrame principal si es necesario

        // Añadir un fondo o cualquier otro componente que requieras
        
    }

    // Método para mostrar el diálogo de registro
    public static void mostrarDialogoRegistro(JFrame owner) {
        Registro dialogoRegistro = new Registro(owner);
        dialogoRegistro.setVisible(true);
    }
}
