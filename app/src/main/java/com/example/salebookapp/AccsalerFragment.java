package com.example.salebookapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.salebookapp.entities.Customer;

public class AccsalerFragment extends Fragment {

    TextView tvAccountName, tvAccountEmail;
    Button btnStore, btnSalerBill, btnLogout;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_accsaler, container, false);
        setUp();
        setData();
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.accLogin = null;
                getActivity().finish();
                startActivity(new Intent(getContext(),HomeActivity.class));
            }
        });

        return view;
    }
    private void setUp() {
        tvAccountEmail = view.findViewById(R.id.tv_accountEmail);
        tvAccountName = view.findViewById(R.id.tv_accountName);
        btnStore = view.findViewById(R.id.btn_store);
        btnSalerBill = view.findViewById(R.id.btn_salerBill);
        btnLogout = view.findViewById(R.id.btn_logOut);
    }
    private void setData(){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                Customer customer = AppDatabase.getDatabase(getContext())
                        .dao().getAccByCusId(Utils.accLogin.getAccID()).get(0).customer;
                tvAccountName.setText(customer.getFullName());
                tvAccountEmail.setText(Utils.accLogin.getUsername());
            }
        });
    }
}
