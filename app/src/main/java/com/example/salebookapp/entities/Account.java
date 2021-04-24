package com.example.salebookapp.entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Account {
    @PrimaryKey
    private int accID;
    private String username;
    private String password;
    private String typeAcc;

    public int getAccID() {
        return accID;
    }

    public void setAccID(int accID) {
        this.accID = accID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTypeAcc() {
        return typeAcc;
    }

    public void setTypeAcc(String typeAcc) {
        this.typeAcc = typeAcc;
    }
}
