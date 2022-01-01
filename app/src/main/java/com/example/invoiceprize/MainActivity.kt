package com.example.invoiceprize

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class MainActivity : AppCompatActivity() {
    private lateinit var db: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = SQL_helpler(this).writableDatabase

        //綁定元件
        val btn_reward = findViewById<Button>(R.id.btn_reward)
        val btn_win = findViewById<Button>(R.id.btn_win)
        val tv_specialPrize = findViewById<TextView>(R.id.tv_test) //測試用
        val btn_db = findViewById<Button>(R.id.btn_db)

        //把對獎年月都儲存在資料庫中
//        if (db.execSQL("SELECT * FROM prize") == null) {
//            val cd = CatchData()
        val cal: Calendar = Calendar.getInstance()
        cal.add(Calendar.MONTH, -2)
        cal.add(Calendar.DATE, -25)
        var timeNowMonth: String = SimpleDateFormat("MM").format(cal.getTime())
        if (timeNowMonth.toInt() / 2 == 0) {
            cal.add(Calendar.MONTH, -1)
        }
        var timeNowYear = SimpleDateFormat("yyyy").format(cal.getTime())
        timeNowMonth = SimpleDateFormat("MM").format(cal.getTime())
        Thread{
            catchdata(timeNowYear, timeNowMonth)
        }.start()
        tv_specialPrize.text = db.execSQL("SELECT prize_id " +
                "FROM prize " +
                "WHERE name like 'specialPrize'").toString()

        //前往中獎專區
        btn_win.setOnClickListener{
            startActivity(Intent(this,WinActivity::class.java))
        }

        //前往對獎專區
        btn_reward.setOnClickListener{
            startActivity(Intent(this,RewardActivity::class.java))
        }
    }

    fun catchdata(year: String, month: String) {
        var urlData: String? = null
        val site = "https://www.etax.nat.gov.tw/etw-main/ETW183W2_$year$month/"
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
            parser(urlData!!, year+month)
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
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
                    "INSERT INTO addressBooks(id, name,phone,address)" +
                            "VALUES('${temp}','${date}','${name[counter]}')"
                )

            } else if (counter == len - 1) {
                start = data.indexOf("<div class=\"col-12 mb-3\">", end + 1)
                end = data.indexOf("</div>", start + 1)
                temp = data.substring(end - 18, end - 15)
//                prize.add(temp)
                db.execSQL(
                    "INSERT INTO addressBooks(id, name,phone,address)" +
                            "VALUES('${temp}','${date}','${name[counter]}')"
                )
                counter++
                start = data.indexOf("<div class=\"col-12 mb-3\">", end + 1)
                if (start != -1) {
                    end = data.indexOf("</div>", start + 1)
                    temp = data.substring(end - 23, end - 15)
//                    prize.add(temp)
                    db.execSQL(
                        "INSERT INTO addressBooks(id, name,phone,address)" +
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
                    "INSERT INTO addressBooks(id, name,phone,address)" +
                            "VALUES('${temp}','${date}','${name[counter]}')"
                )
            }
            counter++
        } while (counter < len)
    }
}