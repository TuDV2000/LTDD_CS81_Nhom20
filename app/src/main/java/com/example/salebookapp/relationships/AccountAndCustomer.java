package com.example.salebookapp.relationships;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.salebookapp.entities.Account;
import com.example.salebookapp.entities.Customer;

public class AccountAndCustomer {
    @Embedded public Customer customer;
    @Relation(
            parentColumn = "cusID",
            entityColumn = "accID"
    )
    public Account account;
}
