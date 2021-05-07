package com.example.salebookapp.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "bills",
        foreignKeys = @ForeignKey(
                entity = Customer.class,
                parentColumns = "cus_id",
                childColumns = "fk_cus_id",
                onDelete = CASCADE,
                onUpdate = CASCADE)
)
public class Bill {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "bill_id")
    private int billID;
    @ColumnInfo(name = "fk_cus_id")
    private int fkCusID;
    @ColumnInfo(name = "total")
    private double total;
    @ColumnInfo(name = "date_of_export")
    private String dateOfExport;

//    public Bill(int fkCusID, double total, String dateOfExport) {
//        this.fkCusID = fkCusID;
//        this.total = total;
//        this.dateOfExport = dateOfExport;
//    }

    //get and set
    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public int getFkCusID() {
        return fkCusID;
    }

    public void setFkCusID(int fkCusID) {
        this.fkCusID = fkCusID;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getDateOfExport() {
        return dateOfExport;
    }

    public void setDateOfExport(String dateOfExport) {
        this.dateOfExport = dateOfExport;
    }
}
