package com.example.salebookapp.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "booktypes")
public class BookType {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "type_id")
    private int typeID;
    @ColumnInfo(name = "type_name")
    private String typeName;
    @ColumnInfo(name = "describe")
    private String describe;

    public BookType(String typeName, String describe) {
        this.typeName = typeName;
        this.describe = describe;
    }

    //get and set
    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
