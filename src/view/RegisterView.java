/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.RegisterController;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author anin7
 */
public class RegisterView extends JFrame {
    private JTextField txtNama, txtUsername, txtDomisili, txtUmur, txtNoTelepon, txtDeskripsi;
    private JPasswordField txtPassword, txtConfirmPass;
    private JComboBox<String> cmbKelamin, cmbTujuan;
    private JList<String> listHobi;
    private JButton btnDaftar, btnLogin;
    private JLabel lblError;
 
    private static final String[] HOBI_LIST = {
        "Olahraga","Musik","Membaca","Menulis","Melukis","Fotografi","Gaming","Traveling",
        "Memasak","Bersepeda","Yoga","Mendaki","Berkebun","Badminton","Tennis","Renang",
        "Memancing","Skateboard","Vlogging","Ngopi","DIY Craft","Bela Diri","Nonton Film",
        "Cosplay","Karaoke","Otomotif"
    };
 
    public RegisterView() {
        initComponents();
    }
 
    private void initComponents() {
        setTitle("MASO - Daftar Akun Baru");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(520, 700);
        setLocationRelativeTo(null);
        setResizable(false);
 
        Color bg = new Color(18, 18, 30);
        Color cardBg = new Color(28, 28, 45);
        Color accent = new Color(255, 90, 130);
        Color textColor = Color.WHITE;
        Color subText = new Color(160, 160, 190);
 
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(bg);
 
        // Header
        JPanel header = new JPanel();
        header.setBackground(bg);
        header.setBorder(new EmptyBorder(25, 0, 10, 0));
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        JLabel logo = new JLabel("💘 MASO");
        logo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        logo.setForeground(accent);
        logo.setAlignmentX(CENTER_ALIGNMENT);
        JLabel sub = new JLabel("Buat akun baru & temukan soulmate-mu");
        sub.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        sub.setForeground(subText);
        sub.setAlignmentX(CENTER_ALIGNMENT);
        header.add(logo);
        header.add(Box.createVerticalStrut(4));
        header.add(sub);
 
        // Scroll pane for form
        JPanel form = new JPanel();
        form.setLayout(new GridBagLayout());
        form.setBackground(cardBg);
        form.setBorder(new EmptyBorder(20, 28, 20, 28));
 
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;
        c.gridx = 0;
        c.insets = new Insets(4, 0, 2, 0);
 
        int row = 0;
 
        // Row helper lambda
        c.gridy = row++;
        form.add(makeLabel("Nama Lengkap", subText), c);
        c.gridy = row++;
        txtNama = makeTextField(); form.add(txtNama, c);
 
        c.gridy = row++;
        form.add(makeLabel("Username", subText), c);
        c.gridy = row++;
        txtUsername = makeTextField(); form.add(txtUsername, c);
 
        c.gridy = row++;
        form.add(makeLabel("Password", subText), c);
        c.gridy = row++;
        txtPassword = makePassField(); form.add(txtPassword, c);
 
        c.gridy = row++;
        form.add(makeLabel("Konfirmasi Password", subText), c);
        c.gridy = row++;
        txtConfirmPass = makePassField(); form.add(txtConfirmPass, c);
 
        c.gridy = row++;
        form.add(makeLabel("Domisili (Kota)", subText), c);
        c.gridy = row++;
        txtDomisili = makeTextField(); form.add(txtDomisili, c);
 
        c.gridy = row++;
        form.add(makeLabel("Usia", subText), c);
        c.gridy = row++;
        txtUmur = makeTextField(); form.add(txtUmur, c);
 
        c.gridy = row++;
        form.add(makeLabel("No. Telepon", subText), c);
        c.gridy = row++;
        txtNoTelepon = makeTextField(); form.add(txtNoTelepon, c);
 
        c.gridy = row++;
        form.add(makeLabel("Jenis Kelamin", subText), c);
        c.gridy = row++;
        cmbKelamin = new JComboBox<>(new String[]{"Laki-laki", "Perempuan"});
        styleCombo(cmbKelamin); form.add(cmbKelamin, c);
 
        c.gridy = row++;
        form.add(makeLabel("Looking For", subText), c);
        c.gridy = row++;
        cmbTujuan = new JComboBox<>(new String[]{"menikah", "fun date", "friends", "fwb"});
        styleCombo(cmbTujuan); form.add(cmbTujuan, c);
 
        c.gridy = row++;
        form.add(makeLabel("Minat / Hobi (Ctrl+klik untuk pilih lebih dari satu)", subText), c);
        c.gridy = row++;
        listHobi = new JList<>(HOBI_LIST);
        listHobi.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        listHobi.setBackground(new Color(40, 40, 62));
        listHobi.setForeground(textColor);
        listHobi.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        listHobi.setFixedCellHeight(28);
        listHobi.setSelectionBackground(new Color(255, 90, 130, 180));
        listHobi.setSelectionForeground(Color.WHITE);
        JScrollPane hobiscroll = new JScrollPane(listHobi);
        hobiscroll.setPreferredSize(new Dimension(0, 120));
        hobiscroll.setBorder(new LineBorder(new Color(80, 80, 120), 1));
        form.add(hobiscroll, c);
 
        c.gridy = row++;
        form.add(makeLabel("Deskripsi Diri (Opsional)", subText), c);
        c.gridy = row++;
        txtDeskripsi = makeTextField(); form.add(txtDeskripsi, c);
 
        c.gridy = row++;
        lblError = new JLabel(" ");
        lblError.setForeground(new Color(255, 80, 80));
        lblError.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblError.setHorizontalAlignment(SwingConstants.CENTER);
        form.add(lblError, c);
 
        c.gridy = row++;
        btnDaftar = makePrimaryButton("Daftar Sekarang", accent);
        form.add(btnDaftar, c);
 
        c.gridy = row++;
        btnLogin = makeSecondaryButton("Sudah punya akun? Masuk", accent, cardBg);
        form.add(btnLogin, c);
 
        JScrollPane scroll = new JScrollPane(form);
        scroll.setBackground(bg);
        scroll.setBorder(BorderFactory.createEmptyBorder(0, 25, 25, 25));
        scroll.getVerticalScrollBar().setUnitIncrement(16);
 
        root.add(header, BorderLayout.NORTH);
        root.add(scroll, BorderLayout.CENTER);
        add(root);
 
        new RegisterController(this);
    }
 
