/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anin7
 */
public class User {
    private int id;
    private String nama;
    private String username;
    private String password;
    private String domisili;
    private int umur;
    private String kelamin;
    private List<String> hobi;
    private String tujuan;
    private String deskripsi;
    private String noTelepon;
    private String fotoProfil; // path atau base64, bisa diisi default
 
    public User() {
        this.hobi = new ArrayList<>();
    }
 
    public User(int id, String nama, String username, String password,
                String domisili, int umur, String kelamin,
                List<String> hobi, String tujuan, String deskripsi) {
        this.id = id;
        this.nama = nama;
        this.username = username;
        this.password = password;
        this.domisili = domisili;
        this.umur = umur;
        this.kelamin = kelamin;
        this.hobi = hobi != null ? hobi : new ArrayList<>();
        this.tujuan = tujuan;
        this.deskripsi = deskripsi;
        this.noTelepon = "";
        this.fotoProfil = "";
    }
 
    // ============ Getters & Setters ============
 
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
 
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
 
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
 
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
 
    public String getDomisili() { return domisili; }
    public void setDomisili(String domisili) { this.domisili = domisili; }
 
    public int getUmur() { return umur; }
    public void setUmur(int umur) { this.umur = umur; }
 
    public String getKelamin() { return kelamin; }
    public void setKelamin(String kelamin) { this.kelamin = kelamin; }
 
    public List<String> getHobi() { return hobi; }
    public void setHobi(List<String> hobi) { this.hobi = hobi; }
 
    public String getTujuan() { return tujuan; }
    public void setTujuan(String tujuan) { this.tujuan = tujuan; }
 
    public String getDeskripsi() { return deskripsi; }
    public void setDeskripsi(String deskripsi) { this.deskripsi = deskripsi; }
 
    public String getNoTelepon() { return noTelepon; }
    public void setNoTelepon(String noTelepon) { this.noTelepon = noTelepon; }
 
    public String getFotoProfil() { return fotoProfil; }
    public void setFotoProfil(String fotoProfil) { this.fotoProfil = fotoProfil; }
 
    /**
     * Menghitung jumlah kriteria yang sama antara user ini dengan user lain.
     * Kriteria: domisili, kelamin, tujuan, dan setiap hobi.
     */
    public int hitungKecocokan(User other) {
        int skor = 0;
        if (this.domisili != null && this.domisili.equalsIgnoreCase(other.domisili)) skor++;
        if (this.kelamin != null && this.kelamin.equalsIgnoreCase(other.kelamin)) skor++;
        if (this.tujuan != null && this.tujuan.equalsIgnoreCase(other.tujuan)) skor++;
        for (String h : this.hobi) {
            for (String oh : other.hobi) {
                if (h.trim().equalsIgnoreCase(oh.trim())) { skor++; break; }
            }
        }
        return skor;
    }
 
    public String getHobiString() {
        return String.join(", ", hobi);
    }
 
    @Override
    public String toString() {
        return "User{id=" + id + ", nama='" + nama + "', username='" + username + "'}";
    }
}
