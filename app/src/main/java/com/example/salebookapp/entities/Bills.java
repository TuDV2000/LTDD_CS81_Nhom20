package com.example.salebookapp.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "bills")
public class Bills {
    @PrimaryKey(autoGenerate = true)
    private int billID;
    private int fkCusID;
    private double total;
    private Date dateOfExport;

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

    public Date getDateOfExport() {
        return dateOfExport;
    }

    public void setDateOfExport(Date dateOfExport) {
        this.dateOfExport = dateOfExport;
    }
}
