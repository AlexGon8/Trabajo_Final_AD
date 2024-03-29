import javax.swing.*;

import Clases.Juego;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JuegoJPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JLabel imagenLabel;
    private JLabel nombreLabel;
    private JLabel precioLabel;
    private JButton btnComprar;
    private Component horizontalStrut;
    private Juego juego;
    
    public JuegoJPanel(Juego juego, ImageIcon icono) {
        this.juego = juego; // Guarda la referencia al objeto Juego
        setLayout(new BorderLayout());
        setBackground(Color.DARK_GRAY);

        imagenLabel = new JLabel(icono);
        add(imagenLabel, BorderLayout.CENTER);

        // Usando Box para los elementos de la parte inferior
        Box bottomBox = Box.createHorizontalBox();

        nombreLabel = new JLabel(juego.getNombre());
        nombreLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        nombreLabel.setForeground(Color.WHITE);
        bottomBox.add(Box.createHorizontalStrut(50)); // Margen izquierdo
        bottomBox.add(nombreLabel);
        bottomBox.add(Box.createHorizontalGlue()); // Empuja todo lo siguiente al extremo opuesto

        precioLabel = new JLabel(String.format("%.2f€", juego.getPrecio()));
        precioLabel.setFont(new Font("Verdana", Font.BOLD, 17));
        precioLabel.setForeground(Color.WHITE);
        bottomBox.add(precioLabel);
        bottomBox.add(Box.createHorizontalStrut(25)); // Margen derecho

        add(bottomBox, BorderLayout.SOUTH);
        // Botón Comprar
        btnComprar = new JButton("Comprar");
        btnComprar.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnComprar.setBackground(new Color(255, 128, 0));
        btnComprar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firePropertyChange("juegoComprado", false, true);
            }
        });
        bottomBox.add(btnComprar);
        
        horizontalStrut = Box.createHorizontalStrut(25);
        bottomBox.add(horizontalStrut);
    }
    // -------> importante para obtener el juego desde el carrito
    public Juego getJuego() {
        return this.juego;
    }
	public JLabel getImagenLabel() {
		return imagenLabel;
	}
	public void setImagenLabel(JLabel imagenLabel) {
		this.imagenLabel = imagenLabel;
	}
}
