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

    public interface IClickGoToDetailListener{
        void onClickGoToDetail(Book book);
    }


    public void setData(List<Book> list,IClickGoToDetailListener listener1){
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
        holder.tvBookPrice.setText(String.valueOf(book.getPrice()));
        holder.tvBookQuantities.setText("Còn " + book.getQuantities() + " sản phẩm");

        if (book.getQuantities() == 0) {
            holder.item.setAlpha((float) 0.6);
            holder.item.setEnabled(false);
        }

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickGoToDetailListener.onClickGoToDetail(book);
            }
        });

        if (Utils.accLogin != null) {
            if (Utils.accLogin.getAccType().equals("Saler")) {
                holder.item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        }
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

        TextView tvBookName, tvBookPrice, tvBookQuantities;
        ImageView imgBook;
        RelativeLayout item;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);

            item = itemView.findViewById(R.id.book_item_home);
            imgBook =itemView.findViewById(R.id.img_book_home);
            tvBookName = itemView.findViewById(R.id.tv_book_name_home);
            tvBookPrice = itemView.findViewById(R.id.tv_price_book_home);
            tvBookQuantities = itemView.findViewById(R.id.tv_book_quantities_home);

        }


    }
}