import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import componentes.RoundedTextField;

public class Home extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel ContenedorGeneral;
    private JPanel glassPane;
    /*
     * para los iconos de la derecha superior
     */
    private JLabel userIcon;
    private JLabel cartIcon;
    private JLabel soporteIcon;
    private JLayeredPane layeredPane;
    private UserMenuDialog userMenuDialog;


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
    	
    	// para que la ventana se oscuresca
        // Configuración inicial para el panel de oscurecimiento
        glassPane = new JPanel();
        glassPane.setBackground(new Color(0, 0, 0, 128)); // Establece un color de fondo oscuro con transparencia
        glassPane.setLayout(null); // Sin layout para poder contener el JDialog
        glassPane.setVisible(false); // Inicialmente no es visible
        setGlassPane(glassPane); // Establecer como el GlassPane de la ventana

        // ...

        //metoodos para que no tenga marcos ni se pueda cambiar el tamaño y aparesca en l cenntro
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
        
        /*
         * creacion de los filtros del jpanel
         */
        

        // Añadir el panel de filtros justo debajo del panel del menú
     // Añadir el panel de filtros en la clase Home
        FilterPanel filterPanel = new FilterPanel("/imagenes/menu/FondoFiltros.jpg"); // Asegúrate de reemplazar con la ruta correcta de tu imagen
        filterPanel.setBounds(0, 86, getWidth(), 50); // Ajusta el y según la altura de menuPanel
        ContenedorGeneral.add(filterPanel);
        
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
        scrollPane.setBounds(42, 159, 1359, 704); // Ajusta las dimensiones como sea necesario
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        ContenedorGeneral.add(scrollPane);
        
        
        // Crea el JLayeredPane para superponer componentes
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, getWidth(), getHeight());
        ContenedorGeneral.add(layeredPane);
        
        // Añadir icono de usuario
        // Añadir icono de usuario
        userIcon = new JLabel(new ImageIcon(getClass().getResource("/imagenes/usuario.png")));
        userIcon.setBounds(10, 10, 56, 50); // Ajustar posición y tamaño
        userIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                toggleUserMenu();
            }
        });
        ContenedorGeneral.add(userIcon); // Añadir directamente al ContenedorGeneral

        //layeredPane.add(userIcon, Integer.valueOf(2)); // Añadir en una capa superior

        // Añadir icono de carrito de compras
        cartIcon = new JLabel(new ImageIcon(getClass().getResource("/imagenes/carrito.png")));
        cartIcon.setBounds(142, 10, 56, 50); // Ajustar posición y tamaño
        ContenedorGeneral.add(cartIcon); // Añadir directamente al ContenedorGeneral


        // Añadir icono de carrito de compras
        soporteIcon = new JLabel(new ImageIcon(getClass().getResource("/imagenes/soporte.png")));
        soporteIcon.setBounds(76, 10, 56, 50); // Ajustar posición y tamaño
        ContenedorGeneral.add(soporteIcon); // Añadir directamente al ContenedorGeneral

    }
    
    // La función para mostrar/ocultar el menú y aplicar el efecto de oscurecimiento
    private void toggleUserMenu() {
        if (userMenuDialog == null || !userMenuDialog.isVisible()) {
            userMenuDialog = new UserMenuDialog(this);

            // Obtiene la ubicación en pantalla de la ventana principal (Home)
            Point locationOnScreen = this.getLocationOnScreen();

            // Calcula la posición del 'userIcon' respecto a la pantalla y ajusta la posición del JDialog
            // Suma la posición X del icono al JFrame y ajusta la Y para que aparezca debajo del icono
            int x = locationOnScreen.x + userIcon.getBounds().x; 
            int y = locationOnScreen.y + userIcon.getBounds().y + userIcon.getBounds().height;

            userMenuDialog.setLocation(x, y); // Establece la posición del JDialog
            userMenuDialog.setVisible(true);
            glassPane.setVisible(true); // Activa el oscurecimiento
        } else {
            userMenuDialog.setVisible(false);
            glassPane.setVisible(false); // Desactiva el oscurecimiento
        }
    }
    
    
    
}
