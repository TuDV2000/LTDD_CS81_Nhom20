package com.example.salebookapp.entities;

public class BillDetail {
    private int fkBillID;
    private int fkBookID;
    private double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getFkBillID() {
        return fkBillID;
    }

    public void setFkBillID(int fkBillID) {
        this.fkBillID = fkBillID;
    }

    public int getFkBookID() {
        return fkBookID;
    }

    public void setFkBookID(int fkBookID) {
        this.fkBookID = fkBookID;
    }
}
