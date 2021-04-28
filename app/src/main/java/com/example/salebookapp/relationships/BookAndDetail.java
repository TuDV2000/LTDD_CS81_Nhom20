package com.example.salebookapp.relationships;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.salebookapp.entities.BillDetail;
import com.example.salebookapp.entities.Book;

import java.util.List;

public class BookAndDetail {
    @Embedded
    public Book book;
    @Relation(
            parentColumn = "book_id",
            entityColumn = "fk_book_id",
            entity = BillDetail.class
    )
    public List<BillDetail> billDetailList;
}
