/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.User;
import model.UserModel;
import view.HomeView;
import view.LoginView;
import view.ChatListView;
import view.ChatRoomView;
import view.ProfilView;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anin7
 */
public class AboutMeController {
    private ProfilView view;
    private User currentUser;
    private UserModel model;
 
    public AboutMeController(ProfilView view, User currentUser) {
        this.view = view;
        this.currentUser = currentUser;
        this.model = new UserModel();
        bindEvents();
    }
 
    private void bindEvents() {
        view.getBtnSimpan().addActionListener(e -> handleSimpan());
        view.getBtnLogout().addActionListener(e -> handleLogout());
        view.getBtnHome().addActionListener(e -> goHome());
        view.getBtnChat().addActionListener(e -> goChat());
    }
 
    private void handleSimpan() {
        String nama = view.getTxtNama().getText().trim();
        String domisili = view.getTxtDomisili().getText().trim();
        String umurStr = view.getTxtUmur().getText().trim();
        String noTelp = view.getTxtNoTelepon().getText().trim();
        String deskripsi = view.getTxtDeskripsi().getText().trim();
        String kelamin = (String) view.getCmbKelamin().getSelectedItem();
        String tujuan = (String) view.getCmbTujuan().getSelectedItem();
        List<String> hobi = new ArrayList<>(view.getListHobi().getSelectedValuesList());
 
        if (nama.isEmpty() || domisili.isEmpty() || umurStr.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Nama, domisili, dan usia tidak boleh kosong.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (hobi.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Pilih setidaknya 1 hobi.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int umur;
        try {
            umur = Integer.parseInt(umurStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Usia harus berupa angka.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
 
        currentUser.setNama(nama);
        currentUser.setDomisili(domisili);
        currentUser.setUmur(umur);
        currentUser.setNoTelepon(noTelp);
        currentUser.setDeskripsi(deskripsi);
        currentUser.setKelamin(kelamin);
        currentUser.setTujuan(tujuan);
        currentUser.setHobi(hobi);
 
        boolean ok = model.updateUser(currentUser);
        if (ok) {
            view.setCurrentUser(currentUser);
            JOptionPane.showMessageDialog(view, "Profil berhasil diperbarui! ✅", "Sukses", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(view, "Gagal menyimpan perubahan.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
 
    private void handleLogout() {
        int confirm = JOptionPane.showConfirmDialog(view,
            "Yakin ingin keluar dari akun?", "Konfirmasi Logout",
            JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            view.dispose();
            SwingUtilities.invokeLater(() -> new LoginView().setVisible(true));
        }
    }
 
    private void goHome() {
        view.dispose();
        List<User> reko = model.getRekomendasi(currentUser);
        SwingUtilities.invokeLater(() -> new HomeView(currentUser, reko).setVisible(true));
    }
 
    private void goChat() {
        view.dispose();
        List<User> matches = model.getMatches(currentUser.getId());

        SwingUtilities.invokeLater(() -> {
            final ChatListView[] chatListRef = new ChatListView[1];

            chatListRef[0] = new ChatListView(currentUser, matches, (matchUser) -> {
                chatListRef[0].setVisible(false);
                new ChatRoomView(currentUser, matchUser, chatListRef[0]).setVisible(true);
            });

            chatListRef[0].getBtnHome().addActionListener(e -> {
                chatListRef[0].dispose();
                List<User> reko = model.getRekomendasi(currentUser);
                SwingUtilities.invokeLater(() -> new HomeView(currentUser, reko).setVisible(true));
            });

            chatListRef[0].getBtnProfil().addActionListener(e -> {
                chatListRef[0].dispose();
                SwingUtilities.invokeLater(() -> new ProfilView(currentUser).setVisible(true));
            });

            chatListRef[0].setVisible(true);
        });
    }
}
