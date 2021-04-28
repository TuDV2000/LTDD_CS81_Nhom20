package com.example.salebookapp.relationships;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.salebookapp.entities.Bill;
import com.example.salebookapp.entities.BillDetail;

import java.util.List;


public class BillAndDetail {
    @Embedded
    public Bill bill;
    @Relation(
            parentColumn = "bill_id",
            entityColumn = "fk_bill_id",
            entity = BillDetail.class
    )
    public List<BillDetail> billDetailList;
}
