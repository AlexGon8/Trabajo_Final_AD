import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JPanel loginPanel;
    private JTextField userField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton createAccountButton;

    public LoginFrame() {
        setTitle("Login");
        setSize(800, 600); // Tamaño del JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        initializeUI();
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
    }

    private void initializeUI() {
        // Panel de fondo con imagen
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Panel de inicio de sesión
        loginPanel = new JPanel();
        loginPanel.setPreferredSize(new Dimension(1080, 750));
        loginPanel.setOpaque(false); // Hacemos el panel transparente

        // Campos de texto y etiquetas
        userField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginPanel.add(new JLabel("Nombre de Usuario:"));
        loginPanel.add(userField);
        loginPanel.add(new JLabel("Contraseña:"));
        loginPanel.add(passwordField);

        // Botones
        loginButton = new JButton("Iniciar sesión");
        createAccountButton = new JButton("Crear cuenta");
        loginPanel.add(loginButton);
        loginPanel.add(createAccountButton);

        // Agregar el panel de inicio de sesión al panel de fondo
        backgroundPanel.add(loginPanel);
        
        // Agregar el panel de fondo al JFrame
        getContentPane().add(backgroundPanel, BorderLayout.CENTER);
    }

    // Clase para el panel de fondo con imagen
    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {
            try {
                backgroundImage = new ImageIcon("E:\\Imagenes\\Screenshots\\cosas.png").getImage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        });
    }
}

