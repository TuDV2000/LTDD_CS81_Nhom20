package com.example.salebookapp;

import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.room.Dao;

import com.example.salebookapp.entities.Account;
import com.example.salebookapp.entities.Book;
import com.example.salebookapp.entities.Customer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import android.os.Bundle;

public class AddBookActivity extends AppCompatActivity {

    EditText edtBookName, edtPublication_Date, edtPrice, edtRepublish, edtQuantity,edtDecribe;
    ImageView imgViewBook;
    String imgPath;
    Button btAddBook;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        init();
        getImgPath();
        btAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(addValid()){
                    Book book = new Book(edtBookName.getText().toString(),edtPublication_Date.getText().toString(),
                            Double.parseDouble(edtPrice.getText().toString()),
                            Integer.parseInt(edtRepublish.getText().toString()),
                            Integer.parseInt(edtQuantity.getText().toString()),
                            edtDecribe.getText().toString(),imgPath);
                    AppDatabase.databaseWriteExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            AppDatabase.getDatabase(getApplicationContext()).dao().bookInsert(book);
                        }
                    });
                    Toast.makeText(getApplicationContext(), "Added successfully!", Toast.LENGTH_SHORT).show();
                    edtBookName.setText("");
                    edtPublication_Date.setText("");
                    edtPrice.setText("");
                    edtRepublish.setText("");
                    edtQuantity.setText("");
                    edtDecribe.setText("");
                    System.out.println("================Thêm được rồi nè================");
                }else{
                    System.out.println("================Thêm được qq================");
                }

            }
        });

    }
    public void getImgPath (){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                imgPath = AppDatabase.getDatabase(getApplicationContext()).dao().getBookByID(1).getImage();
                System.out.println(imgPath);
            }
        });
    }
    public boolean addValid(){
        if(edtBookName.getText().toString().length() == 0){
            Toast.makeText(this,"Yêu cầu nhập tên sách",Toast.LENGTH_SHORT).show();
            System.out.println("======== Vo day ne =====" + edtBookName.getText().toString());
            return false;
        }
        if(edtPublication_Date.getText().toString().length() == 0){
            Toast.makeText(this,"Yêu cầu nhập ngày xuất bản",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(edtPrice.getText().toString().length() == 0){
            Toast.makeText(this,"Yêu cầu nhập giá bán",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(edtRepublish.getText().toString().length() == 0){
            Toast.makeText(this,"Yêu cầu nhập lần tái bản",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(edtQuantity.getText().toString().length() == 0){
            Toast.makeText(this,"Yêu cầu nhập số lượng",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(edtDecribe.getText().toString().length() == 0){
            Toast.makeText(this,"Yêu cầu nhập mô tả",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void init(){
        edtBookName = (EditText) findViewById(R.id.edt_add_bookname);
        edtPublication_Date = (EditText) findViewById(R.id.edt_add_publication_date);
        edtPrice = (EditText) findViewById(R.id.edt_add_price);
        edtRepublish = (EditText) findViewById(R.id.edt_add_republish);
        edtQuantity = (EditText) findViewById(R.id.edt_add_Quantity);
        edtDecribe = findViewById(R.id.edt_add_decribe_book);
        //imgViewBook = (ImageView) findViewById(R.id.img_book);
        btAddBook = (Button) findViewById(R.id.btn_add_book);
    }

}
