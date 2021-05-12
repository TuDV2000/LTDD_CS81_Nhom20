package com.example.salebookapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.salebookapp.entities.Bill;
import com.example.salebookapp.entities.BillDetail;
import com.example.salebookapp.entities.Book;
import com.example.salebookapp.entities.Customer;

import java.util.Date;

public class PaymentActivity extends AppCompatActivity {

    private RecyclerView rcvCart;
    private BookAdapter bookAdapter;
    private TextView tvTotal, tvCusName, tvCusAddress, tvCusNumberPhone;
    private Button btnOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        double total = Utils.cart.getTotalPrice();

        setUp();
        setProfileCus();
        tvTotal.setText(String.valueOf(total));

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDatabase.databaseWriteExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        Date date = new Date();
                        int idBill;
                        String d = String.format("%d-%d-%d", date.getDate(), date.getMonth() + 1,
                                date.getYear() + 1900);

                        AppDatabase.getDatabase(getApplicationContext())
                                .dao().billInsert(new Bill(Utils.accLogin.getAccID(),
                                total, d));
                        idBill = AppDatabase.getDatabase(getApplicationContext())
                                .dao().getAllBill().size();
                        for (Book b : Utils.cart.getCartItemAll()) {
                            AppDatabase.getDatabase(getApplicationContext())
                                    .dao()
                                    .billDetailInsert(new BillDetail(idBill,
                                            b.getBookID(),
                                            b.getPrice(),
                                            b.getAmount())
                                    );
                        }
                        Utils.cart.getCart().clear();
                        Utils.cart.setTotalPrice(0);
                    }
                });
                Toast.makeText(PaymentActivity.this,"Đặt hàng thành công !!!!",Toast.LENGTH_SHORT).show();
                finish();
            }
        });


    }

    void setUp() {
        rcvCart = findViewById(R.id.rcv_book_items);

        tvTotal = findViewById(R.id.tv_total);
        tvCusName = findViewById(R.id.tv_cus_name);
        tvCusAddress = findViewById(R.id.tv_cus_address);
        tvCusNumberPhone = findViewById(R.id.tv_cus_number_phone);

        btnOrder = findViewById(R.id.btn_order);
    }

    void setProfileCus() {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                Customer cus = AppDatabase.getDatabase(getApplicationContext())
                        .dao().getCusById(Utils.accLogin.getAccID());

                if (cus != null) {
                    tvCusName.setText(cus.getFullName());
                    tvCusAddress.setText(cus.getAddress());
                    tvCusNumberPhone.setText(cus.getPhoneNumber());
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvCart.setLayoutManager(linearLayoutManager);

        bookAdapter = new BookAdapter();

        bookAdapter.setData(Utils.cart.getCartItemAll(), new BookAdapter.IClickAddToCartListener() {
            @Override
            public void onClickAddToCart(ImageView imgAddToCart, Book book, BookAdapter.BookViewHolder holder) {

            }
        }, new BookAdapter.IClickGoToDetailListener() {
            @Override
            public void onClickGoToDetail(Book book) {

            }
        }, new BookAdapter.IClickRemoveFromCartListener() {
            @Override
            public void onClickRemoveFromCart(Book book, BookAdapter.BookViewHolder holder) {

            }
        });

        rcvCart.setAdapter(bookAdapter);
    }

}