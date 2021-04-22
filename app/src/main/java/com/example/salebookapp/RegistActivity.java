package com.example.salebookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class RegistActivity extends AppCompatActivity {

    EditText edtFullName, edtUserName, edtPassword,edtCellPhone,edtAddress;
    TextView txtChangeLogin;
    CheckBox cbAgree;
    Button btnCreate;
    Spinner spRole;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        setup();
        setItem();

        cbAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    btnCreate.setEnabled(false);
                } else {
                    btnCreate.setEnabled(true);
                }
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoTen = edtFullName.getText().toString();
                String username = edtUserName.getText().toString();
                String password = edtPassword.getText().toString();
                String soDT = edtCellPhone.getText().toString();
                String diaChi = edtAddress.getText().toString();
                String loai_acc = spRole.getSelectedItem().toString();

                boolean checkAddAcc = db.addAccount(username, password, loai_acc);
                boolean checkAddKH = db.addKhachHang(hoTen, soDT, diaChi);

                if (checkAddAcc && checkAddKH) {
                    Intent intent = new Intent(RegistActivity.this, HomeActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(RegistActivity.this, "Regist Account Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setItem() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(RegistActivity.this,
                R.array.option, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spRole.setAdapter(adapter);
    }

    private void setup() {
        edtFullName = findViewById(R.id.edt_fullname);
        edtUserName = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        edtCellPhone = findViewById(R.id.edt_cellphone);
        edtAddress = findViewById(R.id.edt_address);
        txtChangeLogin = findViewById(R.id.txt_change_login);
        cbAgree = findViewById(R.id.cb_agree);
        btnCreate = findViewById(R.id.btn_create);
        spRole = findViewById(R.id.sp_role);
    }

    public void onClick(View v) {
        Intent intent = new Intent(RegistActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}