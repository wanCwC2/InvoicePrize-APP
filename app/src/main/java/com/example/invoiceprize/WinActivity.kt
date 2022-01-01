package com.example.invoiceprize

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class WinActivity : AppCompatActivity() {
    private lateinit var db: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_win)

        db = SQL_helpler(this).writableDatabase

        //綁定元件
        val btn_back = findViewById<Button>(R.id.btn_back)
        val tv_specialPrize = findViewById<TextView>(R.id.tv_specialPrize)
        val tv_grandPrize = findViewById<TextView>(R.id.tv_grandPrize)
        val tv_firstPrizeA = findViewById<TextView>(R.id.tv_firstPrizeA)
        val tv_firstPrizeB = findViewById<TextView>(R.id.tv_firstPrizeB)
        val tv_firstPrizeC = findViewById<TextView>(R.id.tv_firstPrizeC)
        val tv_addSixPrize = findViewById<TextView>(R.id.tv_addSixPrize)

        //號碼顯示
        tv_specialPrize.text = db.execSQL("SELECT prize_id " +
                "FROM prize " +
                "WHERE name==specialPrize").toString()
        tv_grandPrize.text = db.execSQL("SELECT prize_id " +
                "FROM prize " +
                "WHERE name==grandPrize").toString()
        tv_firstPrizeA.text = db.execSQL("SELECT prize_id " +
                "FROM prize " +
                "WHERE name==firstPrizeA").toString()
        tv_firstPrizeB.text = db.execSQL("SELECT prize_id " +
                "FROM prize " +
                "WHERE name==firstPrizeB").toString()
        tv_firstPrizeC.text = db.execSQL("SELECT prize_id " +
                "FROM prize " +
                "WHERE name==firstPrizeC").toString()
        tv_addSixPrize.text = db.execSQL("SELECT prize_id " +
                "FROM prize " +
                "WHERE name==addSixPrize").toString()


        //回上一頁
        btn_back.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
        }

    }
}