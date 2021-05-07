package com.example.salebookapp;

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

import com.example.salebookapp.entities.Book;

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
    private BookAdapter bookAdapter;

    public BookFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_book, container, false);
        rcvBook = mView.findViewById(R.id.rcv_book);
        mainActivity = (HomeActivity) getActivity();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mainActivity);
        rcvBook.setLayoutManager(linearLayoutManager);

        bookAdapter = new BookAdapter();
        bookAdapter.setData(getListBook(), new BookAdapter.IClickAddToCartListener() {
            @Override
            public void onClickAddToCart(ImageView imgAddToCart, Book book) {
                AnimationUtil.translateAnimation(mainActivity.getViewAnimation(), imgAddToCart,
                        mainActivity.getViewEndAnimation(), new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                book.setAddToCart(true);
                                imgAddToCart.setBackgroundResource(R.drawable.bg_gray_conner_6);
                                bookAdapter.notifyDataSetChanged();

                                mainActivity.setCountProductInCart(mainActivity.getCountBook() + 1);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });
            }
        });

        rcvBook.setAdapter(bookAdapter);

        return mView;
    }

    private List<Book> getListBook(){
        List<Book> list = new ArrayList<>();

        list.add(new Book("Vượt qua tất cả", "20-02-2002",
                            100000.0, 1, 10,
                            Uri.parse("android.resource://" + R.class.getPackage().getName() + "/" + R.drawable.book1).toString())
        );

        list.add(new Book("English for Life", "20-02-2002",
                120000.0, 1, 10,
                Uri.parse("android.resource://" + R.class.getPackage().getName() + "/" + R.drawable.book2).toString())
        );

        return list;
    }
}
