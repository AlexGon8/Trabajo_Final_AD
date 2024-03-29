import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuPanel extends JPanel {
    
    private JButton pcButton;
    private JButton playStationButton;
    private JButton xboxButton;
    private RoundedTextField searchField; // Asumiendo que RoundedTextField es una clase que ya tienes definida
    private JToggleButton searchToggle;
    private JPanel searchPanel; // Panel para contener la barra de búsqueda y hacerla expandible
    private CardLayout cardLayout; 

    private static final long serialVersionUID = 1L;

    public MenuPanel() {
    	setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5)); // Ajustar la separación entre componentes
        setBackground(new Color(51, 51, 51));
        
        pcButton = createPlatformButton("PC", "/imagenes/menu/monitor.png");
        playStationButton = createPlatformButton("PlayStation", "/imagenes/menu/social.png");
        xboxButton = createPlatformButton("Xbox", "/imagenes/menu/xbox.png");

        searchToggle = new JToggleButton(new ImageIcon(getClass().getResource("/imagenes/menu/lupa.png")));
        styleButton(searchToggle);

        searchPanel = new JPanel();
        cardLayout = new CardLayout();
        searchPanel.setLayout(cardLayout);
        searchPanel.setOpaque(false); // Así el panel no dibuja su fondo, manteniendo la estética del MenuPanel

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setOpaque(false);
        buttonPanel.add(searchToggle, BorderLayout.CENTER);

        searchField = new RoundedTextField(20,"");
        searchField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        searchPanel.add(buttonPanel, "Button");
        searchPanel.add(searchField, "TextField");

        // Acción para mostrar/ocultar el campo de búsqueda
        searchToggle.addActionListener(e -> {
            if (searchToggle.isSelected()) {
                cardLayout.show(searchPanel, "TextField");
                searchField.requestFocusInWindow();
            } else {
                cardLayout.show(searchPanel, "Button");
            }
        });

        add(pcButton);
        add(playStationButton);
        add(xboxButton);
        add(searchPanel);
    }

    

    // Método auxiliar para crear botones de plataforma
    private JButton createPlatformButton(String name, String iconPath) {
        JButton button = new JButton(name);
        button.setIcon(new ImageIcon(getClass().getResource(iconPath)));
        styleButton(button);
        return button;
    }

    // Método para aplicar un estilo común a los botones
    private void styleButton(AbstractButton button) {
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setForeground(Color.WHITE); // Letra blanca
    }

    // Métodos para obtener los componentes del menú si es necesario
    public JButton getPcButton() {
        return pcButton;
    }

    public JButton getPlayStationButton() {
        return playStationButton;
    }

    public RoundedTextField getSearchField() {
        return searchField;
    }
    
    // Getter para el botón de búsqueda
    public JToggleButton getSearchToggle() {
        return searchToggle;
    }

	public void setSearchField(RoundedTextField searchField) {
		this.searchField = searchField;
	}
    
}
