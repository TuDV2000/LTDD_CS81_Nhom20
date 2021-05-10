package com.example.salebookapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.salebookapp.entities.Account;
import com.example.salebookapp.entities.Author;
import com.example.salebookapp.entities.Bill;
import com.example.salebookapp.entities.BillDetail;
import com.example.salebookapp.entities.Book;
import com.example.salebookapp.entities.BookType;
import com.example.salebookapp.entities.Customer;
import com.example.salebookapp.entities.Publisher;
import com.example.salebookapp.relationships.AccountAndCustomer;
import com.example.salebookapp.relationships.BillAndDetail;
import com.example.salebookapp.relationships.CustomerAndBill;

import java.util.List;

@Dao
public interface DAO {

    //AccountDao
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void accountInsert(Account account);
    @Delete
    public void accountDelete(Account account);
    @Query("select * from accounts where username = :username")
    public List<Account> getAccount(String username);


    //CustomerDao
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void customerInsert(Customer customer);
    @Delete
    public void customerDelete(Customer customer);
    @Query("select * from customers")
    public List<Customer> getAllCustomer();
    @Query("select * from customers where cus_id = :id")
    public Customer getCusById(int id);
    @Query("update customers set address = :newaddress, phone_number = :newPhone where cus_id = :id")
    public void changeCusProfile(String newaddress, String newPhone, int id);


    //BillDetailDao
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void billDetailInsert(BillDetail billDetail);
    @Delete
    public void billDetailDelete(BillDetail billDetail);


    //BillDao
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void billInsert(Bill bill);
    @Delete
    public void billDelete(Bill bill);
    @Query("select * from bills where bill_id = :id")
    public Bill getBillById(int id);
    @Query("select * from bills")
    public List<Bill> getAllBill();
    @Query("select * from bills where fk_cus_id = :id")
    public List<Bill> getBillByCusId(int id);
//    @Transaction
//    @Query("select * from bills where bill_id = :id")
//    public List<BillAndDetail> getDetailByBillId(int id);


    //BookTypeDao
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void typeInsert(BookType bookType);
    @Delete
    public void typeDelete(BookType bookType);
    @Query("select * from booktypes")
    public List<BookType> getAllType();
    @Query("update books set quantities =  :newQuantities where book_id  = :id")
    public void updateQuantitiesOfBook(int newQuantities, int id);


    //AuthorDao
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void authorInsert(Author author);
    @Delete
    public void authorDelete(Author author);
    @Query("select * from authors")
    public List<Author> getAllAuthor();


    //PublisherDao
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void publisherInsert(Publisher publisher);
    @Delete
    public void publisherDelete(Publisher publisher);
    @Query("select * from publishers")
    public List<Publisher> getAllPublisher();


    //BookDao
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void bookInsert(Book book);
    @Delete
    public void bookDelete(Book book);
    @Update
    public void bookUpdate(Book book);
    @Query("select * from books")
    public List<Book> getAllBook();
    @Query("select * from books where book_id = :id")
    public Book getBookByID(int id);
}
