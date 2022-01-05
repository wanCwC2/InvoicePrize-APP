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
        val btn_month = findViewById<Button>(R.id.btn_month)
        val btn_month2 = findViewById<Button>(R.id.btn_month2)
        var name = arrayOf(
            "specialPrize", "grandPrize", "firstPrizeA",
            "firstPrizeB", "firstPrizeC", "addSixPrize"
        )

        val time = Time()
        time.run()

        //顯示按鈕名稱
        btn_month.text = "${time.timeBefYear}年${time.timeBefMonth}-${time.timeBefMonth1}月"
        btn_month2.text = "${time.timeNowYear}年${time.timeNowMonth}-${time.timeNowMonth1}月"

        //號碼顯示
        lateinit var query: String
        query = "SELECT * FROM prize WHERE date like '${time.timeNow}'"
        val c = db.rawQuery(query, null)
        c?.moveToFirst()
        if (c != null) {
            val s = c.getString(c.getColumnIndex("prize_id"))
            tv_specialPrize.text = s
        }
        if (c != null) {
            c.moveToNext()
            tv_grandPrize.text = "${c.getString(0)}"
        }
        if (c != null) {
            c.moveToNext()
            tv_firstPrizeA.text = "${c.getString(0)}"
        }
        if (c != null) {
            c.moveToNext()
            tv_firstPrizeB.text = "${c.getString(0)}"
        }
        if (c != null) {
            c.moveToNext()
            tv_firstPrizeC.text = "${c.getString(0)}"
        }
        if (c != null) {
            c.moveToNext()
            tv_addSixPrize.text = "${c.getString(0)}"
        }
        c.close()

        //去另外一期
        btn_month.setOnClickListener{
            startActivity(Intent(this,Win2Activity::class.java))
        }

        //回上一頁
        btn_back.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
        }

    }
}