package com.example.salebookapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.salebookapp.entities.Book;

import java.util.ArrayList;

public class BookListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Book> bookList;

    public BookListAdapter(Context context, int layout, ArrayList<Book> bookList) {
        this.context = context;
        this.layout = layout;
        this.bookList = bookList;
    }

    @Override
    public int getCount() {
        return bookList.size();
    }

    @Override
    public Object getItem(int position) {
        return bookList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView txtBookName, txtPrice;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if (row == null ){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.txtBookName = (TextView) row.findViewById(R.id.tv_book_name);
            holder.txtPrice= (TextView) row.findViewById(R.id.tv_price);
            holder.imageView = (ImageView) row.findViewById(R.id.img_book);
            row.setTag(holder);
        }
        else {
            holder =(ViewHolder) row.getTag();
        }

        Book book = bookList.get(position);

        holder.txtBookName.setText(book.getBookName());
        holder.txtPrice.setText(String.valueOf(book.getPrice()));

        byte[] bookImage = book.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(bookImage, 0, bookImage.length);
        holder.imageView.setImageBitmap(bitmap);

        return null;
    }
}
