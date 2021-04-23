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

import java.util.Random;



public class ConfirmActivity extends AppCompatActivity {

    TextView tvEmail;
    EditText edtConfirmCode, edtResult;
    Button btnSubmit, btnResend;
    String confirmCode, email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("CONFIRM");
        actionBar.setDisplayHomeAsUpEnabled(true);

        Anhxa();

        Intent intent = getIntent();
        email = intent.getStringExtra(RegistActivity.EXTRA_TEXT);

        tvEmail.setText(email);
        sendMail();
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConfirmTrue()){
                    Toast.makeText(ConfirmActivity.this, "Xác thực thành công", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ConfirmActivity.this, LoginActivity.class);
                    startActivity(intent);

                }else {
                    Toast.makeText(ConfirmActivity.this, "Xác thực không thành công", Toast.LENGTH_LONG).show();
                }
            }
        } );
    }

    private void sendMail() {
        confirmCode = randomCode()+"";
        JavaMailAPI javaMailAPI = new JavaMailAPI(this,email,"Send code",confirmCode);
            javaMailAPI.execute();
    }

    private void Anhxa() {
        tvEmail = findViewById(R.id.tv_email);
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