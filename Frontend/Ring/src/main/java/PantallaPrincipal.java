import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import componentes.RoundedTextField;

import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.CardLayout;
import java.awt.Panel;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import java.awt.TextField;
import java.awt.Label;
import java.awt.Font;
import java.awt.Toolkit;

public class PantallaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel ContenedorGeneral;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaPrincipal frame = new PantallaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PantallaPrincipal() {
		setUndecorated(true); // --> para que no aparezca el marco predeterminado
		
		setResizable(false);//-->no permitire que sea reajustable la pantalla que lanza para no preocuparme por adaptar imagenes
		setForeground(new Color(0, 255, 0));
		setBackground(new Color(51, 51, 51));

		setSize(1401, 863); //---> ESTO LE DA EL TAMAÑO DE Y RESOLUCION QUE TENDRA EL JFRAME
        setLocationRelativeTo(null);//---> ESTO HACE QUE LA IMAGEN SIEMPRE APAREZCA EN EL MEDIO DE LA PANTALLA
        
		ContenedorGeneral = new JPanel();
		ContenedorGeneral.setBackground(new Color(82, 82, 82));
		ContenedorGeneral.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(ContenedorGeneral);
		ContenedorGeneral.setLayout(null);

		// Botón de cerrar personalizado
		JButton closeButton = new JButton(new ImageIcon("path_to_close_icon.png"));
		closeButton.setBounds(1331, 10, 43, 20); // Ajusta la posición y el tamaño según sea necesario
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0); // Esto cerrará la aplicación
			}
		});

		// Botón de minimizar personalizado
		JButton minimizeButton = new JButton(new ImageIcon("path_to_minimize_icon.png"));
		minimizeButton.setBounds(ContenedorGeneral.getWidth() - 60, 10, 20, 20); // Ajusta la posición y el tamaño según
																					// sea necesario
		minimizeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setExtendedState(JFrame.ICONIFIED); // Esto minimizará la ventana
			}
		});
		ContenedorGeneral.add(minimizeButton);
		ContenedorGeneral.add(closeButton);

		Panel panel = new Panel();
		panel.setBackground(new Color(238, 238, 238));
		panel.setBounds(52, 129, 312, 401);
		ContenedorGeneral.add(panel);
		panel.setLayout(null);

		ContenedorGeneral.setLayout(null);

		// Campo de texto para la contraseña
		JPasswordField textFieldContraseña = new JPasswordField("Contraseña");
		textFieldContraseña.setBackground(new Color(192, 192, 192));
		textFieldContraseña.setForeground(Color.GRAY);
		textFieldContraseña.setBounds(24, 161, 256, 42);
		textFieldContraseña.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
		textFieldContraseña.setEchoChar((char) 0); // Esto muestra el placeholder como texto normal
		textFieldContraseña.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				String pass = new String(textFieldContraseña.getPassword());
				if (pass.equals("Contraseña")) {
					textFieldContraseña.setText("");
					textFieldContraseña.setForeground(Color.BLACK);
					textFieldContraseña.setEchoChar('•'); // Esto establece el carácter de eco para la contraseña
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				String pass = new String(textFieldContraseña.getPassword());
				if (pass.isEmpty()) {
					textFieldContraseña.setForeground(Color.GRAY);
					textFieldContraseña.setText("Contraseña");
					textFieldContraseña.setEchoChar((char) 0); // Esto vuelve a mostrar el placeholder como texto normal
				}
			}
		});
		panel.add(textFieldContraseña);

		JTextField textFieldUsuario = new JTextField("Usuario");
		textFieldUsuario.setBackground(new Color(192, 192, 192));
		textFieldUsuario.setForeground(Color.GRAY);
		textFieldUsuario.setBounds(24, 95, 256, 42);
		textFieldUsuario.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
		textFieldUsuario.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (textFieldUsuario.getText().equals("Usuario")) {
					textFieldUsuario.setText("");
					textFieldUsuario.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (textFieldUsuario.getText().isEmpty()) {
					textFieldUsuario.setForeground(Color.GRAY);
					textFieldUsuario.setText("Usuario");
				}
			}
		});
		panel.add(textFieldUsuario);

		JLabel lblNewLabel = new JLabel("Iniciar Sesión");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setBackground(new Color(192, 192, 192));
		lblNewLabel.setBounds(74, 31, 157, 27);
		panel.add(lblNewLabel);

		JLabel lblNewLabelOlvidecontrasena = new JLabel("Olvide mi contraseña");
		lblNewLabelOlvidecontrasena.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabelOlvidecontrasena.setBounds(24, 320, 157, 14);
		panel.add(lblNewLabelOlvidecontrasena);

		JLabel lblNewLabelCrearUsuario = new JLabel("Crear Usuario");
		lblNewLabelCrearUsuario.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabelCrearUsuario.setBounds(24, 359, 111, 14);
		panel.add(lblNewLabelCrearUsuario);

		//este jlabel fue creado solo para funcion de meter la imagen de fondo
		JLabel ImagenLogin1 = new JLabel("");
		ImagenLogin1.setIcon(new ImageIcon(
				"E:\\Documentos\\GitHub\\Trabajo_Final_AD\\Frontend\\Ring\\src\\main\\java\\imagenes\\elden_ring_shadow_erdtree_details.jpg"));
		ImagenLogin1.setBounds(0, 0, getWidth(), getHeight());
		ContenedorGeneral.add(ImagenLogin1);

		RoundedTextField userField = new RoundedTextField(20, "NOMBRE DE USUARIO");
		RoundedTextField passwordField = new RoundedTextField(20, "CONTRASEÑA");

	}
}
