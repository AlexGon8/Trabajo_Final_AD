import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDialog extends JDialog {

    private List<CartItemPanel> cartItemPanels = new ArrayList<>();
    private JPanel cartPanel;
    private JLabel lblPrecioOficial;
    private JLabel lblDescuento;
    private JLabel lblSubtotal;
    private JButton btnPagar;
    private JButton btnContinuarComprando;
    private JLabel lblPrecioTotal;


    public ShoppingCartDialog(Frame owner) {
        super(owner, "Carrito de Compra", true);
        getContentPane().setBackground(new Color(51, 51, 51));
        getContentPane().setLayout(null);

        cartPanel = new JPanel();
        cartPanel.setBackground(new Color(31, 31, 31)); // Color oscuro para el fondo del carrito
        cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));
        JScrollPane cartScrollPane = new JScrollPane(cartPanel);
        cartScrollPane.setBounds(10, 10, 934, 565);
        getContentPane().add(cartScrollPane);

        lblPrecioTotal = new JLabel("Precio oficial: 0€");
        lblPrecioTotal.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblPrecioTotal.setHorizontalAlignment(SwingConstants.CENTER);
        lblDescuento = new JLabel("Descuento: 0€");
        lblDescuento.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblDescuento.setHorizontalAlignment(SwingConstants.CENTER);
        lblSubtotal = new JLabel("Subtotal: 0€");
        lblSubtotal.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblSubtotal.setHorizontalAlignment(SwingConstants.CENTER);

        // Configuración de lblPrecioTotal, lblDescuento, lblSubtotal...
        // Por ejemplo, configurar el color y fuente:
        lblPrecioTotal.setForeground(Color.WHITE);
        lblDescuento.setForeground(Color.WHITE);
        lblSubtotal.setForeground(Color.WHITE);

        JPanel summaryPanel = new JPanel();
        summaryPanel.setLayout(new GridLayout(0, 1)); // GridLayout para organizar los componentes verticalmente
        summaryPanel.setBackground(new Color(31, 31, 31));
        summaryPanel.setBounds(962, 59, 239, 438);
        getContentPane().add(summaryPanel);

        // Agregar las etiquetas al panel de resumen
        summaryPanel.add(lblPrecioTotal);
        summaryPanel.add(lblDescuento);
        summaryPanel.add(lblSubtotal);

        // Botón para proceder con el pago
        btnPagar = new JButton("Proceder con el pago");
        btnPagar.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnPagar.setBounds(51, 77, 133, 22);
        btnPagar.setBackground(new Color(255, 128, 0)); // Color naranja como en la imagen
        btnPagar.setForeground(Color.WHITE);
        btnPagar.setFocusPainted(false);
        summaryPanel.add(btnPagar);

        // Botón para continuar comprando
        btnContinuarComprando = new JButton("Continuar comprando");
        btnContinuarComprando.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnContinuarComprando.setBounds(51, 128, 135, 22);
        btnContinuarComprando.setBackground(new Color(31, 31, 31)); // Fondo oscuro
        btnContinuarComprando.setForeground(new Color(255, 128, 64));
        btnContinuarComprando.setFocusPainted(false);
        summaryPanel.add(btnContinuarComprando);
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
        
        JLabel lblImagenPasoDeCCompra = new JLabel("");
        // Aquí se asume que ya tienes una imagen en el path correcto
        lblImagenPasoDeCCompra.setIcon(new ImageIcon("/imagenes/minimize-maximize-and-close-button-icon-elements-of-web-or-mobile-app-vector.jpg"));
        lblImagenPasoDeCCompra.setBounds(277, 640, 667, 79);
        getContentPane().add(lblImagenPasoDeCCompra);
        
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(new ImageIcon(getClass().getResource("/imagenes/carritoDeCompra/fondo_carrito.jpg")).getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
            }
        };
        backgroundPanel.setBounds(0, 0, 1211, 751);
        backgroundPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        backgroundPanel.add(closeButton);
        getContentPane().add(backgroundPanel, BorderLayout.NORTH);

        setUndecorated(true);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        
        
        // Botón para proceder con el pago
        btnPagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(ShoppingCartDialog.this,
                                              "Error al conectar con la pasarela de pago. Inténtelo más tarde.",
                                              "Error de Pago",
                                              JOptionPane.ERROR_MESSAGE);
            }
        });

        // Botón para continuar comprando
        btnContinuarComprando.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShoppingCartDialog.this.setVisible(false); // Cierra el diálogo
            }
        });
    }

    public void addItemToCart(String productName, String productPrice, ImageIcon productImage) {
        CartItemPanel itemPanel = new CartItemPanel(productName, productPrice, productImage);
        itemPanel.addPropertyChangeListener("cantidadCambiada", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                updateCartSummary(); // Actualizar el resumen del carrito cuando cambia la cantidad
            }
        });
        cartPanel.add(itemPanel);
        cartItemPanels.add(itemPanel);
        cartPanel.revalidate();
        cartPanel.repaint();
        updateCartSummary(); // Actualizar el resumen del carrito inmediatamente después de agregar el artículo
    }
    private void updateCartSummary() {
        double precioTotal = 0.0;
        double descuento = 0.0; // Asumiendo que tienes una manera de calcular el descuento
        
        for (CartItemPanel item : cartItemPanels) {
            double precioItem = item.getPrecio() * item.getCantidadSeleccionada();
            precioTotal += precioItem;
            // Aquí deberías agregar tu lógica para calcular el descuento
        }

        double subtotal = precioTotal - descuento;
        
        lblPrecioTotal.setText("Precio oficial: " + String.format("%.2f€", precioTotal));
        lblDescuento.setText("Descuento: -" + String.format("%.2f€", descuento));
        lblSubtotal.setText("Subtotal: " + String.format("%.2f€", subtotal));
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
