package com.example.invoiceprize

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQL_helpler2(
    context: Context,
    name: String = database,
    factory: SQLiteDatabase.CursorFactory? = null,
    version: Int = ver
): SQLiteOpenHelper(context, name, factory, version) {
        companion object{
            private const val database = "passbook"
            private const val ver = 1
        }
    override fun onCreate(db: SQLiteDatabase){
        db.execSQL("CREATE TABLE passbook(invoice_id PRIMARY KEY, date not null, money not null, award not null)")
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int){
        db.execSQL("DROP TABLE IF EXISTS passbook")
        onCreate(db)
    }

}