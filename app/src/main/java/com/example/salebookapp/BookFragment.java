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
        }, new BookAdapter.IClickGoToDetailListener() {
            @Override
            public void onClickGoToDetail(Book book) {
                Intent intent = new Intent(getContext(), BookDetailActivity.class);

                intent.putExtra("bookID", book.getBookID());
//                intent.putExtra("amount", Utils.cart.getCart().get(book.getBookID()).getAmount());

                startActivity(intent);
            }
        }, new BookAdapter.IClickRemoveFromCartListener() {
            @Override
            public void onClickRemoveFromCart(Book book, BookAdapter.BookViewHolder holder) {
                Book bookCart = Utils.cart.getCart().get(book.getBookID());
                System.out.println("remove " + bookCart);
                Utils.cart.removeFromCart(book);
                holder.getTvQuantity().setText("X " + (bookCart != null ? bookCart.getAmount() : 0));
            }
        }, new BookAdapter.IClickRemoveBookFromCartListener() {
            @Override
            public void onClickRemoveBookFromCart(Book book, BookAdapter.BookViewHolder holder) {

            }
        });

        rcvBook.setAdapter(bookAdapter);

        return mView;
    }
    static int dem = 0;
    @Override
    public void onResume() {

        super.onResume();
//        bookAdapter.notify();
        System.out.println("laan:" + dem++);
        bookAdapter.notifyDataSetChanged();
        for(Book book: Utils.cart.getCart().values()){
            System.out.println(book);
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        bookAdapter.notifyDataSetChanged();
    }
}
