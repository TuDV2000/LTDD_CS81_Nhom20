package com.example.salebookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.salebookapp.entities.Book;

public class BookDetailActivity extends AppCompatActivity {

    ImageView imvBook;
    TextView tvBookName, tvBookPrice, tvDescribe, tvQuantities, tvSL;
    Button btnPlus, btnMinus, btnAddCart;
    Intent intent;
    int bookID;
    int quantities = 0;
    int dbquantities;
    Book book;
    boolean flag;
    HomeActivity homeActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        anhxa();
        intent = getIntent();
        bookID = intent.getIntExtra("bookID", 1);
        flag = intent.getBooleanExtra("flag", true);
        setdata();

        if (!flag) {
            btnPlus.setEnabled(false);
            btnPlus.setAlpha(0);
            btnMinus.setEnabled(false);
            btnMinus.setAlpha(0);
            tvQuantities.setAlpha(0);
            btnAddCart.setEnabled(false);
            btnAddCart.setAlpha(0);
            tvSL.setAlpha(0);

        }

        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dbquantities = AppDatabase.getDatabase(getApplicationContext())
                        .dao().getBookByID(bookID).getQuantities();
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantities++;
                tvQuantities.setText(String.valueOf(quantities));
            }
        });
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantities > 0)
                    quantities -= 1;
                tvQuantities.setText(String.valueOf(quantities));
            }
        });
        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book b = Utils.cart.getCart().get(bookID);
                int i = (b==null?0:b.getAmount()) + quantities;

                if (isAddToCart(bookID, quantities, dbquantities)) {
                    Utils.cart.addToCart(book, quantities);
                    finish();
                } else {
                    System.out.println("Số lượng có thể thêm là " + (dbquantities - i + quantities));
                    Toast.makeText(BookDetailActivity.this,
                            "Số lượng có thể thêm là " + (dbquantities - i + quantities), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     *
     * @param bookID
     * @param quantities
     * @param dbquantities
     * @return true khi so luong them vao gio hang be hon so luong trong kho, false thi nguoc lai
     */
    static boolean isAddToCart(int bookID, int quantities, int dbquantities) {
        Book b = Utils.cart.getCart().get(bookID);

        int i = (b==null?0:b.getAmount()) + quantities;

        return i <= dbquantities;
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

    Context getContextThis() {
        return this;
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
        tvSL = findViewById(R.id.tv_soLuong);
    }


}