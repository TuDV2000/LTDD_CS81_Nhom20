package com.example.salebookapp.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Customer {
    @PrimaryKey(autoGenerate = true)
    public int cusID;
    public String fullName;
    public String phoneNumber;
    public String address;

    public Customer(String fullName, String phoneNumber, String address) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}
