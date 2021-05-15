package com.example.salebookapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.salebookapp.entities.Account;
import com.example.salebookapp.entities.Customer;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;



public class ConfirmActivity extends AppCompatActivity {
    
    EditText edtConfirmCode;
    TextView tvemail;
    Button btnSubmit, btnResend;
    String confirmCode;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        intent = getIntent();
        Anhxa();

        tvemail.setText(intent.getExtras().getString("email"));

        sendMail();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConfirmTrue()){
                    String user = intent.getExtras().getString("email");
                    String pass = intent.getExtras().getString("pass");
                    String type = intent.getExtras().getString("type");
                    String fullName = intent.getExtras().getString("fullName");
                    String phone = intent.getExtras().getString("phone");
                    String address = intent.getExtras().getString("address");

                    Customer cus = new Customer(fullName, phone, address);

                    Account acc = new Account(user, Utils.byPass(pass), type);

                    AppDatabase.databaseWriteExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            AppDatabase.getDatabase(getApplicationContext()).dao().customerInsert(cus);
                            AppDatabase.getDatabase(getApplicationContext()).dao().accountInsert(acc);
                        }
                    });
                    Toast.makeText(ConfirmActivity.this, "Xác thực thành công", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ConfirmActivity.this, HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }else {
                    Toast.makeText(ConfirmActivity.this, "Xác thực không thành công", Toast.LENGTH_LONG).show();
                }
            }
        } );
        btnResend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtConfirmCode.setText("");
                sendMail();
//                if(isConfirmTrue()){
//                    Toast.makeText(ConfirmActivity.this, "Xác thực thành công", Toast.LENGTH_LONG).show();
//                    Intent intent = new Intent(ConfirmActivity.this, AccountFragment.class);
//                    startActivity(intent);
//                }else {
//                    Toast.makeText(ConfirmActivity.this, "Xác thực không thành công", Toast.LENGTH_LONG).show();
//                }
            }
        });
    }

    private void sendMail() {
        confirmCode = randomCode()+"";

        JavaMailAPI javaMailAPI = null;
            javaMailAPI = new JavaMailAPI(this, intent.getExtras().getString("email"),"Send code",confirmCode);
            javaMailAPI.execute();

    }

    private void Anhxa() {
        tvemail = findViewById(R.id.tv_email);
        edtConfirmCode = findViewById(R.id.edt_code);
        btnSubmit = findViewById(R.id.btn_submit);
        btnResend = findViewById(R.id.btn_resend);
    }
    private int randomCode(){
        Random ran = new Random();

        int s = (int)Math.floor(Math.random()*(999999-100000+1)+100000);
        System.out.println(s);
        return s;
    }
    private  boolean isConfirmTrue(){
        System.out.println("input: "+edtConfirmCode.getText().toString()+ edtConfirmCode.getText().toString().contains(confirmCode));
        return edtConfirmCode.getText().toString().contains(confirmCode);
    }

}