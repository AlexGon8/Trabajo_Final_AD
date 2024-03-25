import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDialog extends JDialog {

    private List<CartItemPanel> cartItemPanels = new ArrayList<>();
    private JPanel cartPanel;

    public ShoppingCartDialog(Frame owner) {
        super(owner, "Carrito de Compra", true);
        getContentPane().setBackground(new Color(51, 51, 51));
        getContentPane().setLayout(null);
        
                JLabel lblImagenPasoDeCCompra = new JLabel("");
                // Aquí se asume que ya tienes una imagen en el path correcto
                lblImagenPasoDeCCompra.setIcon(new ImageIcon("E:\\Documentos\\GitHub\\Trabajo_Final_AD\\Frontend\\Ring\\src\\main\\java\\imagenes\\carritoDeCompra\\paso1Carrito.png"));
                lblImagenPasoDeCCompra.setBounds(309, 640, 635, 79);
                getContentPane().add(lblImagenPasoDeCCompra);

        cartPanel = new JPanel();
        cartPanel.setBackground(new Color(31, 31, 31)); // Color oscuro para el fondo del carrito
        cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));
        JScrollPane cartScrollPane = new JScrollPane(cartPanel);
        cartScrollPane.setBounds(10, 10, 882, 564);
        getContentPane().add(cartScrollPane);

        JPanel summaryPanel = new JPanel();
        summaryPanel.setBackground(new Color(31, 31, 31));
        summaryPanel.setBounds(939, 59, 243, 435);
        getContentPane().add(summaryPanel);

        // Aquí agregarías el resto de los componentes a summaryPanel...

        setSize(1211, 751); // Dimensiones del diálogo de carrito
        setLocationRelativeTo(owner);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setOpaque(false); // Hacerlo transparente para ver el fondo
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        JButton closeButton = new JButton(new ImageIcon(getClass().getResource("/imagenes/cerrar.png")));
        closeButton.setBorderPainted(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setFocusPainted(false);
        closeButton.addActionListener(e -> setVisible(false));
        
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(new ImageIcon(getClass().getResource("/imagenes/juegos/horizon-forbidden-west-complete-edition-complete-edition-pc-juego-steam-europe-cover.jpg")).getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
            }
        };
        backgroundPanel.setBounds(0, 0, 1211, 751);
        backgroundPanel.setLayout(new FlowLayout(FlowLayout.RIGHT)); 
        backgroundPanel.add(closeButton);
        getContentPane().add(backgroundPanel, BorderLayout.NORTH);

        setUndecorated(true);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void addItemToCart(String productName, String productPrice, ImageIcon productImage) {
        CartItemPanel itemPanel = new CartItemPanel(productName, productPrice, productImage);
        cartPanel.add(itemPanel);
        cartItemPanels.add(itemPanel);
        cartPanel.revalidate();
        cartPanel.repaint();
    }

    // Método main para probar el diálogo (opcional)
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            ShoppingCartDialog dialog = new ShoppingCartDialog(frame);
            dialog.addItemToCart("Dragon's Dogma 2 - Europe", "43.99€", new ImageIcon("/imagenes/juegos/dragon-s-dogma-2-pc-juego-steam-europe-cover.jpg"));
            dialog.addItemToCart("Helldivers 2 - Europe", "29.79€", new ImageIcon("/imagenes/juegos/helldivers-2-pc-juego-steam-europe-cover.jpg"));
            dialog.setVisible(true);
        });
    }
}
