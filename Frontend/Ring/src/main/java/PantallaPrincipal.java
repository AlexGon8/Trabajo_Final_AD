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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

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
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		JButton closeButton = new JButton(new ImageIcon(PantallaPrincipal.class.getResource("/imagenes/cerrar (1).png")));
		closeButton.setBounds(1336, 10, 45, 37); // Ajusta la posición y el tamaño según sea necesario
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0); // Esto cerrará la aplicación
			}
		});
		
		JButton minimizeButton_1 = new JButton(new ImageIcon(PantallaPrincipal.class.getResource("/imagenes/minimizar (1).png")));
		minimizeButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setExtendedState(JFrame.ICONIFIED); // Esto minimizará la ventana
			}
		});
		
			
		
		minimizeButton_1.setBounds(1281, 10, 45, 37);
		ContenedorGeneral.add(minimizeButton_1);
		ContenedorGeneral.add(closeButton);

		Panel panel = new Panel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(52, 129, 312, 401);
		ContenedorGeneral.add(panel);
		panel.setLayout(null);

		ContenedorGeneral.setLayout(null);

		// Campo de texto para la contraseña--> Estos son los parametros para darle un aspecto moderno a las cajas 
		JPasswordField textFieldContraseña = new JPasswordField("Contraseña");
		textFieldContraseña.setBackground(new Color(225, 225, 225));
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
		textFieldUsuario.setBackground(new Color(230, 230, 230));
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
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setBackground(new Color(0, 0, 0));
		lblNewLabel.setBounds(74, 31, 157, 27);
		panel.add(lblNewLabel);

		JLabel lblNewLabelOlvidecontrasena = new JLabel("Olvide mi contraseña");
		lblNewLabelOlvidecontrasena.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
		lblNewLabelOlvidecontrasena.setBounds(24, 331, 157, 14);
		panel.add(lblNewLabelOlvidecontrasena);
		
		// jlabel que al precionar debe abrir el dilogo de registro de usuarios 
		JLabel lblNewLabelCrearUsuario = new JLabel("Crear Usuario");
		lblNewLabelCrearUsuario.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {//--> se creal el evento para que funcione como si fuese un boton
		        // Aquí invocas al diálogo de registro
		        EventQueue.invokeLater(new Runnable() {
		            public void run() {
		                try {
		                    // llama al  JDialog q se llama Registro y
		                    Registro dialogoRegistro = new Registro(PantallaPrincipal.this);
		                    dialogoRegistro.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		                    dialogoRegistro.setVisible(true);//--> lo muestra
		                } catch (Exception ex) {
		                    ex.printStackTrace();
		                }
		            }
		        });
		    }
		});
		
		
		lblNewLabelCrearUsuario.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 12));
		lblNewLabelCrearUsuario.setBounds(24, 356, 111, 14);
		panel.add(lblNewLabelCrearUsuario);
		
		//El icono para acceder a la app
		JLabel lblNewLabelIconoLogin = new JLabel("");
		lblNewLabelIconoLogin.setBackground(new Color(255, 255, 255));
		lblNewLabelIconoLogin.setIcon(new ImageIcon(PantallaPrincipal.class.getResource("/imagenes/iconEntrarsinfondo.jpg")));
		lblNewLabelIconoLogin.setBounds(90, 214, 120, 106);
		panel.add(lblNewLabelIconoLogin);
		
		JLabel JLabelEntrar = new JLabel("Entrar");
		JLabelEntrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		JLabelEntrar.setBounds(54, 261, 46, 14);
		panel.add(JLabelEntrar);

		lblNewLabelIconoLogin.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseEntered(MouseEvent e) {
		        // Aumenta el tamaño del ícono al pasar el ratón por encima
		        Icon icon = lblNewLabelIconoLogin.getIcon();
		        if (icon instanceof ImageIcon) {
		            ImageIcon imgIcon = (ImageIcon) icon;
		            Image img = imgIcon.getImage();
		            Image newimg = img.getScaledInstance(
		                (int)(imgIcon.getIconWidth() * 1.05), // Aumenta el ancho en un 5%
		                (int)(imgIcon.getIconHeight() * 1.05), // Aumenta el alto en un 5%
		                java.awt.Image.SCALE_SMOOTH);
		            lblNewLabelIconoLogin.setIcon(new ImageIcon(newimg));
		            // Centra el ícono en su posición original
		            lblNewLabelIconoLogin.setBounds(
		                lblNewLabelIconoLogin.getX() - (int)(imgIcon.getIconWidth() * 0.025),
		                lblNewLabelIconoLogin.getY() - (int)(imgIcon.getIconHeight() * 0.025),
		                (int)(imgIcon.getIconWidth() * 1.05),
		                (int)(imgIcon.getIconHeight() * 1.05)
		            );
		        }
		    }

		    @Override
		    public void mouseExited(MouseEvent e) {
		        // Restaura el tamaño original del ícono cuando el ratón sale
		        lblNewLabelIconoLogin.setIcon(new ImageIcon(PantallaPrincipal.class.getResource("/imagenes/iconEntrarsinfondo.jpg")));
		        lblNewLabelIconoLogin.setBounds(90, 214, 120, 106);
		    }
		});

		//este jlabel fue creado solo para funcion de meter la imagen de fondo
		JLabel ImagenLogin1 = new JLabel("");
		ImagenLogin1.setIcon(new ImageIcon(PantallaPrincipal.class.getResource("imagenes\\elden_ring_shadow_erdtree_details.jpg")));
		ImagenLogin1.setBounds(0, 0, getWidth(), getHeight());
		ContenedorGeneral.add(ImagenLogin1);

		RoundedTextField userField = new RoundedTextField(20, "NOMBRE DE USUARIO");
		RoundedTextField passwordField = new RoundedTextField(20, "CONTRASEÑA");//--->esto aplica el placeholder 
		
		

	}
}
