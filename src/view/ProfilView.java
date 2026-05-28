/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.AboutMeController;
import model.User;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author anin7
 */
public class ProfilView extends JFrame {
    private JTextField txtNama, txtDomisili, txtUmur, txtNoTelepon;
    private JTextArea txtDeskripsi;
    private JComboBox<String> cmbKelamin, cmbTujuan;
    private JList<String> listHobi;
    private JButton btnSimpan, btnLogout;
    private JButton btnHome, btnChat, btnProfil;
    private JLabel lblUsername, lblFoto;
    private User currentUser;
 
    private static final String[] HOBI_LIST = {
        "Olahraga","Musik","Membaca","Menulis","Melukis","Fotografi","Gaming","Traveling",
        "Memasak","Bersepeda","Yoga","Mendaki","Berkebun","Badminton","Tennis","Renang",
        "Memancing","Skateboard","Vlogging","Ngopi","DIY Craft","Bela Diri","Nonton Film",
        "Cosplay","Karaoke","Otomotif"
    };
 
    public ProfilView(User user) {
        this.currentUser = user;
        initComponents();
        populateData();
    }
 
    private void initComponents() {
        setTitle("MASO - Profilku");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(420, 720);
        setLocationRelativeTo(null);
        setResizable(false);
 
        Color bg = new Color(18, 18, 30);
        Color cardBg = new Color(28, 28, 45);
        Color accent = new Color(255, 90, 130);
 
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(bg);
 
        // Top bar
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(new Color(24, 24, 40));
        topBar.setBorder(new EmptyBorder(14, 20, 14, 20));
        JLabel title = new JLabel("👤 Profilku");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setForeground(accent);
        topBar.add(title, BorderLayout.WEST);
 
        btnLogout = new JButton("Keluar");
        btnLogout.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        btnLogout.setForeground(new Color(255, 100, 100));
        btnLogout.setBackground(new Color(24, 24, 40));
        btnLogout.setBorder(new LineBorder(new Color(255, 100, 100, 80), 1, true));
        btnLogout.setFocusPainted(false);
        btnLogout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        topBar.add(btnLogout, BorderLayout.EAST);
 
        // Profile avatar + username header
        JPanel profileHeader = new JPanel();
        profileHeader.setLayout(new BoxLayout(profileHeader, BoxLayout.Y_AXIS));
        profileHeader.setBackground(cardBg);
        profileHeader.setBorder(new EmptyBorder(20, 0, 20, 0));
 
        lblFoto = new JLabel() {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(80, 20, 60));
                g2.fillOval(0, 0, getWidth(), getHeight());
                g2.dispose();
                g.setFont(new Font("Segoe UI", Font.PLAIN, 40));
                g.setColor(Color.WHITE);
                String icon = currentUser.getKelamin().equals("Perempuan") ? "👩" : "👨";
                FontMetrics fm = g.getFontMetrics();
                g.drawString(icon, (getWidth() - fm.stringWidth(icon)) / 2, getHeight() / 2 + fm.getAscent() / 2 - 8);
            }
        };
        lblFoto.setPreferredSize(new Dimension(80, 80));
        lblFoto.setMaximumSize(new Dimension(80, 80));
        lblFoto.setMinimumSize(new Dimension(80, 80));
        lblFoto.setAlignmentX(CENTER_ALIGNMENT);
 
        lblUsername = new JLabel("@" + currentUser.getUsername());
        lblUsername.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblUsername.setForeground(new Color(160, 160, 190));
        lblUsername.setAlignmentX(CENTER_ALIGNMENT);
 
        profileHeader.add(lblFoto);
        profileHeader.add(Box.createVerticalStrut(8));
        profileHeader.add(lblUsername);
 
        // Form
        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(bg);
        form.setBorder(new EmptyBorder(16, 20, 16, 20));
 
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0; c.gridx = 0;
        c.insets = new Insets(4, 0, 2, 0);
 
        int row = 0;
 
        c.gridy = row++; form.add(label("Nama Lengkap"), c);
        c.gridy = row++;
        txtNama = field(); form.add(txtNama, c);
 
        c.gridy = row++; form.add(label("Domisili"), c);
        c.gridy = row++;
        txtDomisili = field(); form.add(txtDomisili, c);
 
        c.gridy = row++; form.add(label("Usia"), c);
        c.gridy = row++;
        txtUmur = field(); form.add(txtUmur, c);
 
        c.gridy = row++; form.add(label("No. Telepon"), c);
        c.gridy = row++;
        txtNoTelepon = field(); form.add(txtNoTelepon, c);
 
        c.gridy = row++; form.add(label("Jenis Kelamin"), c);
        c.gridy = row++;
        cmbKelamin = new JComboBox<>(new String[]{"Laki-laki", "Perempuan"});
        styleCombo(cmbKelamin); form.add(cmbKelamin, c);
 
        c.gridy = row++; form.add(label("Looking For"), c);
        c.gridy = row++;
        cmbTujuan = new JComboBox<>(new String[]{"menikah", "fun date", "friends", "fwb"});
        styleCombo(cmbTujuan); form.add(cmbTujuan, c);
 
        c.gridy = row++; form.add(label("Minat / Hobi (Ctrl+klik untuk multi-pilih)"), c);
        c.gridy = row++;
        listHobi = new JList<>(HOBI_LIST);
        listHobi.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        listHobi.setBackground(new Color(40, 40, 62));
        listHobi.setForeground(Color.WHITE);
        listHobi.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        listHobi.setFixedCellHeight(26);
        listHobi.setSelectionBackground(new Color(255, 90, 130, 180));
        listHobi.setSelectionForeground(Color.WHITE);
        JScrollPane hs = new JScrollPane(listHobi);
        hs.setPreferredSize(new Dimension(0, 110));
        hs.setBorder(new LineBorder(new Color(80, 80, 120), 1));
        form.add(hs, c);
 
        c.gridy = row++; form.add(label("Deskripsi Diri"), c);
        c.gridy = row++;
        txtDeskripsi = new JTextArea(3, 20);
        txtDeskripsi.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtDeskripsi.setForeground(Color.WHITE);
        txtDeskripsi.setBackground(new Color(40, 40, 62));
        txtDeskripsi.setCaretColor(Color.WHITE);
        txtDeskripsi.setLineWrap(true);
        txtDeskripsi.setWrapStyleWord(true);
        txtDeskripsi.setBorder(new CompoundBorder(
            new LineBorder(new Color(80, 80, 120), 1),
            new EmptyBorder(8, 12, 8, 12)
        ));
        JScrollPane ds = new JScrollPane(txtDeskripsi);
        ds.setBorder(null); form.add(ds, c);
 
        c.gridy = row++;
        c.insets = new Insets(16, 0, 4, 0);
        btnSimpan = makePrimaryButton("Simpan Perubahan", accent);
        form.add(btnSimpan, c);
 
        JScrollPane scroll = new JScrollPane(form);
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
        btnHome = navBtn("🏠 Home", false);
        btnChat = navBtn("💬 Chat", false);
        btnProfil = navBtn("👤 Profil", true);
        navPanel.add(btnHome);
        navPanel.add(btnChat);
        navPanel.add(btnProfil);
 
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(bg);
        centerPanel.add(profileHeader, BorderLayout.NORTH);
        centerPanel.add(scroll, BorderLayout.CENTER);
 
        root.add(topBar, BorderLayout.NORTH);
        root.add(centerPanel, BorderLayout.CENTER);
        root.add(navPanel, BorderLayout.SOUTH);
 
        add(root);
        new AboutMeController(this, currentUser);
    }
 
    private void populateData() {
        txtNama.setText(currentUser.getNama());
        txtDomisili.setText(currentUser.getDomisili());
        txtUmur.setText(String.valueOf(currentUser.getUmur()));
        txtNoTelepon.setText(currentUser.getNoTelepon() != null ? currentUser.getNoTelepon() : "");
        txtDeskripsi.setText(currentUser.getDeskripsi() != null ? currentUser.getDeskripsi() : "");
        cmbKelamin.setSelectedItem(currentUser.getKelamin());
        cmbTujuan.setSelectedItem(currentUser.getTujuan());
 
        // Select hobi di list
        List<String> userHobi = currentUser.getHobi();
        List<Integer> selectedIndices = new java.util.ArrayList<>();
        for (int i = 0; i < HOBI_LIST.length; i++) {
            for (String h : userHobi) {
                if (HOBI_LIST[i].equalsIgnoreCase(h.trim())) {
                    selectedIndices.add(i);
                    break;
                }
            }
        }
        int[] indices = selectedIndices.stream().mapToInt(Integer::intValue).toArray();
        listHobi.setSelectedIndices(indices);
    }
 
    private JLabel label(String text) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        l.setForeground(new Color(160, 160, 190));
        return l;
    }
 
    private JTextField field() {
        JTextField f = new JTextField();
        f.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        f.setForeground(Color.WHITE);
        f.setBackground(new Color(40, 40, 62));
        f.setCaretColor(Color.WHITE);
        f.setBorder(new CompoundBorder(
            new LineBorder(new Color(80, 80, 120), 1, true),
            new EmptyBorder(9, 12, 9, 12)
        ));
        return f;
    }
 
    private void styleCombo(JComboBox<String> cb) {
        cb.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        cb.setBackground(new Color(40, 40, 62));
        cb.setForeground(Color.WHITE);
    }
 
    private JButton makePrimaryButton(String text, Color accent) {
        JButton btn = new JButton(text) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getModel().isPressed() ? new Color(200, 50, 90) :
                            getModel().isRollover() ? new Color(240, 80, 120) : accent);
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
        btn.setPreferredSize(new Dimension(0, 46));
        return btn;
    }
 
    private JButton navBtn(String text, boolean active) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        btn.setForeground(active ? new Color(255, 90, 130) : new Color(140, 140, 170));
        btn.setBackground(new Color(24, 24, 40));
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }
 
    // Getters
    public JTextField getTxtNama() { return txtNama; }
    public JTextField getTxtDomisili() { return txtDomisili; }
    public JTextField getTxtUmur() { return txtUmur; }
    public JTextField getTxtNoTelepon() { return txtNoTelepon; }
    public JTextArea getTxtDeskripsi() { return txtDeskripsi; }
    public JComboBox<String> getCmbKelamin() { return cmbKelamin; }
    public JComboBox<String> getCmbTujuan() { return cmbTujuan; }
    public JList<String> getListHobi() { return listHobi; }
    public JButton getBtnSimpan() { return btnSimpan; }
    public JButton getBtnLogout() { return btnLogout; }
    public JButton getBtnHome() { return btnHome; }
    public JButton getBtnChat() { return btnChat; }
    public JButton getBtnProfil() { return btnProfil; }
    public User getCurrentUser() { return currentUser; }
    public void setCurrentUser(User user) { this.currentUser = user; }
}
