

import Clases.Juego;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class JuegoJPanel extends JPanel implements ActionListener {

    private Home principal;

    private Juego juego;

    private JLabel lblNombreJuego;
    private JLabel lblPrecioJuego;
    private JLabel lblAnnoDeSalida;
    private JLabel lblCompannia;

    private JLabel lblValorNombreJuego;
    private JLabel lblValorPrecioJuego;
    private JLabel lblValorAnnoDeSalida;
    private JLabel lblValorCompannia;

    private JButton botonComprar;

    public JuegoJPanel(Home p) {
        principal = p;

        setLayout(new BorderLayout());

        JPanel panelDatosJuego = new JPanel();
        add(panelDatosJuego, BorderLayout.CENTER);

        panelDatosJuego.setBorder(new EmptyBorder(5, 5, 5, 5));
        setBorder(new TitledBorder("Juego"));

        panelDatosJuego.setLayout(new GridLayout(4, 2, 0, 5));

        lblNombreJuego = new JLabel("Nombre: ");
        lblValorNombreJuego = new JLabel();
        panelDatosJuego.add(lblNombreJuego);
        panelDatosJuego.add(lblValorNombreJuego);

        lblPrecioJuego = new JLabel("Precio: ");
        lblValorPrecioJuego = new JLabel();
        panelDatosJuego.add(lblPrecioJuego);
        panelDatosJuego.add(lblValorPrecioJuego);

        lblAnnoDeSalida = new JLabel("Año de Salida: ");
        lblValorAnnoDeSalida = new JLabel();
        panelDatosJuego.add(lblAnnoDeSalida);
        panelDatosJuego.add(lblValorAnnoDeSalida);

        lblCompannia = new JLabel("Compañía: ");
        lblValorCompannia = new JLabel();
        panelDatosJuego.add(lblCompannia);
        panelDatosJuego.add(lblValorCompannia);

        botonComprar = new JButton("Comprar");
        botonComprar.setActionCommand("COMPRAR");
        botonComprar.addActionListener(this);
        add(botonComprar, BorderLayout.SOUTH);
    }

    public JuegoJPanel() {
		// TODO Auto-generated constructor stub
	}

	public void actualizarPanel(Juego juego) {
        this.juego = juego;
        setBorder(new TitledBorder(juego.getNombre()));

        lblValorNombreJuego.setText(juego.getNombre());
        lblValorPrecioJuego.setText(juego.getPrecio().toString());
        lblValorAnnoDeSalida.setText(juego.getAnnoDeSalida().toString());
        lblValorCompannia.setText(juego.getCompannia().getNombre());
    }

    public void actionPerformed(ActionEvent evento) {
        String comando = evento.getActionCommand();

        if (comando.equals("COMPRAR")) {
            // Lógica para comprar el juego
            // Esta lógica dependerá de cómo estés manejando las ventas en tu sistema
        }
    }
}
