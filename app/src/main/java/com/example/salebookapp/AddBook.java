package com.example.salebookapp;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
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

import com.example.salebookapp.entities.Book;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class AddBook extends AppCompatActivity {

    EditText edtBookName, edtPublication_Date, edtPrice, edtRepublish, edtQuantity;
    ImageView imgViewBook;

    Button btAddBook;





//    private static final int PICK_IMAGE = 100;

    final int REQUEST_CODE_GALLERY = 999;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addbook);

        init();


        imgViewBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        AddBook.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });

        btAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Book book = new Book(
                            edtBookName.getText().toString(),
                            edtPublication_Date.getText().toString(),
                            Double.parseDouble(edtPrice.getText().toString()),
                            Integer.parseInt(edtRepublish.getText().toString()),
                            Integer.parseInt(edtQuantity.getText().toString()),
                            imgViewBookToByte(imgViewBook));
                try{
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
                    imgViewBook.setImageResource(R.mipmap.ic_launcher);
                }
                catch (Exception e){
                    e.printStackTrace();
                };

            }
        });


    }

    private byte[] imgViewBookToByte(ImageView image){
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_CODE_GALLERY){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(getApplicationContext(),
                        "You don't have permission to access file", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgViewBook.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    public void init(){
        edtBookName = (EditText) findViewById(R.id.edt_bookname);
        edtPublication_Date = (EditText) findViewById(R.id.edt_publication_date);
        edtPrice = (EditText) findViewById(R.id.edt_price);
        edtRepublish = (EditText) findViewById(R.id.edt_republish);
        edtQuantity = (EditText) findViewById(R.id.edt_Quantity);
        imgViewBook = (ImageView) findViewById(R.id.img_book);
        btAddBook = (Button) findViewById(R.id.bt_add_book);

    }

}