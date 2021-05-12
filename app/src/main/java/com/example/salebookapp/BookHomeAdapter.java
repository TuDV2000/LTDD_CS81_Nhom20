package com.example.salebookapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salebookapp.entities.Book;

import java.util.List;

public class BookHomeAdapter extends RecyclerView.Adapter<BookHomeAdapter.BookViewHolder> {

    private List<Book> mListBook;
    private IClickGoToDetailListener iClickGoToDetailListener;

    public interface IClickAddToCartListener{
        void onClickAddToCart(ImageView imgBook, Book book,BookViewHolder holder);

    }

    public interface IClickGoToDetailListener{
        void onClickGoToDetail(Book book);
    }

    public interface IClickRemoveFromCartListener{
        void onClickRemoveFromCart(Book book, BookViewHolder holder);
    }

    public interface IClickRemoveBookFromCartListener{
        void onClickRemoveBookFromCart(Book book, BookViewHolder holder);
    }

    public void setData(List<Book> list, IClickAddToCartListener listener,
                        IClickGoToDetailListener listener1, IClickRemoveFromCartListener listener2,
                        IClickRemoveBookFromCartListener listener3){
        this.mListBook = list;
        this.iClickGoToDetailListener = listener1;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_home, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final BookViewHolder holder, int position) {
        Book book = mListBook.get(position);

        if (book == null){
            return;
        }

        holder.imgBook.setImageResource(Integer.parseInt(book.getImage().substring(book.getImage().lastIndexOf('/') + 1)));
        if(book.getBookName().length() >= 20){
            holder.tvBookName.setText(book.getBookName().substring(0,18)+"...");
        }else {
            holder.tvBookName.setText(book.getBookName());
        }
//        holder.tvDescription.setText(book.getDescribe());
        holder.tvPrice.setText(String.valueOf(book.getPrice()));
        Book bookCart = Utils.cart.getCart().get(book.getBookID());
        holder.tvQuantity.setText("Số lượng: " + String.valueOf(bookCart != null  ? bookCart.getAmount(): 0));
        //System.out.println("boooke 1" +book.getAmount());

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickGoToDetailListener.onClickGoToDetail(book);
            }
        });

    }

    public List<Book> getmListBook() {
        return mListBook;
    }

    @Override
    public int getItemCount() {
        if (mListBook != null){
            return mListBook.size();
        }
        return 0;
    }

    public class BookViewHolder extends RecyclerView.ViewHolder{

        //Khai báo


        public BookViewHolder(@NonNull View itemView) {
            super(itemView);

            //Ánh xạ
        }


    }
}