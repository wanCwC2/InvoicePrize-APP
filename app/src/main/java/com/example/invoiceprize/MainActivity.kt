package com.example.invoiceprize

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.os.StrictMode.VmPolicy
import android.widget.Button
import android.widget.TextView
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    private lateinit var db: SQLiteDatabase
    private lateinit var rdb: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = SQL_helpler(this).writableDatabase
        rdb = SQL_helpler2(this).writableDatabase

        //綁定元件
        val btn_reward = findViewById<Button>(R.id.btn_reward)
        val btn_win = findViewById<Button>(R.id.btn_win)
        val btn_passbook = findViewById<Button>(R.id.btn_passbook)
        val btn_sql = findViewById<Button>(R.id.btn_sql)
        val tv_test = findViewById<TextView>(R.id.tv_test)

        //錯誤問題：android.os.NetworkOnMainThreadException之解決方式
        StrictMode.setThreadPolicy(
            ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()
                .penaltyLog()
                .build()
        )
        StrictMode.setVmPolicy(
            VmPolicy.Builder()
                .detectLeakedClosableObjects()
                .penaltyLog()
//                .penaltyDeath()
                .build()
        )

        //把對獎年月都儲存在資料庫中
        val time = Time()
        time.run()
        lateinit var query: String
        query = "SELECT * FROM prize WHERE date like '${time.timeNow}'"
        var c = db.rawQuery(query, null)
        try {
            c.moveToFirst()
            val s = c.getString(c.getColumnIndex("prize_id"))
        }catch (e: Exception){
            catchdata("${time.timeNow}")
        }
        query = "SELECT * FROM prize WHERE date like '${time.timeBef}'"
        c = db.rawQuery(query, null)
        try {
            c.moveToFirst()
            val s = c.getString(c.getColumnIndex("prize_id"))
        }catch (e: Exception){
            catchdata("${time.timeBef}")
        }

        c.close()
//        tv_test.text = time.timeBef

        //前往中獎專區
        btn_win.setOnClickListener{
            startActivity(Intent(this,WinActivity::class.java))
        }

        //前往對獎專區
        btn_reward.setOnClickListener{
            startActivity(Intent(this,CheckNumbersActivity::class.java))
        }

        //前往發票存摺
        btn_passbook.setOnClickListener{
            startActivity(Intent(this,PassbookActivity::class.java))
        }

        //清空資料庫
        btn_sql.setOnClickListener{
            db.execSQL("DELETE FROM prize")
            rdb.execSQL("DELETE FROM passbook")
        }
    }

    fun catchdata(date: String) {
        var urlData: String? = null
        val site = "https://www.etax.nat.gov.tw/etw-main/ETW183W2_${date}/"
        try {
            val url = URL(site)
            val conn = url.openConnection() as HttpURLConnection
            val `is` = conn.inputStream
            val bis = BufferedReader(InputStreamReader(`is`))
            var line = bis.readLine()
            val data = StringBuffer()
            while (line != null) {
                data.append(line)
                line = bis.readLine()
            }
            urlData = data.toString()
            parser(urlData!!, "${date}")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun parser(data: String, date: String) {
        db = SQL_helpler(this).writableDatabase
        var name = arrayOf(
            "specialPrize", "grandPrize", "firstPrizeA",
            "firstPrizeB", "firstPrizeC", "addSixPrize"
        )
        var temp: String? = null
        var start = 0
        var end = 0
        var counter = 0
        val len = name.size
        do {
            if (counter <= len - 2 && counter >= len - 4) {
                start = data.indexOf("<div class=\"col-12 mb-3\">", end + 1)
                end = data.indexOf("</div>", start + 1)
                temp = data.substring(end - 23, end - 15)
//                prize.add(temp)
                db.execSQL(
                    "INSERT INTO prize(prize_id, date, name)" +
                            "VALUES('${temp}','${date}','${name[counter]}')"
                )

            } else if (counter == len - 1) {
                start = data.indexOf("<div class=\"col-12 mb-3\">", end + 1)
                end = data.indexOf("</div>", start + 1)
                temp = data.substring(end - 18, end - 15)
//                prize.add(temp)
                db.execSQL(
                    "INSERT INTO prize(prize_id, date, name)" +
                            "VALUES('${temp}','${date}','${name[counter]}')"
                )
                counter++
                start = data.indexOf("<div class=\"col-12 mb-3\">", end + 1)
                if (start != -1) {
                    end = data.indexOf("</div>", start + 1)
                    temp = data.substring(end - 23, end - 15)
//                    prize.add(temp)
                    db.execSQL(
                        "INSERT INTO prize(prize_id, date, name)" +
                                "VALUES('${temp}','${date}','${name[counter]}')"
                    )
                }
            } else {
                start = data.indexOf(
                    "<div class=\"col-12 mb-3\">",
                    end + 1
                )
                end = data.indexOf("</div>", start + 1)
                temp = data.substring(end - 23, end - 15)
//                prize.add(temp)
                db.execSQL(
                    "INSERT INTO prize(prize_id, date, name)" +
                            "VALUES('${temp}','${date}','${name[counter]}')"
                )
            }
            counter++
        } while (counter < len)
    }
}