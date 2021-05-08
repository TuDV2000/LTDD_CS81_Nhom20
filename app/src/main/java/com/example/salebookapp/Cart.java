package com.example.salebookapp;

import com.example.salebookapp.entities.Book;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Book> listBooks;
    private int bookCount;
    private double totalPrice;

    public Cart() {
        listBooks = new ArrayList<>();
        totalPrice = 0.0;
    }

    public boolean addToCart(Book book, int quantity) {
        if (quantity != 0) {
            totalPrice += (book.getPrice() * quantity);
            listBooks.add(book);

            return true;
        }
        return false;
    }



    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Book> getListBooks() {
        return listBooks;
    }

    public int getBookCount() {
        return bookCount;
    }

    public void setBookCount(int bookCount) {
        this.bookCount = bookCount;
    }
}
