package com.example.salebookapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.salebookapp.entities.Bill;
import com.example.salebookapp.entities.Book;

import java.util.List;

public class OrderHistoryActivity extends AppCompatActivity {

    private RecyclerView rcvOrderHistory;
    private BillAdapter billAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        setUp();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvOrderHistory.setLayoutManager(linearLayoutManager);

        billAdapter = new BillAdapter();

        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                int idCus = AppDatabase.getDatabase(getApplicationContext())
                        .dao().getCusById(Utils.accLogin.getAccID()).getCusID();

                List<Bill> list = AppDatabase.getDatabase(getApplicationContext())
                        .dao().getBillByCusId(idCus);

                billAdapter.setData(list);
            }
        });

        rcvOrderHistory.setAdapter(billAdapter);
    }

    void setUp() {
        rcvOrderHistory = findViewById(R.id.rcv_order_history);
    }
}