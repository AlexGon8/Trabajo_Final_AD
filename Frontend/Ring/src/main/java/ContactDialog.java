import javax.swing.*;
import java.awt.*;

public class ContactDialog extends JDialog {

    private ButtonGroup optionsGroup;

    public ContactDialog(Frame owner) {
        super(owner, "Página de Contacto", true);
        getContentPane().setBackground(new Color(128, 128, 128));
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 0, 0, 0)); // Transparente para mostrar el GIF
        panel.setBounds(10, 11, 1154, 743);
        getContentPane().add(panel);
        panel.setLayout(null);

        int yPosition = 50;

        // Nombre
        panel.add(createLabel("Nombre:", 30, yPosition, 300, 30));
        JTextField nameField = createTextField(30, yPosition + 20, 300, 30);
        panel.add(nameField);
        
        // Email
        yPosition += 70;
        panel.add(createLabel("Email:", 30, yPosition, 300, 30));
        JTextField emailField = createTextField(30, yPosition + 20, 300, 30);
        panel.add(emailField);
        
        // Mensaje
        yPosition += 70;
        panel.add(createLabel("Mensaje:", 30, yPosition, 300, 30));
        JTextArea messageArea = createTextArea(30, yPosition + 20, 300, 100);
        JScrollPane scrollPane = new JScrollPane(messageArea);
        scrollPane.setBounds(30, yPosition + 20, 300, 100);
        panel.add(scrollPane);

        // Botones de opción y ButtonGroup
        yPosition += 140;
        optionsGroup = new ButtonGroup();
        panel.add(createOption("Pedidos y productos", 30, yPosition, 300, 30));
        panel.add(createOption("Pago", 30, yPosition + 30, 300, 30));
        panel.add(createOption("Cuenta y seguridad", 30, yPosition + 60, 300, 30));
        panel.add(createOption("Afiliación y patrocinios", 30, yPosition + 90, 300, 30));
        
        // Botón Enviar
        JButton sendButton = createButton("Enviar", 30, yPosition + 130, 100, 30);
        panel.add(sendButton);
        
                JLabel gifFondo = new JLabel("");
                gifFondo.setIcon(new ImageIcon(ContactDialog.class.getResource("/imagenes/gifRegistrogif.gif")));
                gifFondo.setBounds(0, 0, 1154, 743);
                panel.add(gifFondo);

        setUndecorated(true);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1174, 765);
        setLocationRelativeTo(owner);
    }

    private JLabel createLabel(String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setBounds(x, y, width, height);
        return label;
    }

    private JTextField createTextField(int x, int y, int width, int height) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, width, height);
        textField.setForeground(Color.WHITE);
        textField.setBackground(Color.DARK_GRAY);
        return textField;
    }

    private JTextArea createTextArea(int x, int y, int width, int height) {
        JTextArea textArea = new JTextArea();
        textArea.setBounds(x, y, width, height);
        textArea.setForeground(Color.WHITE);
        textArea.setBackground(Color.DARK_GRAY);
        return textArea;
    }

    private JRadioButton createOption(String text, int x, int y, int width, int height) {
        JRadioButton radioButton = new JRadioButton(text);
        radioButton.setForeground(Color.ORANGE);
        radioButton.setBackground(Color.BLACK);
        radioButton.setFocusPainted(false);
        radioButton.setBounds(x, y, width, height);
        optionsGroup.add(radioButton);
        return radioButton;
    }

    private JButton createButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setForeground(Color.ORANGE);
        button.setBackground(Color.BLACK);
        button.setFocusPainted(false);
        return button;
    }
}
