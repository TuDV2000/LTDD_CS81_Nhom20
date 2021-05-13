package com.example.salebookapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.salebookapp.entities.Bill;

import java.util.List;

public class BillManagerActivity extends AppCompatActivity {

    private RecyclerView rcvBill;
    private BillAdapter billAdapter;
    List<Bill> listBill = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_manager);

        setUp();
        //setData();


    }

    public void setUp() {
        rcvBill = findViewById(R.id.rcv_bill);
    }

    @Override
    protected void onResume() {
        super.onResume();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        billAdapter = new BillAdapter();

        rcvBill.setLayoutManager(linearLayoutManager);

        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                listBill = AppDatabase.getDatabase(getApplicationContext())
                        .dao().getAllBill();
            }
        });
        (new Handler()).postDelayed(new Runnable() {
            @Override
            public void run() {
                billAdapter.setData(listBill, new BillAdapter.IClickConfirmListener() {
                    @Override
                    public void onClickConfirm(Bill bill, BillAdapter.BillViewHolder holder) {
                        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
                            @Override
                            public void run() {
                                if (bill.getStatus() == 0) {
                                    AppDatabase.getDatabase(getApplicationContext())
                                            .dao().updateStatusBill(bill.getBillID(), 1);
                                }
                            }
                        });
                        onResume();
                    }
                });
            }
        }, 200);

        rcvBill.setAdapter(billAdapter);
    }

//    public void setData() {
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        billAdapter = new BillAdapter();
//
//        rcvBill.setLayoutManager(linearLayoutManager);
//
//        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                listBill = AppDatabase.getDatabase(getApplicationContext())
//                        .dao().getAllBill();
//
//                billAdapter.setData(listBill, new BillAdapter.IClickConfirmListener() {
//                    @Override
//                    public void onClickConfirm(Bill bill, BillAdapter.BillViewHolder holder) {
//                        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
//                            @Override
//                            public void run() {
//                                if (bill.getStatus() == 0) {
//                                    AppDatabase.getDatabase(getApplicationContext())
//                                            .dao().updateStatusBill(bill.getBillID(), 1);
//                                }
//                            }
//                        });
//                    }
//                });
//            }
//        });
//
//        rcvBill.setAdapter(billAdapter);
//    }
}