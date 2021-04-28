package com.example.salebookapp;

import android.content.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.salebookapp.entities.Account;
import com.example.salebookapp.entities.Author;
import com.example.salebookapp.entities.Bill;
import com.example.salebookapp.entities.BillDetail;
import com.example.salebookapp.entities.Book;
import com.example.salebookapp.entities.BookType;
import com.example.salebookapp.entities.Customer;
import com.example.salebookapp.entities.Publisher;

@Database(
        entities = {Account.class, Customer.class, Bill.class,
                BillDetail.class, Book.class, BookType.class, Author.class, Publisher.class},
        version = 16,
        exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DAO dao();

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 1;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "salebook")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
