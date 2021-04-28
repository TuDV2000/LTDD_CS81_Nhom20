package com.example.salebookapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.salebookapp.entities.Account;

import java.util.List;

@Dao
public interface DAO {

    //AccountDao
    @Insert
    public void accountInsert(Account account);
    @Delete
    public void accountDelete(Account account);
    @Query("select * from Account")
    public List<Account> getAllAccount();

    //
}
