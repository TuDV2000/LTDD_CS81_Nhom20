package com.example.salebookapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.salebookapp.entities.Account;
import com.example.salebookapp.entities.Customer;

@Database(entities = {Account.class, Customer.class}, version = 16, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DAO dao();
}
