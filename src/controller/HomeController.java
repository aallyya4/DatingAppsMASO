/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.User;
import model.UserModel;
import view.HomeView;
import view.ChatListView;
import view.ChatRoomView;
import view.ProfilView;
import javax.swing.*;
import java.util.List;

/**
 *
 * @author anin7
 */
public class HomeController {
    private HomeView view;
    private User currentUser;
    private List<User> rekomendasi;
    private UserModel model;
 
    public HomeController(HomeView view, User currentUser, List<User> rekomendasi) {
        this.view = view;
        this.currentUser = currentUser;
        this.rekomendasi = rekomendasi;
        this.model = new UserModel();
        bindEvents();
    }
 
    private void bindEvents() {
        view.getBtnInterest().addActionListener(e -> handleSwipe(true));
        view.getBtnNotInterest().addActionListener(e -> handleSwipe(false));
 
        view.getBtnChat().addActionListener(e -> openChatList());
        view.getBtnProfil().addActionListener(e -> openProfil());
        view.getBtnHome().addActionListener(e -> {  });
    }
 
    private void handleSwipe(boolean interest) {
        int idx = view.getCurrentIndex();
        if (idx >= rekomendasi.size()) { view.showNoProfile(); return; }
 
        User target = rekomendasi.get(idx);
        model.swipe(currentUser.getId(), target.getId(), interest);
 
        if (interest) {
            // Cek apakah ini jadi match
            List<User> matches = model.getMatches(currentUser.getId());
            boolean newMatch = matches.stream().anyMatch(u -> u.getId() == target.getId());
            if (newMatch) {
                SwingUtilities.invokeLater(() -> {
                    int choice = JOptionPane.showConfirmDialog(view,
                        "🎉 Kamu match dengan " + target.getNama() + "!\nMau langsung chat sekarang?",
                        "It's a Match! 💘",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);
                    if (choice == JOptionPane.YES_OPTION) {
                        view.dispose();

                        List<User> matchesBaru = model.getMatches(currentUser.getId());

                        final ChatListView[] chatListRef = new ChatListView[1];

                        chatListRef[0] = new ChatListView(currentUser, matches, (matchUser) -> {
                            chatListRef[0].setVisible(false);
                            new ChatRoomView(currentUser, matchUser, chatListRef[0]).setVisible(true);
                        });

                        chatListRef[0].getBtnHome().addActionListener(ev -> {
                            chatListRef[0].dispose();
                            List<User> reko = model.getRekomendasi(currentUser);
                            new HomeView(currentUser, reko).setVisible(true);
                        });

                        chatListRef[0].getBtnProfil().addActionListener(ev -> {
                            chatListRef[0].dispose();
                            new ProfilView(currentUser).setVisible(true);
                        });

                        chatListRef[0].setVisible(false);
                        new ChatRoomView(currentUser, target, chatListRef[0]).setVisible(true);
                    }
                });
            }
        }
 
        int nextIdx = idx + 1;
        view.setCurrentIndex(nextIdx);
        if (nextIdx < rekomendasi.size()) {
            view.showCard(rekomendasi.get(nextIdx));
        } else {
            view.showNoProfile();
        }
    }
 
    private void openChatList() {
        List<User> matches = model.getMatches(currentUser.getId());
        view.dispose();

        SwingUtilities.invokeLater(() -> {
            ChatListView[] chatListRef = new ChatListView[1];

            chatListRef[0] = new ChatListView(currentUser, matches, (matchUser) -> {
                chatListRef[0].setVisible(false);

                ChatRoomView room = new ChatRoomView(
                    currentUser,
                    matchUser,
                    chatListRef[0]
                );

                room.setVisible(true);
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
 
    private void openProfil() {
        view.dispose();
        SwingUtilities.invokeLater(() -> new ProfilView(currentUser).setVisible(true));
    }
}
