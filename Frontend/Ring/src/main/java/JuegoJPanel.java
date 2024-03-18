

import co.edu.universidadean.modelos.Juego;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class JuegoJPanel extends JPanel implements ActionListener {

    private Home principal;

    private Juego juego;

    private JLabel lblCategoriaJuego;

    private JLabel lblPrecioJuego;

    private JLabel lblDisponibles;

    private JLabel lblVendidas;

    private JTextField txtCategoriaJuego;

    private JTextField txtPrecioJuego;

    private JTextField txtDisponibles;

    private JTextField txtVendidas;

    private JLabel lblImagen;

    private JButton botonComprar;

    public JuegoJPanel(Home p) {
        principal = p;

        setLayout(new BorderLayout());

        JPanel panelDatosJuego = new JPanel();
        add(panelDatosJuego, BorderLayout.CENTER);

        panelDatosJuego.setBorder(new EmptyBorder(5, 5, 5, 5));
        setBorder(new TitledBorder("Juego"));

        panelDatosJuego.setLayout(new GridLayout(5, 1, 0, 5));

        lblCategoriaJuego = new JLabel("Categoría: ");
        txtCategoriaJuego = new JTextField(15);
        txtCategoriaJuego.setEditable(false);
        panelDatosJuego.add(lblCategoriaJuego);
        panelDatosJuego.add(txtCategoriaJuego);

        lblPrecioJuego = new JLabel("Precio: ");
        txtPrecioJuego = new JTextField(15);
        txtPrecioJuego.setEditable(false);
        panelDatosJuego.add(lblPrecioJuego);
        panelDatosJuego.add(txtPrecioJuego);

        lblDisponibles = new JLabel("Disponibles: ");
        txtDisponibles = new JTextField(15);
        txtDisponibles.setEditable(false);
        panelDatosJuego.add(lblDisponibles);
        panelDatosJuego.add(txtDisponibles);

        lblVendidas = new JLabel("Vendidas: ");
        txtVendidas = new JTextField(15);
        txtVendidas.setEditable(false);
        panelDatosJuego.add(lblVendidas);
        panelDatosJuego.add(txtVendidas);

        JPanel panelSur = new JPanel();
        panelSur.setLayout(new GridLayout(1, 2));

        botonComprar = new JButton("Comprar");
        botonComprar.setActionCommand("COMPRAR");
        botonComprar.addActionListener(this);
        panelSur.add(botonComprar);

        add(panelSur, BorderLayout.SOUTH);

        lblImagen = new JLabel();
        add(lblImagen, BorderLayout.WEST);

    }

    public void actualizarPanel(Juego juego) {
        this.juego = juego;
        setBorder(new TitledBorder(juego.getNombre()));

        switch (juego.getCategoria()) {
            case DEPORTES: {
                txtCategoriaJuego.setText("Deportes");
                break;
            }
            case RPG: {
                txtCategoriaJuego.setText("RPG");
                break;
            }
            case FPS: {
                txtCategoriaJuego.setText("FPS");
                break;
            }
            case SIMULADOR: {
                txtCategoriaJuego.setText("Simulador");
                break;
            }
            case ARCADE: {
                txtCategoriaJuego.setText("Arcade");
                break;
            }
            case OCIO: {
                txtCategoriaJuego.setText("Ocio");
                break;
            }
        }
        txtPrecioJuego.setText(String.valueOf(juego.getPrecio()));
        txtDisponibles.setText(String.format("%d", juego.getCantidadDisponible() - juego.getCantidadVendida()));
        txtVendidas.setText(String.valueOf(juego.getCantidadVendida()));

        String ruta = "/datos/" + juego.getImagen();

        lblImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource(ruta)));
        lblImagen.setBorder(new TitledBorder(""));
    }

    public void actionPerformed(ActionEvent evento) {
        String comando = evento.getActionCommand();

        if (comando.equals("COMPRAR")) {
            if (principal.venderJuego(juego)) {
                actualizarPanel(juego);
            } else {
                JOptionPane.showMessageDialog(this, "Producto no disponible", "Mensaje", JOptionPane.OK_OPTION);
            }
        }
    }

}
