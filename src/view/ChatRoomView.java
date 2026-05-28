/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import model.User;
import model.ChatMessage;
import model.UserModel;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 *
 * @author anin7
 */
public class ChatRoomView extends JFrame {
    private JPanel messagesPanel;
    private JTextField txtPesan;
    private JButton btnKirim, btnBack;
    private User currentUser;
    private User otherUser;
    private UserModel userModel;
    private JScrollPane scrollPane;
 
    public ChatRoomView(User currentUser, User otherUser) {
        this.currentUser = currentUser;
        this.otherUser = otherUser;
        this.userModel = new UserModel();
        initComponents();
        loadMessages();
    }
 
    private void initComponents() {
        setTitle("MASO - Chat dengan " + otherUser.getNama());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(420, 720);
        setLocationRelativeTo(null);
        setResizable(false);
 
        Color bg = new Color(18, 18, 30);
        Color accent = new Color(255, 90, 130);
        Color topBg = new Color(24, 24, 40);
 
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(bg);
 
        // Top bar
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(topBg);
        topBar.setBorder(new EmptyBorder(10, 16, 10, 16));
 
        btnBack = new JButton("‹ Kembali");
        btnBack.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btnBack.setForeground(accent);
        btnBack.setBackground(topBg);
        btnBack.setBorderPainted(false);
        btnBack.setFocusPainted(false);
        btnBack.setContentAreaFilled(false);
        btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBack.addActionListener(e -> dispose());
 
        JPanel userInfo = new JPanel(new BorderLayout());
        userInfo.setBackground(topBg);
 
        JLabel avatar = new JLabel(otherUser.getKelamin().equals("Perempuan") ? "👩" : "👨");
        avatar.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        avatar.setBorder(new EmptyBorder(0, 10, 0, 10));
 
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.Y_AXIS));
        namePanel.setBackground(topBg);
        JLabel name = new JLabel(otherUser.getNama());
        name.setFont(new Font("Segoe UI", Font.BOLD, 14));
        name.setForeground(Color.WHITE);
        JLabel statusLabel = new JLabel(otherUser.getDomisili() + " • " + otherUser.getUmur() + " thn");
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        statusLabel.setForeground(new Color(140, 140, 170));
        namePanel.add(name);
        namePanel.add(statusLabel);
 
        userInfo.add(avatar, BorderLayout.WEST);
        userInfo.add(namePanel, BorderLayout.CENTER);
 
        topBar.add(btnBack, BorderLayout.WEST);
        topBar.add(userInfo, BorderLayout.CENTER);
 
        // Messages area
        messagesPanel = new JPanel();
        messagesPanel.setLayout(new BoxLayout(messagesPanel, BoxLayout.Y_AXIS));
        messagesPanel.setBackground(bg);
        messagesPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
 
        scrollPane = new JScrollPane(messagesPanel);
        scrollPane.setBackground(bg);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
 
        // Input area
        JPanel inputArea = new JPanel(new BorderLayout(8, 0));
        inputArea.setBackground(topBg);
        inputArea.setBorder(new CompoundBorder(
            new MatteBorder(1, 0, 0, 0, new Color(50, 50, 80)),
            new EmptyBorder(10, 14, 10, 14)
        ));
 
        txtPesan = new JTextField();
        txtPesan.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtPesan.setForeground(Color.WHITE);
        txtPesan.setBackground(new Color(40, 40, 60));
        txtPesan.setCaretColor(Color.WHITE);
        txtPesan.setBorder(new CompoundBorder(
            new LineBorder(new Color(70, 70, 100), 1, true),
            new EmptyBorder(10, 14, 10, 14)
        ));
        txtPesan.setToolTipText("Ketik pesan...");
 
        btnKirim = new JButton("➤") {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getModel().isPressed() ? new Color(200, 50, 90) : accent);
                g2.fillOval(0, 0, getWidth(), getHeight());
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btnKirim.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnKirim.setForeground(Color.WHITE);
        btnKirim.setOpaque(false);
        btnKirim.setContentAreaFilled(false);
        btnKirim.setBorderPainted(false);
        btnKirim.setFocusPainted(false);
        btnKirim.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnKirim.setPreferredSize(new Dimension(46, 46));
 
        inputArea.add(txtPesan, BorderLayout.CENTER);
        inputArea.add(btnKirim, BorderLayout.EAST);
 
        root.add(topBar, BorderLayout.NORTH);
        root.add(scrollPane, BorderLayout.CENTER);
        root.add(inputArea, BorderLayout.SOUTH);
        add(root);
 
        // Action listeners
        btnKirim.addActionListener(e -> sendMessage());
        txtPesan.addActionListener(e -> sendMessage());
    }
 
    private void loadMessages() {
        messagesPanel.removeAll();
        List<ChatMessage> messages = userModel.getMessages(currentUser.getId(), otherUser.getId());
 
        if (messages.isEmpty()) {
            JLabel empty = new JLabel("<html><center>👋<br>Mulai percakapan dengan " + otherUser.getNama() + "!</center></html>");
            empty.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            empty.setForeground(new Color(140, 140, 170));
            empty.setHorizontalAlignment(SwingConstants.CENTER);
            empty.setAlignmentX(CENTER_ALIGNMENT);
            empty.setBorder(new EmptyBorder(60, 20, 0, 20));
            messagesPanel.add(empty);
        } else {
            for (ChatMessage msg : messages) {
                boolean isMe = msg.getSenderId() == currentUser.getId();
                messagesPanel.add(makeBubble(msg.getPesan(), msg.getWaktuFormatted(), isMe));
                messagesPanel.add(Box.createVerticalStrut(4));
            }
        }
 
        messagesPanel.revalidate();
        messagesPanel.repaint();
        SwingUtilities.invokeLater(() -> {
            JScrollBar vertical = scrollPane.getVerticalScrollBar();
            vertical.setValue(vertical.getMaximum());
        });
    }
 
    private void sendMessage() {
        String text = txtPesan.getText().trim();
        if (text.isEmpty()) return;
        ChatMessage msg = new ChatMessage(currentUser.getId(), otherUser.getId(), text);
        userModel.sendMessage(msg);
        txtPesan.setText("");
        loadMessages();
    }
 
    private JPanel makeBubble(String text, String time, boolean isMe) {
        JPanel wrapper = new JPanel(new FlowLayout(isMe ? FlowLayout.RIGHT : FlowLayout.LEFT));
        wrapper.setBackground(new Color(18, 18, 30));
        wrapper.setBorder(new EmptyBorder(2, 10, 2, 10));
        wrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, 9999));
 
        JPanel bubble = new JPanel() {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(isMe ? new Color(200, 50, 90) : new Color(45, 45, 70));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 18, 18);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        bubble.setLayout(new BoxLayout(bubble, BoxLayout.Y_AXIS));
        bubble.setOpaque(false);
        bubble.setBorder(new EmptyBorder(10, 14, 10, 14));
 
        JLabel txtLabel = new JLabel("<html><p style='width:180px'>" + text + "</p></html>");
        txtLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtLabel.setForeground(Color.WHITE);
 
        JLabel timeLabel = new JLabel(time);
        timeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        timeLabel.setForeground(new Color(255, 255, 255, 140));
        timeLabel.setAlignmentX(isMe ? RIGHT_ALIGNMENT : LEFT_ALIGNMENT);
 
        bubble.add(txtLabel);
        bubble.add(Box.createVerticalStrut(3));
        bubble.add(timeLabel);
 
        wrapper.add(bubble);
        return wrapper;
    }
}
