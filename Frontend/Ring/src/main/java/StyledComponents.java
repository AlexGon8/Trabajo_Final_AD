import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class StyledComponents {
    public static JTextField createStyledTextField() {
        JTextField textField = new JTextField(10);
        textField.setForeground(Color.WHITE); // Texto blanco
        textField.setBackground(Color.DARK_GRAY); // Fondo gris oscuro
        textField.setBorder(new LineBorder(Color.GRAY)); // Borde gris
        return textField;
    }

    public static JTextArea createStyledTextArea() {
        JTextArea textArea = new JTextArea(5, 10);
        textArea.setForeground(Color.WHITE); // Texto blanco
        textArea.setBackground(Color.DARK_GRAY); // Fondo gris oscuro
        textArea.setBorder(new LineBorder(Color.GRAY)); // Borde gris
        return textArea;
    }

    public static JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setForeground(Color.ORANGE); // Texto naranja
        button.setBackground(Color.BLACK); // Fondo negro
        button.setBorder(new LineBorder(Color.ORANGE)); // Borde naranja
        button.setFocusPainted(false); // Sin foco pintado
        return button;
    }

    public static JCheckBox createStyledCheckBox(String text) {
        JCheckBox checkBox = new JCheckBox(text);
        checkBox.setForeground(Color.ORANGE); // Texto naranja
        checkBox.setBackground(Color.BLACK); // Fondo negro
        checkBox.setFocusPainted(false); // Sin foco pintado
        return checkBox;
    }
}
