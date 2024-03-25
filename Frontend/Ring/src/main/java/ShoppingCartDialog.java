import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDialog extends JDialog {

    private List<CartItemPanel> cartItemPanels = new ArrayList<>();
    
    public ShoppingCartDialog(Frame owner) {
        super(owner, "Carrito de Compra", true);
        getContentPane().setBackground(new Color(51, 51, 51));
        getContentPane().setLayout(null);

        JPanel cartPanel = new JPanel();
        cartPanel.setBackground(new Color(31, 31, 31)); // Color oscuro para el fondo del carrito
        cartPanel.setBounds(10, 10, 800, 500); // Dimensiones del panel de carrito
        cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));
        JScrollPane cartScrollPane = new JScrollPane(cartPanel);
        cartScrollPane.setBounds(10, 10, 882, 564);
        getContentPane().add(cartScrollPane);

        // Añade aquí los componentes de los productos del carrito...

        // Resumen del pedido y botón de proceder con el pago
        JPanel summaryPanel = new JPanel();
        summaryPanel.setBackground(new Color(31, 31, 31));
        summaryPanel.setBounds(939, 59, 243, 435);
        getContentPane().add(summaryPanel);

        // Agrega el resto de tus componentes al summaryPanel...
        // Por ejemplo, JLabel para "Precio oficial", "Descuento", "Subtotal"
        // y un JButton para "Proceder con el pago"

        setUndecorated(true);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1211, 751); // Dimensiones del diálogo de carrito
        setLocationRelativeTo(owner);
        
        // Paneles de contenido
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setOpaque(false); // Hacerlo transparente para ver el fondo
        getContentPane().add(contentPanel, BorderLayout.CENTER); // Contenido del carrito en el centro
        
        JLabel lblImagenPasoDeCCompra = new JLabel("");
        lblImagenPasoDeCCompra.setIcon(new ImageIcon("E:\\Documentos\\GitHub\\Trabajo_Final_AD\\Frontend\\Ring\\src\\main\\java\\imagenes\\carritoDeCompra\\paso1Carrito.png"));
        lblImagenPasoDeCCompra.setBounds(309, 640, 635, 79);
        getContentPane().add(lblImagenPasoDeCCompra);
        
        
        // Botón para cerrar el diálogo
        JButton closeButton = new JButton(new ImageIcon(getClass().getResource("/imagenes/cerrar.png")));
        closeButton.setBorderPainted(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setFocusPainted(false);
        closeButton.addActionListener(e -> setVisible(false));
        
                // Panel de fondo que incluye la imagen
                JPanel backgroundPanel = new JPanel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        g.drawImage(new ImageIcon(getClass().getResource("/imagenes/juegos/horizon-forbidden-west-complete-edition-complete-edition-pc-juego-steam-europe-cover.jpg")).getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
                    }
                };
                backgroundPanel.setBounds(0, 0, 1211, 751);
                backgroundPanel.setLayout(new FlowLayout(FlowLayout.RIGHT)); // Alinear el botón de cierre a la derecha
                backgroundPanel.add(closeButton);
                
                        // Agregar paneles al diálogo
                        getContentPane().add(backgroundPanel, BorderLayout.NORTH); // Botón de cerrar en la parte superior

        setUndecorated(true);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void addItemToCart(String productName, String productPrice) {
        CartItemPanel itemPanel = new CartItemPanel(productName, productPrice);
        cartItemPanels.add(itemPanel);
        // Agregar itemPanel al cartPanel...
    }
    
    private class CartItemPanel extends JPanel {
        private JLabel nameLabel;
        private JLabel priceLabel;
        
        public CartItemPanel(String name, String price) {
            setBackground(new Color(45, 45, 45));
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            
            nameLabel = new JLabel(name);
            nameLabel.setForeground(Color.WHITE);
            priceLabel = new JLabel(price);
            priceLabel.setForeground(Color.WHITE);
            
            add(nameLabel);
            add(Box.createHorizontalGlue()); // Espacio entre nombre y precio
            add(priceLabel);
        }
    }
    // Método main para probar el diálogo (opcional)
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            
            ShoppingCartDialog dialog = new ShoppingCartDialog(frame);
            dialog.setVisible(true);
        });
    }
}
