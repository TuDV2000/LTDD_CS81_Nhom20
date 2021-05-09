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
    private BookAdapter bookAdapter;

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

        bookAdapter = new BookAdapter();

        bookAdapter.setData(HomeActivity.bookList, new BookAdapter.IClickAddToCartListener() {
            @Override
            public void onClickAddToCart(ImageView imgBook, Book book, BookAdapter.BookViewHolder holder) {
                Utils.cart.addToCart(book);

                holder.getTvQuantity().setText("X " + Utils.cart.getCart().get(book.getBookID()).getAmount());


                AnimationUtil.translateAnimation(mainActivity.getViewAnimation(), imgBook,
                    mainActivity.getViewEndAnimation(), new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                            bookAdapter.notifyDataSetChanged();
                            mainActivity.setCountProductInCart(mainActivity.getCountBook() + 1);
                        }
                });
            }
        });

        rcvBook.setAdapter(bookAdapter);

        return mView;
    }
}
