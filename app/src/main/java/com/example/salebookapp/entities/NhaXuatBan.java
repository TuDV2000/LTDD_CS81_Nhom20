package com.example.salebookapp.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class NhaXuatBan {
    @PrimaryKey
    private int idNXB;
    private String ten;
    private String diaChi;
    private String lienHe;

    public int getIdNXB() {
        return idNXB;
    }

    public void setIdNXB(int idNXB) {
        this.idNXB = idNXB;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getLienHe() {
        return lienHe;
    }

    public void setLienHe(String lienHe) {
        this.lienHe = lienHe;
    }
}
