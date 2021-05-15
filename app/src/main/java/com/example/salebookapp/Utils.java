package com.example.salebookapp;

import android.content.Context;

import com.example.salebookapp.entities.Account;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {
    public static final String EMAIL ="laptrinhdidong321@gmail.com";
    public static final String PASSWORD = "Qweqwe123@";
    public static Account accLogin = null;
    public static Context context;
    public static Cart cart = new Cart();
    public static int fragmentCurrentId = 1;

    public static String byPass(String pass){
        String plaintext = pass;
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(plaintext.getBytes());
            byte[] digest = m.digest();
           return convertByteToHex1(digest);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }


    }
    public static String convertByteToHex1(byte[] data) {
        BigInteger number = new BigInteger(1, data);
        String hashtext = number.toString(16);
        // Now we need to zero pad it if you actually want the full 32 chars.
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }
        return hashtext;
    }
}
