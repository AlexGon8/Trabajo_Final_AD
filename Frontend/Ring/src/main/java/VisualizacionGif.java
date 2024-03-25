

import java.io.IOException;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Ejercicio 192: Reproducir una animación GIF con un JLabel.
 * 
 * @author John Ortiz Ordoñez
 */
public class VisualizacionGif {

    public static void main(String[] args) throws IOException{
        
        final String IMAGEN = "https://64.media.tumblr.com/0901013c2121ffe2b48a755080bf4523/c606e4a7979684c2-78/s1280x1920/e668408582fde369ac25cfa2fbec4451d66f9673.gif";
        
        URL urlGif = new URL(IMAGEN);
        Icon iconGif = new ImageIcon(urlGif);
        JLabel animacionGif = new JLabel(iconGif);
        
        JFrame ventana = new JFrame("Visualizando GIF");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.getContentPane().add(animacionGif);
        ventana.setSize(400, 400);
        
        ventana.setVisible(true);
    }
}