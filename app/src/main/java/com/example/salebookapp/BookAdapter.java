package com.example.salebookapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salebookapp.entities.Book;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private List<Book> mListBook;
    private IClickAddToCartListener  iClickAddToCartListener;

    public interface IClickAddToCartListener{
        void onClickAddToCart(ImageView imgBook, Book book,BookViewHolder holder);

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
        holder.tvQuantity.setText("X " + String.valueOf(book.getAmount()));

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickAddToCartListener.onClickAddToCart(holder.imgBook, book,holder);
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
        private TextView tvBookName, tvDescription, tvPrice, tvQuantity;
        private RelativeLayout item;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);

            item = itemView.findViewById(R.id.book_item);
            imgBook = itemView.findViewById(R.id.img_book);
            tvBookName = itemView.findViewById(R.id.tv_book_name);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvQuantity = itemView.findViewById(R.id.tv_quantity);
        }

        public ImageView getImgBook() {
            return imgBook;
        }

        public void setImgBook(ImageView imgBook) {
            this.imgBook = imgBook;
        }

        public TextView getTvBookName() {
            return tvBookName;
        }

        public void setTvBookName(TextView tvBookName) {
            this.tvBookName = tvBookName;
        }

        public TextView getTvDescription() {
            return tvDescription;
        }

        public void setTvDescription(TextView tvDescription) {
            this.tvDescription = tvDescription;
        }

        public TextView getTvPrice() {
            return tvPrice;
        }

        public void setTvPrice(TextView tvPrice) {
            this.tvPrice = tvPrice;
        }

        public TextView getTvQuantity() {
            return tvQuantity;
        }

        public void setTvQuantity(TextView tvQuantity) {
            this.tvQuantity = tvQuantity;
        }

        public RelativeLayout getItem() {
            return item;
        }

        public void setItem(RelativeLayout item) {
            this.item = item;
        }
    }
}