    // ============ Getters ============
    public JTextField getTxtNama() { return txtNama; }
    public JTextField getTxtUsername() { return txtUsername; }
    public JPasswordField getTxtPassword() { return txtPassword; }
    public JPasswordField getTxtConfirmPass() { return txtConfirmPass; }
    public JTextField getTxtDomisili() { return txtDomisili; }
    public JTextField getTxtUmur() { return txtUmur; }
    public JTextField getTxtNoTelepon() { return txtNoTelepon; }
    public JTextField getTxtDeskripsi() { return txtDeskripsi; }
    public JComboBox<String> getCmbKelamin() { return cmbKelamin; }
    public JComboBox<String> getCmbTujuan() { return cmbTujuan; }
    public JList<String> getListHobi() { return listHobi; }
    public JButton getBtnDaftar() { return btnDaftar; }
    public JButton getBtnLogin() { return btnLogin; }
    public JLabel getLblError() { return lblError; }
 
    // ============ Style Helpers ============
    private JLabel makeLabel(String text, Color color) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        l.setForeground(color);
        return l;
    }
 
    private JTextField makeTextField() {
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
 
    private JPasswordField makePassField() {
        JPasswordField f = new JPasswordField();
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
 
    private JButton makeSecondaryButton(String text, Color accent, Color bg) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btn.setForeground(accent);
        btn.setBackground(bg);
        btn.setBorder(new LineBorder(new Color(255, 90, 130, 80), 1, true));
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(0, 40));
        return btn;
    }
}
