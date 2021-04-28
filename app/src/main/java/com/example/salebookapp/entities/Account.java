package com.example.salebookapp.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Account {
    @PrimaryKey(autoGenerate = true)
    public int accID;
//    public int cusID;
    public String username;
    public String password;
    public String accType;

    public Account(String username, String password, String accType) {
        this.username = username;
        this.password = password;
        this.accType = accType;
    }
}
