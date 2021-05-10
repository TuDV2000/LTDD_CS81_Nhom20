package com.example.salebookapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.salebookapp.entities.Account;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.example.salebookapp.entities.Customer;

import java.util.List;

import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;


public class AccbuyerFragment extends Fragment {

    Button btnEdit, btnLogout, btnSaveEdit, btnBuyerBill;
    EditText edtEditAddress, edtEditPhone;
    String oldAddress, oldPhone;
    TextView tvAccountName, tvAccountEmail;
    View view;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_accbuyer, container, false);

        setUp();

        setData();

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oldAddress = edtEditAddress.getText().toString();
                oldPhone = edtEditPhone.getText().toString();
                edtEditAddress.setEnabled(true);
                edtEditPhone.setEnabled(true);
                btnEdit.setEnabled(false);
                btnSaveEdit.setAlpha(1);
            }
        });
        btnSaveEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newAddress = edtEditAddress.getText().toString();
                String newPhone = edtEditPhone.getText().toString();
                if (addValidation(newAddress, newPhone)){
                    int cusID = Utils.accLogin.getAccID();
                    AppDatabase.databaseWriteExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            AppDatabase.getDatabase(getContext()).dao().changeCusProfile(newAddress,newPhone,cusID);
                        }
                    });
                    edtEditAddress.setEnabled(false);
                    edtEditPhone.setEnabled(false);
                    btnSaveEdit.setAlpha(0);
                    btnEdit.setEnabled(true);
                    }
                }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.accLogin = null;
                getActivity().finish();
                startActivity(new Intent(getContext(),HomeActivity.class));
            }
        });

        btnBuyerBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), OrderHistoryActivity.class));
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    private void setUp() {
        btnEdit = view.findViewById(R.id.btn_editAcc);
        btnLogout = view.findViewById(R.id.btn_logOut);
        btnSaveEdit = view.findViewById(R.id.btn_saveEdit);
        btnBuyerBill = view.findViewById(R.id.btn_buyerBill);
        edtEditAddress = view.findViewById(R.id.edt_addressEdit);
        edtEditPhone = view.findViewById(R.id.edt_phoneEdit);
        tvAccountEmail = view.findViewById(R.id.tv_accountEmail);
        tvAccountName = view.findViewById(R.id.tv_accountName);

    }
    private boolean addValidation(String newAddress, String newPhone){
        if(newAddress.length()== 0 || newPhone.length() == 0){
            Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            System.out.println("================Vui lòng nhập đầy đủ thông tin=============");
            return false;
        }
        if (newPhone.length() <= 8 || newPhone.length() >= 13){
            Toast.makeText(getContext(), "Vui lòng nhập đúng số điện thoại", Toast.LENGTH_SHORT).show();
            System.out.println("================Vui lòng nhập đúng số điện thoại=============");
            return false;
        }
        if (newAddress.equals(oldAddress)){
            Toast.makeText(getContext(), "Bạn không thay đổi số điện thoại", Toast.LENGTH_SHORT).show();
            System.out.println("================Bạn không thay đổi số điện thoại=============");
            return false;
        }
        if(newPhone.equals(oldPhone)){
            Toast.makeText(getContext(), "Bạn không thay đổi địa chỉ", Toast.LENGTH_SHORT).show();
            System.out.println("================Bạn không thay đổi địa chỉ=============");
            return false;
        }
        return true;
    }
    private void setData(){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                Customer customer = AppDatabase.getDatabase(getContext())
                        .dao().getCusById(Utils.accLogin.getAccID());

                tvAccountName.setText(customer.getFullName());
                tvAccountEmail.setText(Utils.accLogin.getUsername());
                edtEditAddress.setText(customer.getAddress());
                edtEditPhone.setText(customer.getPhoneNumber());

            }
        });
    }



}
