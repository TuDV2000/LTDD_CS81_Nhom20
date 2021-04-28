package com.example.salebookapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.example.salebookapp.entities.Account;

@Dao
public interface DAO {

    //AccountDao
    @Insert
    public void accountInsert(Account account);

    //
}