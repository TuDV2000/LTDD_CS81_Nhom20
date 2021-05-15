package com.example.salebookapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.example.salebookapp.entities.Account;
import com.example.salebookapp.entities.Bill;
import com.example.salebookapp.entities.BillDetail;
import com.example.salebookapp.entities.Book;

import java.util.Date;
import java.util.List;


public class CartFragment extends Fragment{

    private RecyclerView rcvCart;
    private View mView;
    private HomeActivity mainActivity;
    private BookAdapter bookAdapter;
    private TextView tvTotalPrice, tvPrice;
    private Button btnPayment;
    int id;

    public CartFragment() {

    }

    private void setUp() {
        rcvCart = mView.findViewById(R.id.rcv_cart);
        tvTotalPrice = mView.findViewById(R.id.tv_total_price);
        tvPrice = mView.findViewById(R.id.tv_price);
        btnPayment = mView.findViewById(R.id.btn_payment);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_cart, container, false);

        setUp();
        id = getId();

        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Utils.cart.getCart().isEmpty()) {
                    if (Utils.accLogin != null) {
                        startActivity(new Intent(getContext(), PaymentActivity.class));
                    } else {
                        Toast.makeText(getContext(), "Bạn chưa đăng nhập !!!", Toast.LENGTH_SHORT).show();
                        Utils.fragmentCurrentId = 2;
                        mainActivity.getAhBottomNavigationViewPager().setCurrentItem(3);

                    }
                } else {
                    Toast.makeText(getContext(), "Không có gì trong giỏ !!!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();

        mainActivity = (HomeActivity) getActivity();

        if (Utils.cart.getCartItemAll() != null) {
            mainActivity.setCountProductInCart(Utils.cart.getCartItemAll().size());
        } else {
            mainActivity.setCountProductInCart(0);
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mainActivity);
        rcvCart.setLayoutManager(linearLayoutManager);

        bookAdapter = new BookAdapter();

        bookAdapter.setData(Utils.cart.getCartItemAll(), new BookAdapter.IClickAddToCartListener() {
            @Override
            public void onClickAddToCart(ImageView imgAddToCart, Book book, BookAdapter.BookViewHolder holder) {

//                AppDatabase.databaseWriteExecutor.execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        dbquantities = AppDatabase.getDatabase(getContext())
//                                .dao().getBookByID(book.getBookID()).getQuantities();
//                    }
//                });

                Utils.cart.addToCart(book);
                holder.getTvQuantity().setText("Số lượng: " + Utils.cart.getCart().get(book.getBookID()).getAmount());
                tvTotalPrice.setText("Tổng tiền: " + Utils.cart.getTotalPrice());
            }
        }, new BookAdapter.IClickGoToDetailListener() {
            @Override
            public void onClickGoToDetail(Book book) {
                Intent intent = new Intent(getContext(), BookDetailActivity.class);

                intent.putExtra("bookID", book.getBookID());
                intent.putExtra("flag", false);

                startActivity(intent);
            }
        }, new BookAdapter.IClickRemoveFromCartListener() {
            @Override
            public void onClickRemoveFromCart(Book book, BookAdapter.BookViewHolder holder) {
                Utils.cart.removeFromCart(book);
                if (Utils.cart.getCart().get(book.getBookID()) != null) {
                    holder.getTvQuantity().setText("Số lượng: " + Utils.cart.getCart().get(book.getBookID()).getAmount());
                } else holder.getTvQuantity().setText("Số lượng: 0");

                tvTotalPrice.setText("Tổng tiền: " + Utils.cart.getTotalPrice());
            }
        }, new BookAdapter.IClickRemoveBookFromCartListener() {
            @Override
            public void onClickRemoveBookFromCart(Book book, BookAdapter.BookViewHolder holder) {
                Utils.cart.removeBookFromCart(book);
                Intent intent = new Intent(getContext(),getContext().getClass() );
                tvTotalPrice.setText("Tổng tiền: " + Utils.cart.getTotalPrice());
                if (Utils.cart.getCartItemAll() != null) {
                    mainActivity.setCountProductInCart(Utils.cart.getCartItemAll().size());
                } else {
                    mainActivity.setCountProductInCart(0);
                }
                onResume();
            }
        });

        rcvCart.setAdapter(bookAdapter);

        tvTotalPrice.setText("Tổng tiền: " + Utils.cart.getTotalPrice());
    }
}