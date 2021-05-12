package com.example.salebookapp;

import com.example.salebookapp.entities.Book;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CartTest {

    Cart cart;
    Book book;

    @Before
    public void setUp() throws Exception {
        cart = new Cart();
        book = new Book();
        book.setBookID(1);
        book.setPrice(100);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addToCart() {
        System.out.println("item before addCart: " + book);
        cart.addToCart(book);
        assertNotNull(cart);
        System.out.println("item in Cart: " + cart.getCart().get(book.getBookID()));
        assertEquals(cart.getCart().get(book.getBookID()).getBookID(), book.getBookID());
    }

    @Test
    public void removeFromCart() {
        cart.addToCart(book);
        cart.addToCart(book);
        cart.addToCart(book);
        int amount = cart.getCart().get(book.getBookID()).getAmount();
        System.out.println("Số lượng ban đầu trong cart: "+amount);
        cart.removeFromCart(book);
        System.out.println("Số lượng sau khi remove: "+cart.getCart().get(book.getBookID()).getAmount());
        assertEquals(cart.getCart().get(book.getBookID()).getAmount(), amount - 1);

    }

    @Test
    public void testAddToCartFail() {
        try
        {
            book.setBookID(new Integer(null));
            cart.addToCart(book);
        }catch (NumberFormatException e){
            assertEquals(NumberFormatException.class,e.getClass());
        }

    }

    @Test
    public void getTotalPrice() {

        System.out.println("Tổng tiền đúng: 300");
        cart.addToCart(book);
        cart.addToCart(book);
        cart.addToCart(book);
        double totalPriceCart = cart.getTotalPrice();
        System.out.println("Tổng tiền sau khi thêm vào cart: " + totalPriceCart);
        assertTrue(totalPriceCart==300);
    }



    @Test
    public void getItemCartById() {
        System.out.println("Id: " + book.getBookID());
        cart.addToCart(book);
        Book a = cart.getCart().get(book.getBookID());
        System.out.println("item: " + a);
        assertEquals(book,a);
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getCartItemAll() {
        List<Book> cartadds= new ArrayList<>();
        Book a = new Book();
        a.setBookID(1);
        a.setPrice(234);
        Book b = new Book();
        b.setBookID(2);
        b.setPrice(234);
        Book c = new Book();
        c.setBookID(3);
        c.setPrice(234);

        cartadds.add(a);
        cartadds.add(b);
        cartadds.add(c);

        cart.addToCart(book);
        cartadds.forEach(cart::addToCart);

        cartadds.forEach(i -> {
            Book temp  = cart.getCart().get(i.getBookID());
            System.out.println("ID add: " + i.getBookID());
            System.out.println("ID in cart: " + temp.getBookID());
            assertEquals(i.getBookID(),temp.getBookID());
        });
    }
}