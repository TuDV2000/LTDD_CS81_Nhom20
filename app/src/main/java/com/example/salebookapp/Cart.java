package com.example.salebookapp;

import com.example.salebookapp.entities.Book;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Book> listBooks;
    private double totalPrice;

    public Cart() {
        listBooks = new ArrayList<Book>();
        totalPrice = 0.0;
    }

    public void addToCart(Book book, int quantity){
        for (Book b : listBooks) {
            if (b.getBookID() == book.getBookID()) {
                b.setAmount(b.getAmount() + quantity);
                return;
            }
        }
        listBooks.add(book);
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

}
