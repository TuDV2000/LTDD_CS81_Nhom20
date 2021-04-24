package com.example.salebookapp.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TacGia {
    @PrimaryKey
    private int idTG;
    private String hoTen;
    private String gioiTinh;
    private String ngaySinh;

    public int getIdTG() {
        return idTG;
    }

    public void setIdTG(int idTG) {
        this.idTG = idTG;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }
}
