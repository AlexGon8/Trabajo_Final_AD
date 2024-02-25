public class BasicWindowMonitor extends java.awt.event.WindowAdapter {
  
    // Método que maneja el evento de cierre de ventana
    @Override
    public void windowClosing(java.awt.event.WindowEvent event) {
        java.awt.Window window = event.getWindow();
        window.setVisible(false); // Hace invisible la ventana
        window.dispose(); // Libera los recursos de la ventana
        // Considerar otras acciones para cerrar la aplicación si es necesario
    }

    // Constructor protegido
    protected BasicWindowMonitor() {
        super();
    }
}