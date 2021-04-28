package com.example.salebookapp.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "books",
        foreignKeys = {
            @ForeignKey(
                    entity = BookType.class,
                    parentColumns = "type_id",
                    childColumns = "fk_book_type_id",
                    onDelete = CASCADE,
                    onUpdate = CASCADE),
            @ForeignKey(
                    entity = Author.class,
                    parentColumns = "author_id",
                    childColumns = "fk_author_id",
                    onDelete = CASCADE,
                    onUpdate = CASCADE),
            @ForeignKey(
                    entity = Publisher.class,
                    parentColumns = "publisher_id",
                    childColumns = "fk_publisher_id",
                    onDelete = CASCADE,
                    onUpdate = CASCADE)
        }
)
public class Book {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "book_id")
    private int bookID;
    @ColumnInfo(name = "fk_book_type_id")
    private int fkBookTypeID;
    @ColumnInfo(name = "fk_author_id")
    private int fkAuthorID;
    @ColumnInfo(name = "fk_publisher_id")
    private int fkPublisherID;
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
    @ColumnInfo(name = "image")
    private String image;


    public Book(int fkBookTypeID, int fkAuthorID, int fkPublisherID, String bookName,
                String publicationDate, double price, int republish, int quantities, String image) {
        this.fkBookTypeID = fkBookTypeID;
        this.fkAuthorID = fkAuthorID;
        this.fkPublisherID = fkPublisherID;
        this.bookName = bookName;
        this.setPublicationDate(publicationDate);
        this.price = price;
        this.republish = republish;
        this.quantities = quantities;
        this.setImage(image);
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
}
