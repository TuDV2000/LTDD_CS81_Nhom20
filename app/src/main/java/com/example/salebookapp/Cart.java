package com.example.salebookapp;

import com.example.salebookapp.entities.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Cart {

    private Map<Integer,Book> cart ;
    private double totalPrice;

    public Cart() {
        cart = new HashMap<>();
        totalPrice = 0.0;
    }

    public void addToCart(Book book){
        if(cart.containsKey(book.getBookID())){
            Book item = cart.get(book.getBookID());
            item.setAmount(item.getAmount()+1);
            totalPrice += book.getPrice();
            cart.put(book.getBookID(),item);
            return;
        }
        book.setAmount(1);
        totalPrice += book.getPrice();
        cart.put(book.getBookID(),book);
    }



    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Map<Integer, Book> getCart() {
        return cart;
    }

    public List<Book> getCartItemAll(){
        List<Book> a = new ArrayList<>();
        for(Book b: cart.values()) a.add(b);
        return a;
    }


}
