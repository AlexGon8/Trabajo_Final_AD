import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;

public class UserMenuDialog extends JDialog {

    public UserMenuDialog(Frame owner) {
        super(owner);
        setUndecorated(true);
        setSize(200, 300);
        getContentPane().setBackground(new Color(0, 0, 0, 160)); // Fondo semi-transparente

        // Panel que contendrá los botones del menú
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Margen alrededor del panel
        contentPanel.setBackground(new Color(0, 0, 0, 160)); // Color de fondo semi-transparente
        add(contentPanel);

        // Nombres de los botones
        String[] buttonNames = {"Soporte", "Mis pedidos", "Wishlist", "Configuración", "Desconectarse"};

        // Crear y añadir los botones al contentPanel
        for (String buttonName : buttonNames) {
            JButton button = new JButton(buttonName);
            button.setForeground(Color.WHITE); // Color de texto blanco
            button.setFont(new Font("Arial", Font.BOLD, 16)); // Tamaño y estilo del texto
            button.setAlignmentX(Component.CENTER_ALIGNMENT); // Alinear al centro
            button.setContentAreaFilled(false); // Sin relleno
            button.setBorderPainted(false); // Sin borde pintado
            button.setFocusPainted(false); // Sin foco pintado
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    button.setForeground(Color.GRAY); // Cambiar color al pasar el mouse
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    button.setForeground(Color.WHITE); // Restablecer color cuando el mouse sale
                }
            });
            contentPanel.add(button);
            contentPanel.add(Box.createVerticalStrut(10)); // Espacio vertical entre botones
        }

        // Para asegurarnos de que el JFrame se vuelva a activar correctamente
        addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowLostFocus(WindowEvent e) {
                UserMenuDialog.this.setVisible(false);
                if (owner instanceof Home) {
                    ((Home)owner).getGlassPane().setVisible(false); // Desactiva el oscurecimiento
                }
            }
        });
        
        // Encuentra el botón de Soporte y añade el ActionListener
        for (Component comp : contentPanel.getComponents()) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                if ("Soporte".equals(button.getText())) {
                    button.addActionListener(e -> {
                        // Aquí creas y muestras el JDialog de contacto
                        ContactDialog contactDialog = new ContactDialog(owner);
                        contactDialog.setVisible(true);
                    });
                }
            }
        }
    }
}
