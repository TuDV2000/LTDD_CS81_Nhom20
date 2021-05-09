package com.example.salebookapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationHolder;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.basgeekball.awesomevalidation.utility.custom.CustomErrorReset;
import com.basgeekball.awesomevalidation.utility.custom.CustomValidation;
import com.basgeekball.awesomevalidation.utility.custom.CustomValidationCallback;
import com.basgeekball.awesomevalidation.utility.custom.SimpleCustomValidation;
import com.example.salebookapp.entities.Account;
import com.example.salebookapp.entities.Customer;

import java.util.List;

import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;
import static com.basgeekball.awesomevalidation.ValidationStyle.TEXT_INPUT_LAYOUT;
import static com.basgeekball.awesomevalidation.ValidationStyle.UNDERLABEL;

public class RegistActivity extends AppCompatActivity {

    private EditText edtFullName;
    private EditText edtUserName;
    private EditText edtPassword;
    private EditText edtCellPhone;
    private EditText edtAddress;
    private EditText edtConfirmPass;
    private TextView txtChangeLogin;
    private CheckBox cbAgree;
    private Button btnCreate;
    private AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        setup();
        addValidation();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("REGIST");
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Điều khoản
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
            public void onClick (View v){
                // Kiểm tra tính đúng đắn của đầu vào
               if (awesomeValidation.validate()) {
                   Intent intent = new Intent(RegistActivity.this, ConfirmActivity.class);

                   intent.putExtra("email", edtUserName.getText().toString());
                   intent.putExtra("pass", edtPassword.getText().toString());
                   intent.putExtra("type", "Buyer");
                   intent.putExtra("fullName", edtFullName.getText().toString());
                   intent.putExtra("phone", edtCellPhone.getText().toString());
                   intent.putExtra("address", edtAddress.getText().toString());

                   System.out.println(edtUserName.getText().toString());
                   AppDatabase.databaseWriteExecutor.execute(new Runnable() {
                       @Override
                       public void run() {
                           if (AppDatabase.getDatabase(getApplicationContext())
                                   .dao().getAccount(edtUserName.getText().toString()).size() == 0) {
                               startActivity(intent);
                           } else {
                               Toast.makeText(RegistActivity.this,
                                       "Tài khoản đã được sử dụng", Toast.LENGTH_SHORT).show();
                           }
                       }
                   });
               }
            }
        });
    }

    public void addValidation(){
        awesomeValidation = new AwesomeValidation(BASIC);
        awesomeValidation.addValidation(RegistActivity.this, R.id.edt_fullname
                , RegexTemplate.NOT_EMPTY ,R.string.err_fullname);
        awesomeValidation.addValidation(RegistActivity.this,R.id.edt_address
                ,RegexTemplate.NOT_EMPTY,R.string.err_value);
        awesomeValidation.addValidation(RegistActivity.this,R.id.edt_username
                , Patterns.EMAIL_ADDRESS,R.string
                .err_email);
        String regexPassword = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
        awesomeValidation.addValidation(RegistActivity.this,R.id.edt_password
                , regexPassword,R.string
                .err_password);
        awesomeValidation.addValidation(RegistActivity.this,R.id.edt_confirmpassword
                ,R.id.edt_password,R.string.err_confirmpassword);
    }

    public void setup() {
        edtFullName = findViewById(R.id.edt_fullname);
        edtUserName = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        edtConfirmPass= findViewById(R.id.edt_confirmpassword);
        edtCellPhone = findViewById(R.id.edt_cellphone);
        edtAddress = findViewById(R.id.edt_address);
        txtChangeLogin = findViewById(R.id.txt_change_login);
        cbAgree = findViewById(R.id.cb_agree);
        btnCreate = findViewById(R.id.btn_create);
    }

    public void onClick(View v) {
        Intent intent = new Intent(RegistActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public EditText getEdtFullName() {
        return edtFullName;
    }

    public void setEdtFullName(EditText edtFullName) {
        this.edtFullName = edtFullName;
    }

    public EditText getEdtUserName() {
        return edtUserName;
    }

    public void setEdtUserName(EditText edtUserName) {
        this.edtUserName = edtUserName;
    }

    public EditText getEdtPassword() {
        return edtPassword;
    }

    public void setEdtPassword(EditText edtPassword) {
        this.edtPassword = edtPassword;
    }

    public EditText getEdtCellPhone() {
        return edtCellPhone;
    }

    public void setEdtCellPhone(EditText edtCellPhone) {
        this.edtCellPhone = edtCellPhone;
    }

    public EditText getEdtAddress() {
        return edtAddress;
    }

    public void setEdtAddress(EditText edtAddress) {
        this.edtAddress = edtAddress;
    }

    public EditText getEdtConfirmPass() {
        return edtConfirmPass;
    }

    public void setEdtConfirmPass(EditText edtConfirmPass) {
        this.edtConfirmPass = edtConfirmPass;
    }

    public TextView getTxtChangeLogin() {
        return txtChangeLogin;
    }

    public void setTxtChangeLogin(TextView txtChangeLogin) {
        this.txtChangeLogin = txtChangeLogin;
    }

    public CheckBox getCbAgree() {
        return cbAgree;
    }

    public void setCbAgree(CheckBox cbAgree) {
        this.cbAgree = cbAgree;
    }

    public Button getBtnCreate() {
        return btnCreate;
    }

    public void setBtnCreate(Button btnCreate) {
        this.btnCreate = btnCreate;
    }

    public AwesomeValidation getAwesomeValidation() {
        return awesomeValidation;
    }

    public void setAwesomeValidation(AwesomeValidation awesomeValidation) {
        this.awesomeValidation = awesomeValidation;
    }
}