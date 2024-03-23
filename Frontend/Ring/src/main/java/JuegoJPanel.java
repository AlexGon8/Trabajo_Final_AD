import javax.swing.JPanel;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

public class JuegoJPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	   private JLabel imagenLabel;
	    private JLabel nombreLabel;
	    private JLabel precioLabel;
	    


		    public JuegoJPanel(String nombreJuego, String precio, ImageIcon icono) {
		        setLayout(new BorderLayout());
		        setBackground(Color.DARK_GRAY); // Fondo oscuro, o cualquier otro color que prefieras

		        imagenLabel = new JLabel();
		        imagenLabel.setIcon(icono); // Asumiendo que 'icono' es un ImageIcon con la imagen del juego
		        add(imagenLabel, BorderLayout.CENTER);

		        nombreLabel = new JLabel(nombreJuego);
		        nombreLabel.setFont(new Font("Verdana", Font.BOLD, 18));
		        nombreLabel.setForeground(Color.WHITE); // Texto blanco
		        add(nombreLabel, BorderLayout.NORTH);

		        precioLabel = new JLabel(precio);
		        precioLabel.setFont(new Font("Verdana", Font.BOLD, 17));
		        precioLabel.setForeground(Color.WHITE); // Texto blanco
		        add(precioLabel, BorderLayout.SOUTH);
		    }
	}


