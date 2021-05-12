package com.example.salebookapp.entities;

import com.example.salebookapp.HomeActivity;
import com.example.salebookapp.Utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAccID() {

    }

    @Test
    public void addAccount(){
        HomeActivity a = new HomeActivity();
        a.getApplicationContext();
    }

    @Test
    public void testEncodePass(){
        String a = "aa6ec9bf967afd962bf57cda5c588104";
        String pass = "adbc";
        System.out.println("PassEncode mong muốn: " + a);
        System.out.println("PassEncode: "+ Utils.byPass(pass));
        assertEquals(a,Utils.byPass(pass));
    }
}