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
    TextView tvBookName, tvBookPrice, tvDescribe, tvQuantities;
    Button btnPlus, btnMinus, btnAddCart;
    Intent intent;
    int bookID;
    int quantities = 0;
    int dbquantities;
    Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        anhxa();
        intent = getIntent();
        bookID = intent.getIntExtra("bookID", 1);
        setdata();

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
//                if (dbquantities == Utils.cart.getCart().get(bookID).getAmount()) {
//                    System.out.println("tang so luong");
////                    Toast.makeText(getContextThis(), "Đã hết hàng", Toast.LENGTH_SHORT);
//                } else {
//                    quantities += 1;
//
//                }
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
                System.out.println(b);
                if (isAddToCart(bookID, i, dbquantities)) {
                    Utils.cart.addToCart(book, quantities);
                    finish();
                } else {
                    System.out.println("Số lượng có thể thêm là " + (dbquantities - i + quantities));
                    Toast.makeText(BookDetailActivity.this,
                            "Số lượng có thể thêm là " + (dbquantities - i + quantities), Toast.LENGTH_SHORT).show();
                }

//                if (quantities > 0) {
//
//                }

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
    }


}