package com.example.salebookapp.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "authors")
public class Author {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "author_id")
    private int authorID;
    @ColumnInfo(name = "fullname")
    private String fullName;
    @ColumnInfo(name = "sex")
    private String sex;
    @ColumnInfo(name = "dob")
    private String dob;

    public Author(String fullName, String sex, String dob) {
        this.fullName = fullName;
        this.sex = sex;
        this.dob = dob;
    }

    //get and set
    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
