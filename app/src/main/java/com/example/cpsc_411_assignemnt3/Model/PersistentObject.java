package com.example.cpsc_411_assignemnt3.Model;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;

public abstract class PersistentObject {
    public abstract void insert(SQLiteDatabase db);
    public abstract void initFrom(SQLiteDatabase db, Cursor cursor);
}
