package com.example.salebookapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.salebookapp.entities.Account;
import com.example.salebookapp.entities.Customer;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ConfirmActivity extends AppCompatActivity {

    EditText edtConfirmCode, edtResult;
    Button btnSubmit, btnResend;
    String confirmCode;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        intent = getIntent();
        ActionBar actionBar = getSupportActionBar();

        actionBar.setTitle("CONFIRM");
        actionBar.setDisplayHomeAsUpEnabled(true);
        Anhxa();

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
                    Account acc = new Account(user, pass, type);

                    try {
                        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
                            @Override
                            public void run() {
                            AppDatabase.getDatabase(getApplicationContext()).dao().customerInsert(cus);
                            AppDatabase.getDatabase(getApplicationContext()).dao().accountInsert(acc);
                            }
                        });
                        Toast.makeText(ConfirmActivity.this, "Xác thực thành công", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ConfirmActivity.this, LoginActivity.class);
                        startActivity(intent);
                    } catch (Exception ex) {

                    }
                }else {
                    Toast.makeText(ConfirmActivity.this, "Xác thực không thành công", Toast.LENGTH_LONG).show();
                }
            }
        } );
    }

    private void sendMail() {
        confirmCode = randomCode()+"";

        JavaMailAPI javaMailAPI = null;
            javaMailAPI = new JavaMailAPI(this, intent.getExtras().getString("email"),"Send code",confirmCode);
            javaMailAPI.execute();

    }

    private void Anhxa() {
        edtConfirmCode = findViewById(R.id.edt_code);
        edtResult = findViewById(R.id.edt_result);
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