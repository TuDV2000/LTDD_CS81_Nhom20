package com.example.salebookapp.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ChiTietHoaDon {
    @PrimaryKey
    private int idHD;
    @PrimaryKey
    private int idSach;
    private double donGia;

    public int getIdHD() {
        return idHD;
    }

    public void setIdHD(int idHD) {
        this.idHD = idHD;
    }

    public int getIdSach() {
        return idSach;
    }

    public void setIdSach(int idSach) {
        this.idSach = idSach;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }
}
