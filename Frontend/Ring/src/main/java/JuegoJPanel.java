import javax.swing.*;
import java.awt.*;

public class JuegoJPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JLabel imagenLabel;
    private JLabel nombreLabel;
    private JLabel precioLabel;

    public JuegoJPanel(String nombreJuego, String precio, ImageIcon icono) {
        setLayout(new BorderLayout());
        setBackground(Color.DARK_GRAY);

        imagenLabel = new JLabel(icono);
        add(imagenLabel, BorderLayout.CENTER);

        // Usando Box para los elementos de la parte inferior
        Box bottomBox = Box.createHorizontalBox();

        nombreLabel = new JLabel(nombreJuego);
        nombreLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        nombreLabel.setForeground(Color.WHITE);
        bottomBox.add(Box.createHorizontalStrut(50)); // Margen izquierdo
        bottomBox.add(nombreLabel);
        bottomBox.add(Box.createHorizontalGlue()); // Empuja todo lo siguiente al extremo opuesto

        precioLabel = new JLabel(precio);
        precioLabel.setFont(new Font("Verdana", Font.BOLD, 17));
        precioLabel.setForeground(Color.WHITE);
        bottomBox.add(precioLabel);
        bottomBox.add(Box.createHorizontalStrut(25)); // Margen derecho

        add(bottomBox, BorderLayout.SOUTH);
    }
}
