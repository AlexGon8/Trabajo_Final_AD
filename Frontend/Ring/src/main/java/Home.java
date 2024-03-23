import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import componentes.RoundedTextField;

public class Home extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel ContenedorGeneral;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Home frame = new Home();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Home() {
        setUndecorated(true);
        setResizable(false);
        setBackground(new Color(51, 51, 51));
        setSize(1401, 863);
        setLocationRelativeTo(null);

        ContenedorGeneral = new JPanel();
        ContenedorGeneral.setBackground(Color.DARK_GRAY);
        ContenedorGeneral.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(ContenedorGeneral);
        ContenedorGeneral.setLayout(null);

        JButton closeButton = new JButton(new ImageIcon(getClass().getResource("/imagenes/cerrar (1).png")));
        closeButton.setBounds(1336, 10, 45, 37);
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JButton minimizeButton = new JButton(new ImageIcon(getClass().getResource("/imagenes/minimizar (1).png")));
        minimizeButton.setBounds(1281, 10, 45, 37);
        minimizeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setExtendedState(JFrame.ICONIFIED);
            }
        });

        ContenedorGeneral.add(minimizeButton);
        ContenedorGeneral.add(closeButton);

        /*
         * para el menu
         */
        MenuPanel menuPanel = new MenuPanel();
        menuPanel.setBounds(391, 11, 631, 64); // Ajusta las dimensiones según sea necesario
        ContenedorGeneral.add(menuPanel);
        // Creación del panel para los juegos
        JPanel panelJuegos = new JPanel();
        panelJuegos.setLayout(new GridLayout(0, 2, 10, 10)); // 0 filas y 3 columnas
        panelJuegos.setBackground(Color.DARK_GRAY);

        // Aquí se deben crear y añadir los JuegoJPanel
        for (int i = 0; i < 9; i++) {
            ImageIcon icono = new ImageIcon(getClass().getResource("/imagenes/juegos/age-of-empires-iv-the-sultans-ascend-pc-juego-steam-cover.jpg"));
            JuegoJPanel juegoPanel = new JuegoJPanel("Nombre del Juego", "Precio €", icono);
            panelJuegos.add(juegoPanel);
        }

        JScrollPane scrollPane = new JScrollPane(panelJuegos);
        scrollPane.setBounds(42, 159, 1359, 693); // Ajusta las dimensiones como sea necesario
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        ContenedorGeneral.add(scrollPane);
    }
}
