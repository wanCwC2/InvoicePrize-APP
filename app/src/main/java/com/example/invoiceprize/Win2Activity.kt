package com.example.invoiceprize

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Win2Activity : AppCompatActivity() {
    private lateinit var db: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_win2)

        db = SQL_helpler(this).writableDatabase

        //綁定元件
        val btn_back = findViewById<Button>(R.id.btn_back2)
        val tv_specialPrize2 = findViewById<TextView>(R.id.tv_specialPrize2)
        val tv_grandPrize2 = findViewById<TextView>(R.id.tv_grandPrize2)
        val tv_firstPrizeA2 = findViewById<TextView>(R.id.tv_firstPrizeA2)
        val tv_firstPrizeB2= findViewById<TextView>(R.id.tv_firstPrizeB2)
        val tv_firstPrizeC2= findViewById<TextView>(R.id.tv_firstPrizeC2)
        val tv_addSixPrize2= findViewById<TextView>(R.id.tv_addSixPrize2)
        val btn_month3= findViewById<Button>(R.id.btn_month3)
        val btn_month4 = findViewById<Button>(R.id.btn_month4)
        var name = arrayOf(
            "specialPrize2", "grandPrize2", "firstPrizeA2",
            "firstPrizeB2", "firstPrizeC2", "addSixPrize2"
        )

        val time = Time()
        time.run()

        //顯示按鈕名稱
        btn_month3.text = "${time.timeBefYear}年${time.timeBefMonth}-${time.timeBefMonth1}月"
        btn_month4.text = "${time.timeNowYear}年${time.timeNowMonth}-${time.timeNowMonth1}月"

        //號碼顯示
        lateinit var query: String
        query = "SELECT * FROM prize WHERE date like '${time.timeBef}'"
        val c = db.rawQuery(query, null)
        c?.moveToFirst()
        if (c != null) {
            val s = c.getString(c.getColumnIndex("prize_id"))
            tv_specialPrize2.text = s
        }
        if (c != null) {
            c.moveToNext()
            tv_grandPrize2.text = "${c.getString(0)}"
        }
        if (c != null) {
            c.moveToNext()
            tv_firstPrizeA2.text = "${c.getString(0)}"
        }
        if (c != null) {
            c.moveToNext()
            tv_firstPrizeB2.text = "${c.getString(0)}"
        }
        if (c != null) {
            c.moveToNext()
            tv_firstPrizeC2.text = "${c.getString(0)}"
        }
        if (c != null) {
            c.moveToNext()
            tv_addSixPrize2.text = "${c.getString(0)}"
        }
        c.close()

        //去另外一期
        btn_month4.setOnClickListener{
            startActivity(Intent(this,WinActivity::class.java))
        }

        //回上一頁
        btn_back.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}
