/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.HomeController;
import model.User;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.List;

/**
 *
 * @author anin7
 */
public class HomeView extends BaseView {
    private JPanel cardPanel;
    private JButton btnInterest, btnNotInterest;
    private JLabel lblNama, lblUsia, lblDomisili, lblKelamin, lblTujuan, lblHobi, lblDeskripsi;
    private JLabel lblFoto, lblNoProfile, lblSkorCocok;
    private JButton btnChat, btnProfil, btnHome;
    private List<User> rekomendasi;
    private int currentIndex = 0;
    private User currentUser;
 
    public HomeView(User currentUser, List<User> rekomendasi) {
        super("MASO - Home");
        this.currentUser = currentUser;
        this.rekomendasi = rekomendasi;
        initComponents();
        if (!rekomendasi.isEmpty()) showCard(rekomendasi.get(0));
        else showNoProfile();
    }
    
    @Override
    protected void initComponents() {
 
        Color bg = new Color(18, 18, 30);
        Color accent = new Color(255, 90, 130);
 
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(bg);
 
        // TOP BAR
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(new Color(24, 24, 40));
        topBar.setBorder(new EmptyBorder(12, 20, 12, 20));
        JLabel lblAppName = new JLabel("💘 MASO");
        lblAppName.setFont(new Font("Arial Unicode MS", Font.BOLD, 18));
        lblAppName.setForeground(accent);
        JLabel lblGreet = new JLabel("Hai, " + currentUser.getNama().split(" ")[0] + "!");
        lblGreet.setFont(new Font("Arial Unicode MS", Font.PLAIN, 13));
        lblGreet.setForeground(new Color(180, 180, 200));
        topBar.add(lblAppName, BorderLayout.WEST);
        topBar.add(lblGreet, BorderLayout.EAST);
 
        // CARD PANEL
        cardPanel = new JPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setBackground(new Color(28, 28, 45));
        cardPanel.setBorder(new CompoundBorder(
            new EmptyBorder(0, 16, 0, 16),
            new CompoundBorder(new LineBorder(new Color(255, 90, 130, 60), 1, true),
                               new EmptyBorder(0, 0, 0, 0))
        ));
 
        // Foto placeholder
        lblFoto = new JLabel() {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, new Color(80, 20, 60), getWidth(), getHeight(), new Color(30, 10, 50));
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.setColor(new Color(255, 255, 255, 40));
                g2.setFont(new Font("Arial Unicode MS", Font.PLAIN, 60));
                FontMetrics fm = g2.getFontMetrics();
                String icon = getText();
                g2.drawString(icon, (getWidth() - fm.stringWidth(icon)) / 2, getHeight() / 2 + fm.getAscent() / 2 - 10);
                g2.dispose();
            }
        };
        lblFoto.setText("👤");
        lblFoto.setPreferredSize(new Dimension(388, 220));
        lblFoto.setMaximumSize(new Dimension(Integer.MAX_VALUE, 220));
        lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
 
        // Info panel inside card
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(new Color(28, 28, 45));
        infoPanel.setBorder(new EmptyBorder(16, 20, 16, 20));
 
        lblSkorCocok = new JLabel();
        lblSkorCocok.setFont(new Font("Arial Unicode MS", Font.BOLD, 11));
        lblSkorCocok.setForeground(new Color(255, 180, 100));
        lblSkorCocok.setAlignmentX(LEFT_ALIGNMENT);
 
        lblNama = makeInfoLabel("", 22, Font.BOLD, Color.WHITE);
        lblUsia = makeInfoLabel("", 13, Font.PLAIN, new Color(170, 170, 200));
        lblKelamin = makeInfoLabel("", 13, Font.PLAIN, new Color(170, 170, 200));
        lblDomisili = makeInfoLabel("", 13, Font.PLAIN, new Color(170, 170, 200));
        lblTujuan = makeInfoLabel("", 13, Font.PLAIN, new Color(170, 170, 200));
        lblHobi = makeInfoLabel("", 13, Font.PLAIN, new Color(255, 180, 200));
        lblDeskripsi = new JLabel();
        lblDeskripsi.setFont(new Font("Arial Unicode MS", Font.ITALIC, 13));
        lblDeskripsi.setForeground(new Color(190, 190, 210));
        lblDeskripsi.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        lblDeskripsi.setBorder(new CompoundBorder(
            new MatteBorder(1, 0, 0, 0, new Color(60, 60, 90)),
            new EmptyBorder(10, 0, 0, 0)
        ));
 
        infoPanel.add(lblSkorCocok);
        infoPanel.add(Box.createVerticalStrut(4));
        infoPanel.add(lblNama);
        infoPanel.add(Box.createVerticalStrut(4));
        infoPanel.add(lblUsia);
        infoPanel.add(lblKelamin);
        infoPanel.add(lblDomisili);
        infoPanel.add(lblTujuan);
        infoPanel.add(Box.createVerticalStrut(6));
        infoPanel.add(lblHobi);
        infoPanel.add(Box.createVerticalStrut(8));
        infoPanel.add(lblDeskripsi);
 
        cardPanel.add(lblFoto);
        cardPanel.add(infoPanel);
 
        lblNoProfile = new JLabel("<html><center>😔<br><br>Tidak ada rekomendasi profil<br><small>Tidak ada user dengan hobi yang sama<br>atau semua sudah kamu swipe</small></center></html>");
        
        lblNoProfile.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
        lblNoProfile.setForeground(new Color(160, 160, 180));
        lblNoProfile.setHorizontalAlignment(SwingConstants.CENTER);
        lblNoProfile.setVisible(false);
 
        // ACTION BUTTONS
        JPanel actionPanel = new JPanel(new GridLayout(1, 2, 16, 0));
        actionPanel.setBackground(bg);
        actionPanel.setBorder(new EmptyBorder(14, 20, 10, 20));
 
        btnNotInterest = makeActionButton("✕  Tidak", new Color(80, 80, 100), new Color(100, 100, 130));
        btnInterest = makeActionButton("♥  Suka!", accent, new Color(220, 60, 100));
        actionPanel.add(btnNotInterest);
        actionPanel.add(btnInterest);
 
        // BOTTOM NAV
        JPanel navPanel = new JPanel(new GridLayout(1, 3));
        navPanel.setBackground(new Color(24, 24, 40));
        navPanel.setBorder(new CompoundBorder(
            new MatteBorder(1, 0, 0, 0, new Color(50, 50, 80)),
            new EmptyBorder(8, 0, 8, 0)
        ));
        btnHome = makeNavButton("🏠 Home", true);
        btnChat = makeNavButton("💬 Chat", false);
        btnProfil = makeNavButton("👤 Profil", false);
        navPanel.add(btnHome);
        navPanel.add(btnChat);
        navPanel.add(btnProfil);
 
        JPanel centerPanel = new JPanel(new CardLayout());
        centerPanel.setBackground(bg);
        centerPanel.add(cardPanel, "CARD");
        centerPanel.add(lblNoProfile, "NOPROFILE");
 
        JPanel scrollWrapper = new JPanel(new BorderLayout());
        scrollWrapper.setBackground(bg);
        scrollWrapper.setBorder(new EmptyBorder(12, 0, 0, 0));
        scrollWrapper.add(centerPanel, BorderLayout.CENTER);
 
        root.add(topBar, BorderLayout.NORTH);
        root.add(scrollWrapper, BorderLayout.CENTER);
 
        JPanel bottomSection = new JPanel(new BorderLayout());
        bottomSection.setBackground(bg);
        bottomSection.add(actionPanel, BorderLayout.NORTH);
        bottomSection.add(navPanel, BorderLayout.SOUTH);
        root.add(bottomSection, BorderLayout.SOUTH);
 
        add(root);
        new HomeController(this, currentUser, rekomendasi);
    }
 
    public void showCard(User user) {
        CardLayout cl = (CardLayout) cardPanel.getParent().getLayout();
        cl.show(cardPanel.getParent(), "CARD");

        int skor = currentUser.hitungKecocokan(user);

        lblFoto.setText(user.getKelamin().equals("Perempuan") ? "👩" : "👨");
        lblSkorCocok.setText("🎯 " + skor + " kriteria cocok");
        lblNama.setText(user.getNama());
        lblUsia.setText("🎂 " + user.getUmur() + " tahun");
        lblKelamin.setText("⚥ " + user.getKelamin());
        lblDomisili.setText("📍 " + user.getDomisili());
        lblTujuan.setText("🎯 Looking for: " + user.getTujuan());
        lblHobi.setText("✨ " + user.getHobiString());

        String desc = user.getDeskripsi();
        if (desc != null && desc.length() > 100) desc = desc.substring(0, 100) + "...";
        lblDeskripsi.setText("<html><i>" + (desc != null ? desc : "") + "</i></html>");

        cardPanel.revalidate();
        cardPanel.repaint();
    }
 
    public void showNoProfile() {
        CardLayout cl = (CardLayout) cardPanel.getParent().getLayout();
        cl.show(cardPanel.getParent(), "NOPROFILE");

        btnInterest.setEnabled(false);
        btnNotInterest.setEnabled(false);
    }
 
    private JLabel makeInfoLabel(String text, int size, int style, Color color) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("Arial Unicode MS", style, size));
        l.setForeground(color);
        l.setAlignmentX(LEFT_ALIGNMENT);
        return l;
    }
 
    private JButton makeActionButton(String text, Color bg, Color hover) {
        JButton btn = new JButton(text) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getModel().isPressed() || getModel().isRollover() ? hover : bg);
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
        btn.setPreferredSize(new Dimension(0, 48));
        return btn;
    }
 
    // Getters
    public JButton getBtnInterest() { return btnInterest; }
    public JButton getBtnNotInterest() { return btnNotInterest; }
    public JButton getBtnChat() { return btnChat; }
    public JButton getBtnProfil() { return btnProfil; }
    public List<User> getRekomendasi() { return rekomendasi; }
    public int getCurrentIndex() { return currentIndex; }
    public void setCurrentIndex(int i) { currentIndex = i; }
    public JButton getBtnHome() { return btnHome; }
}