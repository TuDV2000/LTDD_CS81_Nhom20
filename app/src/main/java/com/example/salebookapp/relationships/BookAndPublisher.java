//package com.example.salebookapp.relationships;
//
//import androidx.room.Embedded;
//import androidx.room.Relation;
//
//import com.example.salebookapp.entities.Book;
//import com.example.salebookapp.entities.Publisher;
//
//import java.util.List;
//
//public class BookAndPublisher {
//    @Embedded
//    public Publisher publisher;
//    @Relation(
//            parentColumn = "publisher_id",
//            entityColumn = "fk_publisher_id",
//            entity = Book.class
//    )
//    public List<Book> bookList;
//}
