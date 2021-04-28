package com.example.salebookapp.relationships;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.salebookapp.entities.Book;
import com.example.salebookapp.entities.BookType;

import java.util.List;

public class BookAndType {
    @Embedded
    public BookType bookType;
    @Relation(
            parentColumn = "type_id",
            entityColumn = "fk_book_type_id",
            entity = Book.class
    )
    public List<Book> bookList;
}
