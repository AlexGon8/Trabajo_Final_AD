

/**
 * @author Oscar Castrillón
 */
public class Juego {
    private String nombre;
    private double precio;
    private int cantidadDisponible;
    private int cantidadVendida;
    private String imagen;
    private Categoria categoria;

    public Juego() {
    }

    public Juego(String nombre, double precio, int cantidadDisponible, String imagen, Categoria categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadDisponible = cantidadDisponible;
        this.imagen = imagen;
        this.categoria = categoria;
        this.cantidadVendida = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public int getCantidadVendida() {
        return cantidadVendida;
    }

    public void setCantidadVendida(int cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }
    
    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void vender() {
        cantidadVendida = cantidadVendida + 1;
    }
}
