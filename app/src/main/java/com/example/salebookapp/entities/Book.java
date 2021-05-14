package com.example.salebookapp.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "books")
public class Book {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "book_id")
    private int bookID;
    @ColumnInfo(name = "book_name")
    private String bookName;
    @ColumnInfo(name = "publication_date")
    private String publicationDate;
    @ColumnInfo(name = "price")
    private double price;
    @ColumnInfo(name = "republish")
    private int republish;
    @ColumnInfo(name = "quantities")
    private int quantities;
    @ColumnInfo(name = "describe")
    private String describe;
    @ColumnInfo(name = "image")
    private String image;
    private int amount;

    public Book() {};

    public Book(String bookName, String publicationDate, double price, int republish, int quantities, String describe, String image) {
        this.bookName = bookName;
        this.publicationDate = publicationDate;
        this.price = price;
        this.republish = republish;
        this.quantities = quantities;
        this.describe = describe;
        this.image = image;
    }


    //get and set
    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRepublish() {
        return republish;
    }

    public void setRepublish(int republish) {
        this.republish = republish;
    }

    public int getQuantities() {
        return quantities;
    }

    public void setQuantities(int quantities) {
        this.quantities = quantities;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookID=" + bookID +
                ", bookName='" + bookName + '\'' +
                ", publicationDate='" + publicationDate + '\'' +
                ", price=" + price +
                ", republish=" + republish +
                ", quantities=" + quantities +
                ", describe='" + describe + '\'' +
                ", image='" + image + '\'' +
                ", amount=" + amount +
                '}';
    }
}
