import javax.swing.*;
import java.awt.*;

public class Home extends JFrame {

    public Home() {
        setTitle("Tienda de Juegos");
        setSize(1024, 768); // Ajusta al tamaño que necesites
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Menu Superior
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createMenuButton("PC"));
        menuBar.add(createMenuButton("Playstation"));
        menuBar.add(createMenuButton("Xbox"));
        menuBar.add(createMenuButton("Nintendo"));
        JTextField searchField = new JTextField("Buscar...");
        menuBar.add(searchField);

        // Agrega el menuBar al JFrame
        add(menuBar, BorderLayout.NORTH);

        // Panel principal que contiene todo el contenido debajo del menú
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panel superior para el banner
        JLabel bannerLabel = new JLabel(new ImageIcon("E:\\Documentos\\GitHub\\Trabajo_Final_AD\\Frontend\\Ring\\src\\main\\java\\imagenes\\elden_ring_shadow_erdtree_details.jpg")); // Reemplaza con el path de tu imagen
        mainPanel.add(bannerLabel, BorderLayout.NORTH);

        // Panel de juegos en tendencias
        JPanel trendingPanel = new JPanel(new GridLayout(0, 3, 10, 10)); // ajusta la cantidad de filas/columnas según sea necesario

        // Agrega los juegos en tendencia
        trendingPanel.add(createGamePanel("Helldivers 2", "29.79€", "E:\\Documentos\\GitHub\\Trabajo_Final_AD\\Frontend\\Ring\\src\\main\\java\\imagenes\\elden_ring_shadow_erdtree_details.jpg"));
        trendingPanel.add(createGamePanel("Balatro", "9.99€", "path/to/balatro/image.jpg"));
        trendingPanel.add(createGamePanel("The Outlast Trials", "22.11€", "imagenes\\elden_ring_shadow_erdtree_details.jpg"));
        // ... y así sucesivamente para cada juego

        // Agrega el panel de juegos en tendencia al panel principal
        mainPanel.add(trendingPanel, BorderLayout.CENTER);

        // Agrega el mainPanel al JFrame
        add(mainPanel, BorderLayout.CENTER);
    }

    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        // Añadir estilo y funcionalidades al botón
        return button;
    }

    private JPanel createGamePanel(String gameName, String price, String imagePath) {
        JPanel gamePanel = new JPanel(new BorderLayout());
        JLabel gameLabel = new JLabel(new ImageIcon(imagePath)); // Reemplaza con el path de tu imagen
        gamePanel.add(gameLabel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        JLabel priceLabel = new JLabel(price, SwingConstants.LEFT);
        JButton buyButton = new JButton("Comprar");
        bottomPanel.add(priceLabel, BorderLayout.WEST);
        bottomPanel.add(buyButton, BorderLayout.EAST);

        gamePanel.add(bottomPanel, BorderLayout.SOUTH);

        return gamePanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Home ui = new Home();
            ui.setVisible(true);
        });
    }
}
