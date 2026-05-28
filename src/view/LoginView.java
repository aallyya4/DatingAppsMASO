/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.LoginController;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author anin7
 */
public class LoginView extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnRegister;
    private JLabel lblError;
 
    public LoginView() {
        initComponents();
    }
 
    private void initComponents() {
        setTitle("MASO - Match Soulmate Online");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(420, 560);
        setLocationRelativeTo(null);
        setResizable(false);
 
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(18, 18, 30));
 
        // Header / Logo Panel
        JPanel headerPanel = new JPanel(new GridBagLayout());
        headerPanel.setBackground(new Color(18, 18, 30));
        headerPanel.setBorder(new EmptyBorder(40, 30, 20, 30));
 
        JLabel lblLogo = new JLabel("💘 MASO");
        lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 32));
        lblLogo.setForeground(new Color(255, 90, 130));
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
 
        JLabel lblTagline = new JLabel("Match Soulmate Online");
        lblTagline.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblTagline.setForeground(new Color(160, 160, 190));
        lblTagline.setHorizontalAlignment(SwingConstants.CENTER);
 
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0; gbc.insets = new Insets(0,0,5,0);
        headerPanel.add(lblLogo, gbc);
        gbc.gridy = 1;
        headerPanel.add(lblTagline, gbc);
 
        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(new Color(28, 28, 45));
        formPanel.setBorder(new CompoundBorder(
            new LineBorder(new Color(255, 90, 130, 60), 1, true),
            new EmptyBorder(30, 30, 30, 30)
        ));
 
        JLabel lblTitle = new JLabel("Masuk ke Akun");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
 
        JLabel lblSub = new JLabel("Temukan pasangan jiwa kamu");
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSub.setForeground(new Color(140, 140, 170));
        lblSub.setAlignmentX(Component.CENTER_ALIGNMENT);
 
        JLabel lblUser = new JLabel("Username");
        lblUser.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblUser.setForeground(new Color(200, 200, 220));
 
        txtUsername = createTextField("Masukkan username...");
 
        JLabel lblPass = new JLabel("Password");
        lblPass.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblPass.setForeground(new Color(200, 200, 220));
 
        txtPassword = new JPasswordField();
        styleField(txtPassword);
        txtPassword.setToolTipText("Masukkan password");
 
        lblError = new JLabel(" ");
        lblError.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblError.setForeground(new Color(255, 80, 80));
        lblError.setAlignmentX(Component.CENTER_ALIGNMENT);
 
        btnLogin = createPrimaryButton("Masuk");
        btnRegister = createSecondaryButton("Belum punya akun? Daftar");
 
        formPanel.add(lblTitle);
        formPanel.add(Box.createVerticalStrut(4));
        formPanel.add(lblSub);
        formPanel.add(Box.createVerticalStrut(24));
        formPanel.add(lblUser);
        formPanel.add(Box.createVerticalStrut(6));
        formPanel.add(txtUsername);
        formPanel.add(Box.createVerticalStrut(14));
        formPanel.add(lblPass);
        formPanel.add(Box.createVerticalStrut(6));
        formPanel.add(txtPassword);
        formPanel.add(Box.createVerticalStrut(8));
        formPanel.add(lblError);
        formPanel.add(Box.createVerticalStrut(16));
        formPanel.add(btnLogin);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(btnRegister);
 
        JPanel centerWrapper = new JPanel(new BorderLayout());
        centerWrapper.setBackground(new Color(18, 18, 30));
        centerWrapper.setBorder(new EmptyBorder(0, 30, 40, 30));
        centerWrapper.add(formPanel, BorderLayout.CENTER);
 
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(centerWrapper, BorderLayout.CENTER);
 
        add(mainPanel);
 
        // Controller binding
        new LoginController(this);
    }
 
    public JTextField getTxtUsername() { return txtUsername; }
    public JPasswordField getTxtPassword() { return txtPassword; }
    public JButton getBtnLogin() { return btnLogin; }
    public JButton getBtnRegister() { return btnRegister; }
    public JLabel getLblError() { return lblError; }
 
    private JTextField createTextField(String hint) {
        JTextField f = new JTextField();
        styleField(f);
        return f;
    }
 
    private void styleField(JTextField f) {
        f.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        f.setForeground(Color.WHITE);
        f.setBackground(new Color(40, 40, 62));
        f.setCaretColor(Color.WHITE);
        f.setBorder(new CompoundBorder(
            new LineBorder(new Color(80, 80, 120), 1, true),
            new EmptyBorder(10, 14, 10, 14)
        ));
        f.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
    }
 
    private JButton createPrimaryButton(String text) {
        JButton btn = new JButton(text) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (getModel().isPressed()) g2.setColor(new Color(200, 50, 90));
                else if (getModel().isRollover()) g2.setColor(new Color(240, 80, 120));
                else g2.setColor(new Color(255, 90, 130));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setForeground(Color.WHITE);
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 46));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        return btn;
    }
 
    private JButton createSecondaryButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btn.setForeground(new Color(255, 90, 130));
        btn.setBackground(new Color(28, 28, 45));
        btn.setBorder(new LineBorder(new Color(255, 90, 130, 80), 1, true));
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        return btn;
    }
}
