package com.example.salebookapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salebookapp.entities.Bill;
import com.example.salebookapp.entities.Book;

import java.util.List;

public class BillFragment extends Fragment {

    private RecyclerView rcvBill;
    private View mView;
    private HomeActivity mainActivity;
    private BillAdapter billAdapter;
    private List<Bill> billList;

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_bill, container, false);

        setUp();

        return mView;
    }

    public void setUp() {
        rcvBill = mView.findViewById(R.id.rcv_bill);
    }

    @Override
    public void onResume() {
        super.onResume();

        mainActivity = (HomeActivity) getActivity();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mainActivity);
        rcvBill.setLayoutManager(linearLayoutManager);

        billAdapter = new BillAdapter();

        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                billList = AppDatabase.getDatabase(getContext())
                        .dao().getAllBill();
            }
        });

        (new Handler()).postDelayed(new Runnable() {
            @Override
            public void run() {
                billAdapter.setData(billList, new BillAdapter.IClickConfirmListener() {
                    @Override
                    public void onClickConfirm(Bill bill, BillAdapter.BillViewHolder holder) {
                        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
                            @Override
                            public void run() {
                                if (bill.getStatus() == 0) {
                                    AppDatabase.getDatabase(getContext())
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
}
