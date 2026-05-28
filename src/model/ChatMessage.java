/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author anin7
 */
public class ChatMessage {
    private int id;
    private int senderId;
    private int receiverId;
    private String pesan;
    private LocalDateTime waktu;
 
    public ChatMessage(int senderId, int receiverId, String pesan) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.pesan = pesan;
        this.waktu = LocalDateTime.now();
    }
 
    public ChatMessage(int id, int senderId, int receiverId, String pesan, LocalDateTime waktu) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.pesan = pesan;
        this.waktu = waktu;
    }
 
    public int getId() { return id; }
    public int getSenderId() { return senderId; }
    public int getReceiverId() { return receiverId; }
    public String getPesan() { return pesan; }
    public LocalDateTime getWaktu() { return waktu; }
 
    public String getWaktuFormatted() {
        return waktu.format(DateTimeFormatter.ofPattern("HH:mm"));
    }
}
