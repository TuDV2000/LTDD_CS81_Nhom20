package com.example.salebookapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salebookapp.entities.Book;

import java.io.File;
import java.sql.SQLOutput;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private List<Book> mListBook;
    private IClickAddToCartListener  iClickAddToCartListener;

    public interface IClickAddToCartListener{
        void onClickAddToCart(ImageView imgBook, Book book);

    }

    public void setData(List<Book> list, IClickAddToCartListener listener){
        this.mListBook = list;
        this.iClickAddToCartListener = listener;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final BookViewHolder holder, int position) {
        Book book = mListBook.get(position);
        if (book == null){
            return;
        }

        holder.imgBook.setImageResource(Integer.parseInt(book.getImage().substring(book.getImage().lastIndexOf('/') + 1)));
        holder.tvBookName.setText(book.getBookName());
        holder.tvDescription.setText(book.getDescribe());
        holder.tvPrice.setText(String.valueOf(book.getPrice()));

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickAddToCartListener.onClickAddToCart(holder.imgBook, book);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mListBook != null){
            return mListBook.size();
        }
        return 0;
    }

    public class BookViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgBook;
        private TextView tvBookName;
        private TextView tvDescription;
        private TextView tvPrice;
        private RelativeLayout item;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);

            item = itemView.findViewById(R.id.book_item);
            imgBook = itemView.findViewById(R.id.img_book);
            tvBookName = itemView.findViewById(R.id.tv_book_name);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvPrice = itemView.findViewById(R.id.tv_price);
        }
    }
}