package com.example.salebookapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;

public class LoginActivity extends AppCompatActivity {

    EditText edt_user, edt_pass;
    Button btn_signin, btn_signup, btn_quit;
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        AnhXa();
        ControlButton();

//        awesomeValidation.addValidation(LoginActivity.this, R.id.edt_username, Patterns.EMAIL_ADDRESS,R.string
//                .err_email);
//        String regexPassword = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
//        awesomeValidation.addValidation(LoginActivity.this,R.id.edt_password, regexPassword, R.string
//                .err_password);

    }

    private void ControlButton() {
        btn_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this, android.R.style.Theme_DeviceDefault_Light_Dialog);
                builder.setTitle("Bạn có chắc muốn thoát !");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onBackPressed();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.show();
            }
        });
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edt_user.getText().toString();
                String password = edt_pass.getText().toString();
                Log.d("username", username);
                Log.d("password", password);
                if (username.length() != 0 && password.length() != 0){
//                    if (awesomeValidation.validate()) {
//                        String[] user = {username};
//                        accountService = new AccountService(LoginActivity.this);
//                        Cursor c = accountService.getAccount(user);
//                        c.moveToPosition(-1);
//
//                        while (c.moveToNext()) {
//                            if (username == c.getString(c.getColumnIndex("username"))
//                                    && password == c.getString(c.getColumnIndex("password"))) {
//                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
//                                startActivity(intent);
//                            } else {
//                                Toast.makeText(LoginActivity.this, "Login Faile", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    }
                }else {
                    Toast.makeText(LoginActivity.this,"Mời bạn nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistActivity.class);
                startActivity(intent);
            }
        });
    }

    private void AnhXa() {
        edt_user = (EditText)findViewById(R.id.edt_username);
        edt_pass = (EditText)findViewById(R.id.edt_password);

        btn_signin = (Button)findViewById(R.id.btn_signin);
        btn_signup = (Button)findViewById(R.id.btn_signup);
        btn_quit = (Button)findViewById(R.id.btn_quit);
    }
}