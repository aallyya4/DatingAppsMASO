/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import model.User;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

/**
 *
 * @author anin7
 */
public class ChatListView extends JFrame {
    private JButton btnHome, btnChat, btnProfil;
    private JPanel listPanel;
    private User currentUser;
 
    public ChatListView(User currentUser, List<User> matches, Consumer<User> onChatSelected) {
        this.currentUser = currentUser;
        initComponents(matches, onChatSelected);
    }
 
    private void initComponents(List<User> matches, Consumer<User> onChatSelected) {
        setTitle("MASO - Chat");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(420, 720);
        setLocationRelativeTo(null);
        setResizable(false);
 
        Color bg = new Color(18, 18, 30);
        Color accent = new Color(255, 90, 130);
 
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(bg);
 
        // Top bar
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(new Color(24, 24, 40));
        topBar.setBorder(new EmptyBorder(14, 20, 14, 20));
        JLabel title = new JLabel("💬 Pesan");
        title.setFont(new Font("Arial Unicode MS", Font.BOLD, 18));
        title.setForeground(accent);
        topBar.add(title, BorderLayout.WEST);
 
        // Match count badge
        JLabel badge = new JLabel(matches.size() + " match");
        badge.setFont(new Font("Arial Unicode MS", Font.PLAIN, 12));
        badge.setForeground(new Color(255, 180, 100));
        topBar.add(badge, BorderLayout.EAST);
 
        // List panel
        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(bg);
 
        if (matches.isEmpty()) {
            JLabel empty = new JLabel("<html><center>💔<br><br>Belum ada match<br><small>Swipe dan saling 'Suka!' untuk memulai chat</small></center></html>");
            empty.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
            empty.setForeground(new Color(140, 140, 170));
            empty.setHorizontalAlignment(SwingConstants.CENTER);
            empty.setBorder(new EmptyBorder(80, 30, 0, 30));
            listPanel.add(empty);
        } else {
            for (User match : matches) {
                listPanel.add(makeMatchItem(match, onChatSelected));
                listPanel.add(makeDivider());
            }
        }
 
        JScrollPane scroll = new JScrollPane(listPanel);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setBackground(bg);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
 
        // Bottom nav
        JPanel navPanel = new JPanel(new GridLayout(1, 3));
        navPanel.setBackground(new Color(24, 24, 40));
        navPanel.setBorder(new CompoundBorder(
            new MatteBorder(1, 0, 0, 0, new Color(50, 50, 80)),
            new EmptyBorder(8, 0, 8, 0)
        ));
        btnHome = makeNavButton("🏠 Home", false);
        btnChat = makeNavButton("💬 Chat", true);
        btnProfil = makeNavButton("👤 Profil", false);
        navPanel.add(btnHome);
        navPanel.add(btnChat);
        navPanel.add(btnProfil);
 
        root.add(topBar, BorderLayout.NORTH);
        root.add(scroll, BorderLayout.CENTER);
        root.add(navPanel, BorderLayout.SOUTH);
        add(root);
    }
 
    private JPanel makeMatchItem(User match, Consumer<User> onClick) {
        JPanel item = new JPanel(new BorderLayout());
        item.setBackground(new Color(18, 18, 30));
        item.setBorder(new EmptyBorder(14, 20, 14, 20));
        item.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        item.setMaximumSize(new Dimension(Integer.MAX_VALUE, 85));
 
        // Avatar
        JLabel avatar = new JLabel(match.getKelamin().equals("Perempuan") ? "👩" : "👨") {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(60, 20, 50));
                g2.fillOval(0, 0, getWidth(), getHeight());
                g2.dispose();
                super.paintComponent(g);
            }
        };
        avatar.setFont(new Font("Arial Unicode MS", Font.PLAIN, 26));
        avatar.setHorizontalAlignment(SwingConstants.CENTER);
        avatar.setPreferredSize(new Dimension(52, 52));
 
        // Info
        JPanel info = new JPanel();
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
        info.setBackground(new Color(18, 18, 30));
        info.setBorder(new EmptyBorder(0, 14, 0, 0));
 
        JLabel name = new JLabel(match.getNama());
        name.setFont(new Font("Arial Unicode MS", Font.BOLD, 14));
        name.setForeground(Color.WHITE);
 
        JLabel sub = new JLabel(match.getDomisili() + " • " + match.getUmur() + " thn");
        sub.setFont(new Font("Arial Unicode MS", Font.PLAIN, 12));
        sub.setForeground(new Color(140, 140, 170));
 
        JLabel hobi = new JLabel("<html><div style='width:230px;'>"
            + match.getHobiString()
            + "</div></html>");
        hobi.setFont(new Font("Arial Unicode MS", Font.PLAIN, 11));
        hobi.setForeground(new Color(255, 150, 170));
 
        info.add(name);
        info.add(Box.createVerticalStrut(2));
        info.add(sub);
        info.add(Box.createVerticalStrut(2));
        info.add(hobi);
 
        JLabel arrow = new JLabel("›");
        arrow.setFont(new Font("Arial Unicode MSe UI", Font.BOLD, 22));
        arrow.setForeground(new Color(255, 90, 130));
 
        item.add(avatar, BorderLayout.WEST);
        item.add(info, BorderLayout.CENTER);
        item.add(arrow, BorderLayout.EAST);
 
        item.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) { onClick.accept(match); }
            public void mouseEntered(java.awt.event.MouseEvent e) { item.setBackground(new Color(30, 28, 50)); info.setBackground(new Color(30, 28, 50)); }
            public void mouseExited(java.awt.event.MouseEvent e) { item.setBackground(new Color(18, 18, 30)); info.setBackground(new Color(18, 18, 30)); }
        });
 
        return item;
    }
 
    private JSeparator makeDivider() {
        JSeparator sep = new JSeparator();
        sep.setForeground(new Color(40, 40, 65));
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        return sep;
    }
 
    private JButton makeNavButton(String text, boolean active) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial Unicode MSe UI", Font.PLAIN, 12));
        btn.setForeground(active ? new Color(255, 90, 130) : new Color(140, 140, 170));
        btn.setBackground(new Color(24, 24, 40));
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }
 
    public JButton getBtnHome() { return btnHome; }
    public JButton getBtnChat() { return btnChat; }
    public JButton getBtnProfil() { return btnProfil; }
    public User getCurrentUser() { return currentUser; }
}
