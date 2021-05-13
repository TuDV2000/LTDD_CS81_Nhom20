package com.example.salebookapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.example.salebookapp.entities.Bill;
import com.example.salebookapp.entities.Book;

import java.util.List;

public class OrderHistoryActivity extends AppCompatActivity {

    private RecyclerView rcvOrderHistory;
    private BillAdapter billAdapter;
    List<Bill> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        setUp();

    }

    void setUp() {
        rcvOrderHistory = findViewById(R.id.rcv_order_history);
    }

    @Override
    protected void onResume() {
        super.onResume();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvOrderHistory.setLayoutManager(linearLayoutManager);

        billAdapter = new BillAdapter();

        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                int idCus = AppDatabase.getDatabase(getApplicationContext())
                        .dao().getCusById(Utils.accLogin.getAccID()).getCusID();

                list = AppDatabase.getDatabase(getApplicationContext())
                        .dao().getBillByCusId(idCus);
            }
        });
        (new Handler()).postDelayed(new Runnable() {
            @Override
            public void run() {
                billAdapter.setData(list, new BillAdapter.IClickConfirmListener() {
                    @Override
                    public void onClickConfirm(Bill bill, BillAdapter.BillViewHolder holder) {
                        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
                            @Override
                            public void run() {
                                if (bill.getStatus() == 1) {
                                    AppDatabase.getDatabase(getApplicationContext())
                                            .dao().updateStatusBill(bill.getBillID(), 2);

                                }
                            }
                        });
                        onResume();
                    }
                });
            }
        },200);

        rcvOrderHistory.setAdapter(billAdapter);
    }
}