/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import database.Connection;
import java.sql.*;
import java.util.*;

/**
 *
 * @author anin7
 */
public class UserModel {
    // ================= AUTH =================
 
    public User login(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement ps = Connection.getInstance().prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapUser(rs);
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }
 
    public boolean isUsernameExist(String username) {
        String sql = "SELECT id FROM users WHERE username = ?";
        try (PreparedStatement ps = Connection.getInstance().prepareStatement(sql)) {
            ps.setString(1, username);
            return ps.executeQuery().next();
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }
 
    public boolean register(User user) {
        String sql = "INSERT INTO users (nama, username, password, domisili, umur, kelamin, hobi, tujuan, deskripsi, no_telepon, foto_profil) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement ps = Connection.getInstance().prepareStatement(sql)) {
            ps.setString(1, user.getNama());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getDomisili());
            ps.setInt(5, user.getUmur());
            ps.setString(6, user.getKelamin());
            ps.setString(7, user.getHobiString());
            ps.setString(8, user.getTujuan());
            ps.setString(9, user.getDeskripsi());
            ps.setString(10, user.getNoTelepon());
            ps.setString(11, user.getFotoProfil());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }
 
    // ================= UPDATE PROFIL =================
 
    public boolean updateUser(User user) {
        String sql = "UPDATE users SET nama=?, domisili=?, umur=?, kelamin=?, hobi=?, tujuan=?, deskripsi=?, no_telepon=?, foto_profil=? WHERE id=?";
        try (PreparedStatement ps = Connection.getInstance().prepareStatement(sql)) {
            ps.setString(1, user.getNama());
            ps.setString(2, user.getDomisili());
            ps.setInt(3, user.getUmur());
            ps.setString(4, user.getKelamin());
            ps.setString(5, user.getHobiString());
            ps.setString(6, user.getTujuan());
            ps.setString(7, user.getDeskripsi());
            ps.setString(8, user.getNoTelepon());
            ps.setString(9, user.getFotoProfil());
            ps.setInt(10, user.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }
 
    // ================= REKOMENDASI =================
 
    public List<User> getRekomendasi(User currentUser) {
        List<User> semua = getAllUsers();
        List<User> hasil = new ArrayList<>();
        for (User u : semua) {
            if (u.getId() == currentUser.getId()) continue;
            if (sudahDiSwipe(currentUser.getId(), u.getId())) continue;
            hasil.add(u);
        }
        // Sort by score descending
        hasil.sort((a, b) -> b.hitungKecocokan(currentUser) - a.hitungKecocokan(currentUser));
        return hasil;
    }
 
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Statement st = Connection.getInstance().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) list.add(mapUser(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
 
    public User getUserById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (PreparedStatement ps = Connection.getInstance().prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapUser(rs);
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }
 
    // ================= SWIPE / MATCH =================
 
    public void swipe(int userId, int targetId, boolean interest) {
        String sql = "INSERT IGNORE INTO swipes (user_id, target_id, interest) VALUES (?,?,?)";
        try (PreparedStatement ps = Connection.getInstance().prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, targetId);
            ps.setInt(3, interest ? 1 : 0);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
 
        // Cek apakah keduanya sudah saling interest → buat match
        if (interest && isMutualInterest(userId, targetId)) {
            createMatch(userId, targetId);
        }
    }
 
    public boolean sudahDiSwipe(int userId, int targetId) {
        String sql = "SELECT id FROM swipes WHERE user_id=? AND target_id=?";
        try (PreparedStatement ps = Connection.getInstance().prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, targetId);
            return ps.executeQuery().next();
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }
 
    private boolean isMutualInterest(int userId, int targetId) {
        String sql = "SELECT id FROM swipes WHERE user_id=? AND target_id=? AND interest=1";
        try (PreparedStatement ps = Connection.getInstance().prepareStatement(sql)) {
            ps.setInt(1, targetId);
            ps.setInt(2, userId);
            return ps.executeQuery().next();
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }
 
    private void createMatch(int userId, int targetId) {
        int u1 = Math.min(userId, targetId);
        int u2 = Math.max(userId, targetId);
        String sql = "INSERT OR IGNORE INTO matches (user1_id, user2_id) VALUES (?,?)";
        try (PreparedStatement ps = Connection.getInstance().prepareStatement(sql)) {
            ps.setInt(1, u1);
            ps.setInt(2, u2);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
 
    public List<User> getMatches(int userId) {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM matches WHERE user1_id=? OR user2_id=?";
        try (PreparedStatement ps = Connection.getInstance().prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int otherId = rs.getInt("user1_id") == userId
                        ? rs.getInt("user2_id") : rs.getInt("user1_id");
                User u = getUserById(otherId);
                if (u != null) list.add(u);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
 
    // ================= CHAT =================
 
    public void sendMessage(ChatMessage msg) {
        String sql = "INSERT INTO messages (sender_id, receiver_id, pesan, waktu) VALUES (?,?,?,?)";
        try (PreparedStatement ps = Connection.getInstance().prepareStatement(sql)) {
            ps.setInt(1, msg.getSenderId());
            ps.setInt(2, msg.getReceiverId());
            ps.setString(3, msg.getPesan());
            ps.setString(4, msg.getWaktu().toString());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
 
    public List<ChatMessage> getMessages(int userId, int otherId) {
        List<ChatMessage> list = new ArrayList<>();
        String sql = "SELECT * FROM messages WHERE (sender_id=? AND receiver_id=?) OR (sender_id=? AND receiver_id=?) ORDER BY waktu ASC";
        try (PreparedStatement ps = Connection.getInstance().prepareStatement(sql)) {
            ps.setInt(1, userId); ps.setInt(2, otherId);
            ps.setInt(3, otherId); ps.setInt(4, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ChatMessage(
                        rs.getInt("id"),
                        rs.getInt("sender_id"),
                        rs.getInt("receiver_id"),
                        rs.getString("pesan"),
                        java.time.LocalDateTime.parse(rs.getString("waktu"))
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
 
    // ================= HELPER =================
 
    private User mapUser(ResultSet rs) throws SQLException {
        User u = new User();
        u.setId(rs.getInt("id"));
        u.setNama(rs.getString("nama"));
        u.setUsername(rs.getString("username"));
        u.setPassword(rs.getString("password"));
        u.setDomisili(rs.getString("domisili"));
        u.setUmur(rs.getInt("umur"));
        u.setKelamin(rs.getString("kelamin"));
        String hobiStr = rs.getString("hobi");
        if (hobiStr != null && !hobiStr.isEmpty()) {
            List<String> hobiList = new ArrayList<>();
            for (String h : hobiStr.split(",")) hobiList.add(h.trim());
            u.setHobi(hobiList);
        }
        u.setTujuan(rs.getString("tujuan"));
        u.setDeskripsi(rs.getString("deskripsi"));
        try { u.setNoTelepon(rs.getString("no_telepon")); } catch (SQLException ignored) {}
        try { u.setFotoProfil(rs.getString("foto_profil")); } catch (SQLException ignored) {}
        return u;
    }
}
