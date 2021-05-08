package com.example.salebookapp;

import com.example.salebookapp.entities.Book;

public class Cart {
    private Book[] listBooks;
    private int itemCount;
    private double totalPrice;
    private int capacity;

    public Cart() {
        listBooks = new Book[capacity];
        itemCount = 0;
        totalPrice = 0.0;
        capacity = 5;
    }

    public void addToCart(Book book, int quantity) {

        totalPrice += (book.getPrice() * quantity);
        listBooks[itemCount] = book;
        itemCount += 1;

        if(itemCount == capacity) {
            increaseSize();
        }
    }

    private void increaseSize() {
        Book[] temp = new Book[capacity + 3];

        for (int i = 0; i < capacity; i++) {
            temp[i] = listBooks[i];
        }
        listBooks = temp;
        temp = null;
        capacity = listBooks.length;
    }

    public Book[] getListBooks() {
        return listBooks;
    }

    public void setCart(Book[] listBooks) {
        this.listBooks = listBooks;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
