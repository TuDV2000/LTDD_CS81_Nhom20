package com.example.salebookapp.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "billdetails",
        foreignKeys = {
            @ForeignKey(
                entity = Bill.class,
                parentColumns = "bill_id",
                childColumns = "fk_bill_id",
                onDelete = CASCADE,
                onUpdate = CASCADE),
            @ForeignKey(
                entity = Book.class,
                parentColumns = "book_id",
                childColumns = "fk_book_id",
                onDelete = CASCADE,
                onUpdate = CASCADE)
        },
        primaryKeys = {"fk_bill_id", "fk_book_id"}
)
public class BillDetail {
    @ColumnInfo(name = "fk_bill_id")
    private int fkBillID;
    @ColumnInfo(name = "fk_book_id")
    private int fkBookID;
    @ColumnInfo(name = "price")
    private double price;
    @ColumnInfo(name = "bought_quatities")
    private int bought_quatities;

    public BillDetail(int fkBillID, int fkBookID, double price, int bought_quatities) {
        this.fkBillID = fkBillID;
        this.fkBookID = fkBookID;
        this.price = price;
        this.bought_quatities = bought_quatities;
    }

    //get and set
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

    public int getBought_quatities() {
        return bought_quatities;
    }

    public void setBought_quatities(int bought_quatities) {
        this.bought_quatities = bought_quatities;
    }
}
