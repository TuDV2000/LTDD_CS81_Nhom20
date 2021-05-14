package com.example.salebookapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.salebookapp.entities.Account;
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
    private List<Book> bookList = HomeActivity.bookList;

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

        bookHomeAdapter.setData(bookList, new BookHomeAdapter.IClickGoToDetailListener() {
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

        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                bookList = AppDatabase.getDatabase(getContext().getApplicationContext()).dao().getAllBook();
            }
        });
        (new Handler()).postDelayed(new Runnable() {
            @Override
            public void run() {
                bookHomeAdapter.setData(bookList, new BookHomeAdapter.IClickGoToDetailListener() {
                    @Override
                    public void onClickGoToDetail(Book book) {
                        Intent intent = new Intent(getContext(), BookDetailActivity.class);

                        intent.putExtra("bookID", book.getBookID());

                        startActivity(intent);
                    }
                });
            }
        }, 200);
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
