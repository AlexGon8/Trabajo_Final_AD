import javax.swing.*;

import com.toedter.calendar.JDateChooser;

import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.util.Calendar;

public class Registro extends JDialog {

	private JPanel contenedorRegistro;
	private JTextField textFieldUsuario;
	private JPasswordField passwordField;
	private JPasswordField passwordFieldConfirmacion;
	private JLabel lblNewLabelNombre;
	private JLabel lblNewLabelApellido;
	private JLabel lblContrasea;
	private JLabel lblConfirmacionDeContrasea;
	private JTextField textFieldApellidos;
	private JTextField textFieldApodo;
	private JTextField textFieldCorreoElectronico;
	private JButton closeButton;
    private JDateChooser dateChooser;

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
        //boton para cerrar
        closeButton = new JButton((Icon) null);
        closeButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setVisible(false);; // Esto cerrará la aplicación
        	}
        });
        closeButton.setBounds(1331, 11, 45, 37);
        contenedorRegistro.add(closeButton);
        
        JCheckBox newCheckBoxSolicitarPermisos = new JCheckBox("Solicitar permisos para subir juegos");
        newCheckBoxSolicitarPermisos.setFont(new Font("Tahoma", Font.BOLD, 14));
        newCheckBoxSolicitarPermisos.setForeground(new Color(201, 157, 24));
        newCheckBoxSolicitarPermisos.setBackground(new Color(255, 255, 255));
        newCheckBoxSolicitarPermisos.setBounds(65, 626, 300, 23);
        newCheckBoxSolicitarPermisos.setContentAreaFilled(false);
        contenedorRegistro.add(newCheckBoxSolicitarPermisos);

        // Agrega aquí los componentes para el registro, por ejemplo:
        textFieldUsuario = new JTextField();
        textFieldUsuario.setBounds(65, 78, 300, 30);
        contenedorRegistro.add(textFieldUsuario);

        passwordField = new JPasswordField();
        passwordField.setBounds(65, 472, 300, 30);
        contenedorRegistro.add(passwordField);

        passwordFieldConfirmacion = new JPasswordField();
        passwordFieldConfirmacion.setBounds(65, 565, 300, 30);
        contenedorRegistro.add(passwordFieldConfirmacion);

        // Botón para registrar al usuario
        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 17));
        btnRegistrar.setForeground(new Color(201, 157, 24));
        btnRegistrar.setBounds(65, 686, 300, 30);
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica de registro
            }
        });
        contenedorRegistro.add(btnRegistrar);
        
        lblNewLabelNombre = new JLabel("Nombre");
        lblNewLabelNombre.setForeground(new Color(201, 157, 24));
        lblNewLabelNombre.setFont(new Font("Sylfaen", Font.BOLD, 18));
        lblNewLabelNombre.setBounds(65, 49, 300, 25);
        contenedorRegistro.add(lblNewLabelNombre);
        
        lblNewLabelApellido = new JLabel("Apellidos");
        lblNewLabelApellido.setForeground(new Color(201, 157, 24));
        lblNewLabelApellido.setFont(new Font("Sylfaen", Font.BOLD, 18));
        lblNewLabelApellido.setBounds(65, 124, 300, 25);
        contenedorRegistro.add(lblNewLabelApellido);
        
        lblContrasea = new JLabel("Contraseña");
        lblContrasea.setForeground(new Color(201, 157, 24));
        lblContrasea.setFont(new Font("Sylfaen", Font.BOLD, 18));
        lblContrasea.setBounds(65, 448, 300, 25);
        contenedorRegistro.add(lblContrasea);
        
        lblConfirmacionDeContrasea = new JLabel("Confirmacion de contraseña");
        lblConfirmacionDeContrasea.setForeground(new Color(201, 157, 24));
        lblConfirmacionDeContrasea.setFont(new Font("Sylfaen", Font.BOLD, 18));
        lblConfirmacionDeContrasea.setBounds(65, 534, 300, 25);
        contenedorRegistro.add(lblConfirmacionDeContrasea);
        
        textFieldApellidos = new JTextField();
        textFieldApellidos.setBounds(65, 148, 300, 30);
        contenedorRegistro.add(textFieldApellidos);
        
        JLabel lblNewLabelApodo = new JLabel("Apodo");
        lblNewLabelApodo.setForeground(new Color(201, 157, 24));
        lblNewLabelApodo.setFont(new Font("Sylfaen", Font.BOLD, 18));
        lblNewLabelApodo.setBounds(65, 368, 300, 25);
        contenedorRegistro.add(lblNewLabelApodo);
        
        textFieldApodo = new JTextField();
        textFieldApodo.setBounds(65, 388, 300, 30);
        contenedorRegistro.add(textFieldApodo);
        
        textFieldCorreoElectronico = new JTextField();
        textFieldCorreoElectronico.setBounds(65, 308, 300, 30);
        contenedorRegistro.add(textFieldCorreoElectronico);
        
        JLabel lblCorreoElectronico = new JLabel("Correo Electronico");
        lblCorreoElectronico.setForeground(new Color(201, 157, 24));
        lblCorreoElectronico.setFont(new Font("Sylfaen", Font.BOLD, 18));
        lblCorreoElectronico.setBounds(65, 282, 300, 25);
        contenedorRegistro.add(lblCorreoElectronico);
        

        
        // Inicializa JDateChooser
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd/MM/yyyy"); // Formato de fecha
        
        // Establecer la fecha máxima permitida (hoy menos 14 años)
        Calendar maxDate = Calendar.getInstance();
        maxDate.add(Calendar.YEAR, -14);//--> esto es lo que evita que sea menor a 14 años
        dateChooser.setMaxSelectableDate(maxDate.getTime());
        
        dateChooser.setBounds(65, 213, 300, 30); // Coloca el dateChooser en la misma posición que el comboBoxEdad
        contenedorRegistro.add(dateChooser);
        
        JLabel lblNewLabelFechaNacimiento = new JLabel("Fecha de Nacimiento");
        lblNewLabelFechaNacimiento.setForeground(new Color(201, 157, 24));
        lblNewLabelFechaNacimiento.setFont(new Font("Sylfaen", Font.BOLD, 18));
        lblNewLabelFechaNacimiento.setBounds(65, 189, 300, 25);
        contenedorRegistro.add(lblNewLabelFechaNacimiento);
        

        JPanel panel = new JPanel();
        panel.setBounds(29, 31, 375, 733);
        contenedorRegistro.add(panel);
        
        JLabel BackgroundRegistro = new JLabel("New label");
        BackgroundRegistro.setIcon(new ImageIcon(Registro.class.getResource("/imagenes/ImagenRegistro.jpg")));
        BackgroundRegistro.setBounds(0, 0, 1401, 863);
        contenedorRegistro.add(BackgroundRegistro);

        // Repite la lógica de los botones de minimizar y cerrar como en tu JFrame principal si es necesario
		
		
    }

	// Método para mostrar el diálogo de registro
	public static void mostrarDialogoRegistro(JFrame owner) {
		Registro dialogoRegistro = new Registro(owner);
		dialogoRegistro.setVisible(true);
	}
	
    // Método para obtener la fecha de nacimiento del JDateChooser
    public Date getFechaNacimiento() {
        return dateChooser.getDate();
    }
}
