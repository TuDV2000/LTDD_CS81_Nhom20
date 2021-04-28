package com.example.salebookapp.relationships;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.salebookapp.entities.Account;
import com.example.salebookapp.entities.Customer;

public class AccountAndCustomer {
    @Embedded public Customer customer;
    @Relation(
            parentColumn = "cus_id",
            entityColumn = "acc_id",
            entity = Account.class
    )
    public Account account;
}
