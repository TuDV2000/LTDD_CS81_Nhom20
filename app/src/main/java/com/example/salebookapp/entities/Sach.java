package com.example.salebookapp.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Sach {
    @PrimaryKey
    private int idSach;
    private int idLoaiSach, idTG, idNXB;
    private String tenSach;
    private Date ngayXuatBan;
    private double donGia;
    private int lanXuatBan;
    private int soLuongTon;

    public int getIdSach() {
        return idSach;
    }

    public void setIdSach(int idSach) {
        this.idSach = idSach;
    }

    public int getIdLoaiSach() {
        return idLoaiSach;
    }

    public void setIdLoaiSach(int idLoaiSach) {
        this.idLoaiSach = idLoaiSach;
    }

    public int getIdTG() {
        return idTG;
    }

    public void setIdTG(int idTG) {
        this.idTG = idTG;
    }

    public int getIdNXB() {
        return idNXB;
    }

    public void setIdNXB(int idNXB) {
        this.idNXB = idNXB;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public Date getNgayXuatBan() {
        return ngayXuatBan;
    }

    public void setNgayXuatBan(Date ngayXuatBan) {
        this.ngayXuatBan = ngayXuatBan;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public int getLanXuatBan() {
        return lanXuatBan;
    }

    public void setLanXuatBan(int lanXuatBan) {
        this.lanXuatBan = lanXuatBan;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }
}
