package com.example.salebookapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.salebookapp.entities.Book;


public class CartFragment extends Fragment {

    private RecyclerView rcvCart;
    private View mView;
    private HomeActivity mainActivity;
    private BookAdapter bookAdapter;
    private TextView tvTotalPrice;

    public CartFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_cart, container, false);
        rcvCart = mView.findViewById(R.id.rcv_cart);
        tvTotalPrice = mView.findViewById(R.id.tv_total_price);

        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();

        mainActivity = (HomeActivity) getActivity();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mainActivity);
        rcvCart.setLayoutManager(linearLayoutManager);

        bookAdapter = new BookAdapter();

        bookAdapter.setData(Utils.cart.getListBooks(), new BookAdapter.IClickAddToCartListener() {
            @Override
            public void onClickAddToCart(ImageView imgAddToCart, Book book) {

            }
        });

        rcvCart.setAdapter(bookAdapter);

        tvTotalPrice.setText("Tổng tiền: " + Utils.cart.getTotalPrice());
    }
}