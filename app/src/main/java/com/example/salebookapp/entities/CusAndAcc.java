package com.example.salebookapp.entities;

import androidx.room.Embedded;
import androidx.room.Relation;

public class CusAndAcc {
    @Embedded public Customers khachHang;
    @Relation(
            parentColumn = "cusID",
            entityColumn = "accID"
    )
    public Accounts account;

}
