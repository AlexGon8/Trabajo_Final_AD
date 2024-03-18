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

public class Home extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel ContenedorGeneral;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
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
	public Home() {
		setUndecorated(true); // --> para que no aparezca el marco predeterminado
		
		setResizable(false);//-->no permitire que sea reajustable la pantalla que lanza para no preocuparme por adaptar imagenes
		setForeground(new Color(0, 255, 0));
		setBackground(new Color(51, 51, 51));

		setSize(1401, 863); //---> ESTO LE DA EL TAMAÑO DE Y RESOLUCION QUE TENDRA EL JFRAME
        setLocationRelativeTo(null);//---> ESTO HACE QUE LA IMAGEN SIEMPRE APAREZCA EN EL MEDIO DE LA PANTALLA
        
		ContenedorGeneral = new JPanel();
		ContenedorGeneral.setBackground(Color.DARK_GRAY);
		ContenedorGeneral.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(ContenedorGeneral);
		ContenedorGeneral.setLayout(null);

		// Botón de cerrar personalizado
		JButton closeButton = new JButton(new ImageIcon(Home.class.getResource("/imagenes/cerrar (1).png")));
		closeButton.setBounds(1336, 10, 45, 37); // Ajusta la posición y el tamaño según sea necesario
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0); // Esto cerrará la aplicación
			}
		});
		
		JButton minimizeButton_1 = new JButton(new ImageIcon(Home.class.getResource("/imagenes/minimizar (1).png")));
		minimizeButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setExtendedState(JFrame.ICONIFIED); // Esto minimizará la ventana
			}
		});
		
			
		
		minimizeButton_1.setBounds(1281, 10, 45, 37);
		ContenedorGeneral.add(minimizeButton_1);
		ContenedorGeneral.add(closeButton);

		ContenedorGeneral.setLayout(null);

		RoundedTextField userField = new RoundedTextField(20, "NOMBRE DE USUARIO");
		RoundedTextField passwordField = new RoundedTextField(20, "CONTRASEÑA");//--->esto aplica el placeholder 
		
		

	}
}
