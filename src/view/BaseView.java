/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
/**
 *
 * @author L O Q
 */
public abstract class BaseView extends JFrame {
    protected final Color BG_COLOR = new Color(18, 18, 30);
    protected final Color TOP_COLOR = new Color(24, 24, 40);
    protected final Color CARD_COLOR = new Color(28, 28, 45);
    protected final Color FIELD_COLOR = new Color(40, 40, 62);
    protected final Color ACCENT_COLOR = new Color(255, 90, 130);
    protected final Color TEXT_MUTED = new Color(160, 160, 190);

    public BaseView(String title) {
        setTitle(title);
        setSize(420, 720);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    protected abstract void initComponents();

    protected JLabel makeLabel(String text, int size, int style, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial Unicode MS", style, size));
        label.setForeground(color);
        return label;
    }

    protected JButton makeTextButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial Unicode MS", Font.BOLD, 12));
        btn.setForeground(color);
        btn.setBorder(null);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setOpaque(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    protected JButton makeRoundedButton(String text, Color bgColor) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
                );

                g2.setColor(bgColor);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 14, 14);
                g2.dispose();

                super.paintComponent(g);
            }
        };

        btn.setFont(new Font("Arial Unicode MS", Font.BOLD, 14));
        btn.setForeground(Color.WHITE);
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    protected JTextField makeTextField() {
        JTextField field = new JTextField();
        field.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
        field.setForeground(Color.WHITE);
        field.setBackground(FIELD_COLOR);
        field.setCaretColor(Color.WHITE);
        field.setBorder(new CompoundBorder(
            new LineBorder(new Color(80, 80, 120), 1, true),
            new EmptyBorder(9, 12, 9, 12)
        ));
        return field;
    }

    protected JButton makeNavButton(String text, boolean active) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial Unicode MS", Font.PLAIN, 12));
        btn.setForeground(active ? ACCENT_COLOR : new Color(140, 140, 170));
        btn.setBackground(TOP_COLOR);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }
}
