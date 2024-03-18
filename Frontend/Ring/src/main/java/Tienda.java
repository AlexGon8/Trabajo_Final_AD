

import java.util.ArrayList;

/**
 * @author Oscar Castrillón
 */
public class Tienda {

    private String nombre;
    private ArrayList<Juego> juegos;

    public Tienda() {
        juegos = new ArrayList();
    }

    public Tienda(String nombre) {
        this();
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void agregarJuego(Juego juego) {
        juegos.add(juego);
    }

    public Juego obtenerJuegoMasVendido() {
        if (juegos.size() > 0) {
            Juego juego = juegos.get(0);

            for (int i = 1; i < juegos.size(); i++) {
                if (juegos.get(i).getCantidadVendida() > juego.getCantidadVendida()) {
                    juego = juegos.get(i);
                }
            }

            return juego;
        } else {
            return null;
        }
    }

    public int calcularCantidadJuegosVendidos() {
        int cantidadTotalVendidos = 0;

        if (juegos.size() > 0) {
            for (Juego juegoActual : juegos) {
                cantidadTotalVendidos = cantidadTotalVendidos + juegoActual.getCantidadVendida();
            }

            return cantidadTotalVendidos;
        } else {
            return cantidadTotalVendidos;
        }
    }

    public double calcularMontoJuegosVendidos() {
        double montoTotalJuegosVendidos = 0.0;

        if (juegos.size() > 0) {
            for (Juego juegoActual : juegos) {
                montoTotalJuegosVendidos += juegoActual.getPrecio() * juegoActual.getCantidadVendida();
            }

            return montoTotalJuegosVendidos;
        } else {
            return montoTotalJuegosVendidos;
        }
    }

    public boolean vender(Juego juego) {
        if (juegos.size() > 0) {

            if (juego.getCantidadVendida() < juego.getCantidadDisponible()) {
                juego.vender();

                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
