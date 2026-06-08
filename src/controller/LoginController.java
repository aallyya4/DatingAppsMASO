/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.User;
import model.UserModel;
import view.LoginView;
import view.RegisterView;
import view.HomeView;
import javax.swing.*;
import java.util.List;

/**
 *
 * @author anin7
 */
public class LoginController {
    private LoginView view;
    private UserModel model;
 
    public LoginController(LoginView view) {
        this.view = view;
        this.model = new UserModel();
        bindEvents();
    }
 
    private void bindEvents() {
        view.getBtnLogin().addActionListener(e -> handleLogin());
        view.getBtnRegister().addActionListener(e -> goToRegister());
 
        view.getTxtPassword().addActionListener(e -> handleLogin());
    }
 
    private void handleLogin() {
        String username = view.getTxtUsername().getText().trim();
        String password = new String(view.getTxtPassword().getPassword()).trim();
 
        if (username.isEmpty() || password.isEmpty()) {
            view.getLblError().setText("Username dan password tidak boleh kosong.");
            return;
        }
 
        User user = model.login(username, password);
        if (user != null) {
            view.getLblError().setText(" ");
            view.dispose();
 
            // Ambil rekomendasi & buka HomeView
            List<User> reko = model.getRekomendasi(user);
            SwingUtilities.invokeLater(() -> new HomeView(user, reko).setVisible(true));
        } else {
            view.getLblError().setText("Username atau password salah.");
            view.getTxtPassword().setText("");
        }
    }
 
    private void goToRegister() {
        view.dispose();
        SwingUtilities.invokeLater(() -> new RegisterView().setVisible(true));
    }
}
