package com.example.salebookapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.salebookapp.entities.Book;
import com.example.salebookapp.entities.Customer;

import java.util.ArrayList;
import java.util.List;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link BookFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class BookFragment extends Fragment {
    private RecyclerView rcvBook;
    private View mView;
    private HomeActivity mainActivity;
    private BookHomeAdapter bookHomeAdapter;

    public BookFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_book, container, false);
        rcvBook = mView.findViewById(R.id.rcv_book);
        mainActivity = (HomeActivity) getActivity();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mainActivity);
        rcvBook.setLayoutManager(linearLayoutManager);

        bookHomeAdapter = new BookHomeAdapter();



        bookHomeAdapter.setData(HomeActivity.bookList, new BookHomeAdapter.IClickGoToDetailListener() {
            @Override
            public void onClickGoToDetail(Book book) {
                Intent intent = new Intent(getContext(), BookDetailActivity.class);

                intent.putExtra("bookID", book.getBookID());

                startActivity(intent);
            }
        });
        rcvBook.setAdapter(bookHomeAdapter);

        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();

        bookHomeAdapter.notifyDataSetChanged();
        for(Book book: Utils.cart.getCart().values()){
            System.out.println(book);
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        bookHomeAdapter.notifyDataSetChanged();
    }
}
