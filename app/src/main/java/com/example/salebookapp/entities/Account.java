package com.example.salebookapp.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "accounts",
    foreignKeys = @ForeignKey(
            entity = Customer.class,
            parentColumns = "cus_id",
            childColumns = "acc_id",
            onDelete = CASCADE,
            onUpdate = CASCADE)
)
public class Account {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "acc_id")
    private int accID;
    @ColumnInfo(name = "username")
    private String username;
    @ColumnInfo(name = "password")
    private String password;
    @ColumnInfo(name = "acc_type")
    private String accType;

    public Account(String username, String password, String accType) {
        this.setUsername(username);
        this.setPassword(password);
        this.setAccType(accType);
    }
    //GET AND SET
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

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }
}
