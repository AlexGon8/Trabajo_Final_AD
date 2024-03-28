import javax.swing.JLabel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class CartItemPanel extends JPanel {
    private JLabel nameLabel;
    private JLabel priceLabel;
    private JButton removeButton;
    private JComboBox<String> quantityComboBox;
    private JLabel imageLabel;
    

    public CartItemPanel(String name, String price, Icon image) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY)); // Borde del panel

        imageLabel = new JLabel(new ImageIcon("E:\\Documentos\\GitHub\\Trabajo_Final_AD\\Frontend\\Ring\\src\\main\\java\\imagenes\\juegos\\ready-or-not-pc-juego-steam-cover.jpg"));
        imageLabel.setBackground(new Color(128, 128, 128));
        add(imageLabel, BorderLayout.WEST); // Imagen a la izquierda

        JPanel infoPanel = new JPanel();
        infoPanel.setBackground(new Color(128, 128, 128));
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false); // Panel transparente
        add(infoPanel, BorderLayout.CENTER);

        nameLabel = new JLabel(name);
        nameLabel.setBackground(new Color(192, 192, 192));
        nameLabel.setForeground(new Color(0, 0, 0));
        infoPanel.add(nameLabel);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.setBackground(new Color(128, 128, 128));
        bottomPanel.setOpaque(false); // Panel transparente
        infoPanel.add(bottomPanel);

        priceLabel = new JLabel(price);
        priceLabel.setBackground(new Color(240, 240, 240));
        priceLabel.setForeground(new Color(0, 0, 0));
        bottomPanel.add(priceLabel);

        quantityComboBox = new JComboBox<>(new String[] {"1", "2", "3", "4", "5"});
        bottomPanel.add(quantityComboBox);

        removeButton = new JButton("Mover a la lista de deseos");
        removeButton.setForeground(new Color(255, 128, 0));
        removeButton.setBorderPainted(false);
        removeButton.setContentAreaFilled(false);
        removeButton.setFocusPainted(false);
        bottomPanel.add(removeButton);

        setMaximumSize(new Dimension(Integer.MAX_VALUE, 100)); // Altura máxima para el panel
        
        // Añade un ActionListener al JComboBox para manejar los cambios de cantidad
        quantityComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Esta es la lógica que se ejecutará cada vez que el usuario cambie la cantidad de un artículo.
                // Debe comunicar al diálogo de carrito que actualice el resumen total.
                firePropertyChange("cantidadCambiada", 0, 1); // Notificar que la cantidad ha cambiado
            }
        });
        
        
    }

    public double getPrecio() {
        String precioTexto = priceLabel.getText();
        precioTexto = precioTexto.replace("€", "").trim(); // Remover el símbolo de euro y espacios
        try {
            return Double.parseDouble(precioTexto); // Convertir el String a double
        } catch (NumberFormatException e) {
            return 0.0; // En caso de error, devuelve 0.0
        }
    }
    public int getCantidadSeleccionada() {
        return Integer.parseInt((String) quantityComboBox.getSelectedItem());
    }
}
