/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.User;
import model.UserModel;
import view.LoginView;
import view.RegisterView;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anin7
 */
public class RegisterController {
    private RegisterView view;
    private UserModel model;
 
    public RegisterController(RegisterView view) {
        this.view = view;
        this.model = new UserModel();
        bindEvents();
    }
 
    private void bindEvents() {
        view.getBtnDaftar().addActionListener(e -> handleRegister());
        view.getBtnLogin().addActionListener(e -> goToLogin());
    }
 
    private void handleRegister() {
        String nama = view.getTxtNama().getText().trim();
        String username = view.getTxtUsername().getText().trim();
        String password = new String(view.getTxtPassword().getPassword()).trim();
        String confirm = new String(view.getTxtConfirmPass().getPassword()).trim();
        String domisili = view.getTxtDomisili().getText().trim();
        String umurStr = view.getTxtUmur().getText().trim();
        String noTelp = view.getTxtNoTelepon().getText().trim();
        String deskripsi = view.getTxtDeskripsi().getText().trim();
        String kelamin = (String) view.getCmbKelamin().getSelectedItem();
        String tujuan = (String) view.getCmbTujuan().getSelectedItem();
        List<String> hobiSelected = new ArrayList<>(view.getListHobi().getSelectedValuesList());
 
        // Validasi
        if (nama.isEmpty() || username.isEmpty() || password.isEmpty() || domisili.isEmpty() || umurStr.isEmpty()) {
            view.getLblError().setText("Semua field wajib diisi!");
            return;
        }
        if (!password.equals(confirm)) {
            view.getLblError().setText("Password dan konfirmasi tidak cocok.");
            return;
        }
        if (password.length() < 6) {
            view.getLblError().setText("Password minimal 6 karakter.");
            return;
        }
        if (hobiSelected.isEmpty()) {
            view.getLblError().setText("Pilih setidaknya 1 hobi/minat.");
            return;
        }
        int umur;
        try {
            umur = Integer.parseInt(umurStr);
            if (umur < 17 || umur > 100) {
                view.getLblError().setText("Usia harus antara 17-100 tahun.");
                return;
            }
        } catch (NumberFormatException ex) {
            view.getLblError().setText("Usia harus berupa angka.");
            return;
        }
        if (model.isUsernameExist(username)) {
            view.getLblError().setText("Username sudah dipakai, pilih yang lain.");
            return;
        }
 
        User user = new User();
        user.setNama(nama);
        user.setUsername(username);
        user.setPassword(password);
        user.setDomisili(domisili);
        user.setUmur(umur);
        user.setKelamin(kelamin);
        user.setHobi(hobiSelected);
        user.setTujuan(tujuan);
        user.setDeskripsi(deskripsi.isEmpty() ? "Hai! Aku " + nama + " dari " + domisili + ". Yuk kenalan!" : deskripsi);
        user.setNoTelepon(noTelp);
 
        boolean ok = model.register(user);
        if (ok) {
            JOptionPane.showMessageDialog(view,
                "Akun berhasil dibuat! Silakan login.",
                "Registrasi Berhasil 🎉",
                JOptionPane.INFORMATION_MESSAGE);
            goToLogin();
        } else {
            view.getLblError().setText("Gagal membuat akun. Coba lagi.");
        }
    }
 
    private void goToLogin() {
        view.dispose();
        SwingUtilities.invokeLater(() -> new LoginView().setVisible(true));
    }
}
