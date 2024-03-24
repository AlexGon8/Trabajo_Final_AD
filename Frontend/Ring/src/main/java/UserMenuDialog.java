import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class UserMenuDialog extends JDialog {


	/**
	 * Create the dialog.
	 */
	public UserMenuDialog(Frame owner) {
        super(owner);
        setUndecorated(true);//quita el marco
        setSize(200, 300); // Ajusta el tamaño como necesites
        //setLocationRelativeTo(owner);
        getContentPane().setBackground(new Color(255, 255, 255, 200)); // Semi-transparente

        // Agregar elementos al JDialog, por ejemplo:
        add(new JLabel("Soporte 24/7"), BorderLayout.NORTH);
        // ... añadir otros elementos ...

        addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowLostFocus(WindowEvent e) {
                UserMenuDialog.this.setVisible(false);
                ((Home)owner).getGlassPane().setVisible(false); // Desactiva el oscurecimiento
            }
        });
    }

}
