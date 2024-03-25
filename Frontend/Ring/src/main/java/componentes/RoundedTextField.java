package componentes;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.geom.RoundRectangle2D;

public class RoundedTextField extends JTextField {
    private Shape shape;
    private String hintText;
    private Insets dummyInsets;

    public RoundedTextField() {
        this(10, ""); // Asume un tamaño de texto por defecto y un texto de sugerencia vacío
    }

    public RoundedTextField(int columns, String hintText) {
        super(columns);
        this.hintText = hintText;
        initialize();
    }

    private void initialize() {
        setOpaque(false); // As it paints its own background
        Border roundedBorder = new RoundedBorder(10); // 10 is the radius
        Border emptyBorder = new EmptyBorder(0, 5, 0, 0);
        setBorder(BorderFactory.createCompoundBorder(roundedBorder, emptyBorder));
        
        this.dummyInsets = super.getInsets();
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (getText().isEmpty()) {
                    setText("");
                    setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getText().isEmpty()) {
                    setText(hintText);
                    setForeground(Color.GRAY);
                }
            }
        });
        // Initially show hint text
        setText(hintText);
        setForeground(Color.GRAY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
    }

    @Override
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
        }
        return shape.contains(x, y);
    }

    private class RoundedBorder extends AbstractBorder {
        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.left = this.radius + 10;
            insets.right = this.radius;
            insets.top = this.radius + 1;
            insets.bottom = this.radius + 2;
            return insets;
        }
    }

    @Override
    public void setText(String text) {
        super.setText(text);
        if (!text.isEmpty()) {
            setForeground(Color.BLACK);
        }
    }

    @Override
    public Insets getInsets() {
        Insets original = super.getInsets();
        return new Insets(original.top + dummyInsets.top, original.left + dummyInsets.left,
                          original.bottom + dummyInsets.bottom, original.right + dummyInsets.right);
    }

    public String getHintText() {
        return this.hintText;
    }
}
