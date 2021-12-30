package com.example.invoiceprize

import android.database.sqlite.SQLiteDatabase
import java.io.IOException
import java.net.MalformedURLException
import java.io.InputStreamReader
import java.io.BufferedReader
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.ArrayList

class CatchData {
    private lateinit var db: SQLiteDatabase


    var name = arrayOf(
        "specialPrize", "grandPrize", "firstPrizeA",
        "firstPrizeB", "firstPrizeC", "addSixPrize"
    )
    var prize: MutableList<String> = ArrayList()
    var urlData: String? = null

    fun catchdata(year: String, month: String) {

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
            parser(urlData!!)
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun parser(data: String) {

        db = SQL_helpler(this).writableDatabase
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

            } else if (counter == len - 1) {
                start = data.indexOf("<div class=\"col-12 mb-3\">", end + 1)
                end = data.indexOf("</div>", start + 1)
                temp = data.substring(end - 18, end - 15)
//                prize.add(temp)
                counter++
                start = data.indexOf("<div class=\"col-12 mb-3\">", end + 1)
                if (start != -1) {
                    end = data.indexOf("</div>", start + 1)
                    temp = data.substring(end - 23, end - 15)
//                    prize.add(temp)
                }
            } else {
                start = data.indexOf(
                    "<div class=\"col-12 mb-3\">",
                    end + 1
                )
                end = data.indexOf("</div>", start + 1)
                temp = data.substring(end - 23, end - 15)
//                prize.add(temp)
            }
            counter++
        } while (counter < len)
    }

    operator fun get(year: String, month: String): MutableList<String> {
        catchdata(year, month)
        return prize
    }
}