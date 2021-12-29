package com.example.invoiceprize

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQL_helpler(
    context: Context,
    name: String = database,
    factory: SQLiteDatabase.CursorFactory? = null,
    version: Int = ver
): SQLiteOpenHelper(context, name, factory, version) {
        companion object{
            private const val database = "addressBook"
            private const val ver = 1
        }
    override fun onCreate(db: SQLiteDatabase){
        db.execSQL("CREATE TABLE addressBooks(id text PRIMARY KEY, name text not null, phone text not null, address text)")
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int){
        db.execSQL("DROP TABLE IF EXISTS addressBook")
        onCreate(db)
    }

}