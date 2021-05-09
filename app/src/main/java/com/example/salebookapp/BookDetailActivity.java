package com.example.salebookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.salebookapp.entities.Book;

public class BookDetailActivity extends AppCompatActivity {

    ImageView imvBook;
    TextView tvBookName, tvBookPrice, tvDescribe, tvQuantities;
    Button btnPlus, btnMinus, btnAddCart;
    Intent intent;
    int bookID;
    int quantities = 0;
    Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        anhxa();
        intent = getIntent();
        bookID = intent.getIntExtra("bookID", 1);
        //quantities = intent.getIntExtra("amount",0);
        setdata();

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantities += 1;
                tvQuantities.setText(String.valueOf(quantities));
            }
        });
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quantities > 0)
                quantities -= 1;
                tvQuantities.setText(String.valueOf(quantities));
            }
        });
        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.cart.addToCart(book, quantities);
            }
        });
    }

    private void setdata() {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                book = AppDatabase.getDatabase(getApplicationContext()).dao().getBookByID(bookID);
                imvBook.setImageResource(Integer.parseInt(book.getImage().substring(book.getImage().lastIndexOf('/') + 1)));
                tvBookName.setText(book.getBookName());
                tvBookPrice.setText(String.valueOf(book.getPrice()));
                tvDescribe.setText(book.getDescribe());
            }
        });
    }

    private void anhxa() {
        imvBook = findViewById(R.id.iv_detail_book);
        tvBookName = findViewById(R.id.tv_detail_book_name);
        tvBookPrice = findViewById(R.id.tv_detail_book_price);
        tvDescribe = findViewById(R.id.tv_detail_book_describe);
        tvQuantities = findViewById(R.id.tv_bookquantities);
        btnPlus = findViewById(R.id.btn_detail_plus_detail);
        btnMinus = findViewById(R.id.btn_detail_minus_detail);
        btnAddCart = findViewById(R.id.btn_describe_addcart);
    }
}