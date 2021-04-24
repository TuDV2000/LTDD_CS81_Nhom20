package com.example.salebookapp.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class LoaiSach {
    @PrimaryKey
    private int idLoai;
    private String tenLoai;
    private String moTa;

    public int getIdLoai() {
        return idLoai;
    }

    public void setIdLoai(int idLoai) {
        this.idLoai = idLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
