package com.example.salebookapp.relationships;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.salebookapp.entities.Bill;
import com.example.salebookapp.entities.Customer;

import java.util.List;

public class CustomerAndBill {
    @Embedded
    public Customer customer;
    @Relation(
            parentColumn = "cus_id",
            entityColumn = "fk_cus_id",
            entity = Bill.class
    )
    public List<Bill> billList;
}
