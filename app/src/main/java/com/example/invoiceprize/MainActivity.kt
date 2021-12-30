package com.example.invoiceprize

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var db: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = SQL_helpler(this).writableDatabase

        //綁定元件
        val btn_reward = findViewById<Button>(R.id.btn_back)

        //把對獎年月都儲存在資料庫中
        if (db.execSQL("SELECT * FROM prize") == null){
            val cd = CatchData()
            val cal: Calendar = Calendar.getInstance()
            cal.add(Calendar.MONTH, -2)
            cal.add(Calendar.DATE, -25)
            var timeNowMonth: String = SimpleDateFormat("MM").format(cal.getTime())
            if (timeNowMonth.toInt()/2 == 0){
                cal.add(Calendar.MONTH, -1)
            }
            var timeNowYear = SimpleDateFormat("yyyy").format(cal.getTime())
            timeNowMonth = SimpleDateFormat("MM").format(cal.getTime())
            cd.get(timeNowYear, timeNowMonth)
        }



        //前往對獎專區
        btn_reward.setOnClickListener{
            startActivity(Intent(this,RewardActivity::class.java))
        }
    }
}