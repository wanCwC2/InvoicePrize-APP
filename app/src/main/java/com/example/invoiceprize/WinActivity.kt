package com.example.invoiceprize

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
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
        val btn_month2 = findViewById<Button>(R.id.btn_month2)
        var name = arrayOf(
            "specialPrize", "grandPrize", "firstPrizeA",
            "firstPrizeB", "firstPrizeC", "addSixPrize"
        )

        //中獎號碼日期（目前只能前一次的）
        val cal: Calendar = Calendar.getInstance()
        cal.add(Calendar.MONTH, -2)
        cal.add(Calendar.DATE, -25)
        var timeNowMonth: String = SimpleDateFormat("MM").format(cal.getTime())
        if (timeNowMonth.toInt()%2 == 0) {
            cal.add(Calendar.MONTH, -1)
        }
        var timeNowYear = SimpleDateFormat("yyyy").format(cal.getTime())
        timeNowMonth = SimpleDateFormat("MM").format(cal.getTime())
        var time = SimpleDateFormat("yyyyMM").format(cal.getTime())

        //顯示按鈕名稱
        btn_month2.text = "${time}"

        //號碼顯示
        lateinit var query: String
//        query = "SELECT prize_id FROM prize WHERE date == '${time}'"
//        query = "SELECT prize_id FROM prize WHERE date == '11009'"
        query = "SELECT * FROM prize"
        val c = db.rawQuery(query, null)
        c?.moveToFirst()
        if (c != null) {
            tv_specialPrize.text = "${c.getString(0)}"
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

        //回上一頁
        btn_back.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
        }

    }
}