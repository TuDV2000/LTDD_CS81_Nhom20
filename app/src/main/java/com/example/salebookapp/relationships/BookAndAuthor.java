package com.example.salebookapp.relationships;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.salebookapp.entities.Author;
import com.example.salebookapp.entities.Book;

import java.util.List;

public class BookAndAuthor {
    @Embedded
    public Author author;
    @Relation(
            parentColumn = "author_id",
            entityColumn = "fk_author_id",
            entity = Book.class
    )
    public List<Book> bookList;
}
