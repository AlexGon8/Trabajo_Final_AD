import javax.swing.*;
import java.awt.*;

public class FilterPanel extends JPanel {
	private Image backgroundImage;

	public FilterPanel(String imagePath) {

		backgroundImage = new ImageIcon(getClass().getResource(imagePath)).getImage();

		setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Alinear elementos a la izquierda

		// Filtro para plataformas
		JComboBox<String> platformsComboBox = new JComboBox<>(
				new String[] { "Plataformas", "Steam", "Epic Games", "Origin" });
		configureComboBox(platformsComboBox);
		add(platformsComboBox);

		// Filtro para géneros
		JComboBox<String> genresComboBox = new JComboBox<>(new String[] { "Géneros...", "Acción", "Aventura", "RPG" });
		configureComboBox(genresComboBox);
		add(genresComboBox);

		// Etiqueta y campo de texto para el precio mínimo
		add(createLabel("Entre"));
		JTextField fromPriceField = new JTextField(5);
		configureTextField(fromPriceField);
		add(fromPriceField);

		// Etiqueta y campo de texto para el precio máximo
		add(createLabel("y"));
		JTextField toPriceField = new JTextField(5);
		configureTextField(toPriceField);
		add(toPriceField);

		// Filtro para juegos y DLC
		JComboBox<String> gamesDlcComboBox = new JComboBox<>(new String[] { "Juegos y DLC", "Juegos", "DLC" });
		configureComboBox(gamesDlcComboBox);
		add(gamesDlcComboBox);

		// Filtro para ordenar los resultados
		JComboBox<String> orderByComboBox = new JComboBox<>(
				new String[] { "Ordenar por:", "Precio", "Nombre", "Popularidad" });
		configureComboBox(orderByComboBox);
		add(orderByComboBox);
	}
    // Sobrescribe el método paintComponent para dibujar la imagen de fondo
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE); // Color del texto
        label.setFont(label.getFont().deriveFont(Font.BOLD)); // Hacer el texto en negrita
        return label;
    }
    

	// Método para configurar los JComboBox con un estilo común
	private void configureComboBox(JComboBox<String> comboBox) {
		comboBox.setPreferredSize(new Dimension(140, 25));
		comboBox.setBackground(Color.DARK_GRAY);
		comboBox.setForeground(Color.WHITE);
	}

	// Método para configurar los JTextField con un estilo común
	private void configureTextField(JTextField textField) {
		textField.setPreferredSize(new Dimension(60, 25));
		textField.setBackground(Color.DARK_GRAY);
		textField.setForeground(Color.WHITE);
		textField.setCaretColor(Color.WHITE); // Color del cursor de texto
		textField.setBorder(BorderFactory.createLineBorder(Color.WHITE)); // Borde del campo de texto
	}

	// ... Puedes agregar métodos adicionales si son necesarios, como getters para
	// los filtros ...
}
