package com.example.salebookapp.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "customers")
public class Customer {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cus_id")
    private int cusID;
    @ColumnInfo(name = "fullname")
    private String fullName;
    @ColumnInfo(name = "phone_number")
    private String phoneNumber;
    @ColumnInfo(name = "address")
    private String address;

    public Customer(String fullName, String phoneNumber, String address) {
        this.setFullName(fullName);
        this.setPhoneNumber(phoneNumber);
        this.setAddress(address);
    }
    //GET AND SET
    public int getCusID() {
        return cusID;
    }

    public void setCusID(int cusID) {
        this.cusID = cusID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
