package com.example.salebookapp.entities;

import java.util.Date;

public class Book {
    private int bookID;
    private int fkBookTypeID;
    private int fkAuthorID;
    private int fkPublisherID;
    private String bookName;
    private Date PublicationDate;
    private double price;
    private int republish;
    private int quantities;


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

    public Date getPublicationDate() {
        return PublicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        PublicationDate = publicationDate;
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

    public int getFkBookTypeID() {
        return fkBookTypeID;
    }

    public void setFkBookTypeID(int fkBookTypeID) {
        this.fkBookTypeID = fkBookTypeID;
    }

    public int getFkAuthorID() {
        return fkAuthorID;
    }

    public void setFkAuthorID(int fkAuthorID) {
        this.fkAuthorID = fkAuthorID;
    }

    public int getFkPublisherID() {
        return fkPublisherID;
    }

    public void setFkPublisherID(int fkPublisherID) {
        this.fkPublisherID = fkPublisherID;
    }
}
