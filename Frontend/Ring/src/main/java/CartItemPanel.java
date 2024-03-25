import javax.swing.JLabel;

import java.awt.*;

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

        imageLabel = new JLabel(image);
        add(imageLabel, BorderLayout.WEST); // Imagen a la izquierda

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false); // Panel transparente
        add(infoPanel, BorderLayout.CENTER);

        nameLabel = new JLabel(name);
        nameLabel.setForeground(Color.WHITE);
        infoPanel.add(nameLabel);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.setOpaque(false); // Panel transparente
        infoPanel.add(bottomPanel);

        priceLabel = new JLabel(price);
        priceLabel.setForeground(Color.GRAY);
        bottomPanel.add(priceLabel);

        quantityComboBox = new JComboBox<>(new String[] {"1", "2", "3", "4", "5"});
        bottomPanel.add(quantityComboBox);

        removeButton = new JButton("Mover a la lista de deseos");
        removeButton.setForeground(Color.WHITE);
        removeButton.setBorderPainted(false);
        removeButton.setContentAreaFilled(false);
        removeButton.setFocusPainted(false);
        bottomPanel.add(removeButton);

        setMaximumSize(new Dimension(Integer.MAX_VALUE, 100)); // Altura máxima para el panel
    }
}
