package com.example.invoiceprize

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView

class PassbookActivity : AppCompatActivity() {
    private lateinit var rdb: SQLiteDatabase
    private var items: ArrayList<String> = ArrayList()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_passbook)

        rdb = SQL_helpler2(this).writableDatabase

        //綁定元件
        val btn_back3 = findViewById<Button>(R.id.btn_back3)
        val tv_detail = findViewById<TextView>(R.id.tv_detail)
        val resultList = findViewById<ListView>(R.id.resultList)

        //宣告 list用的Adapter 並連結 resultList
        adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,items)
        findViewById<ListView>(R.id.resultList).adapter = adapter


        //輸出發票中獎資訊
        lateinit var query: String
        query = "SELECT * FROM passbook WHERE date == '11009'"
        val c = rdb.rawQuery(query, null)
        c.moveToFirst() //從第一筆開始輸出
        items.clear() //清空舊資料
        if(c.count>0) {
            //由DB讀取資料項,放入resultList
            for (i in 0 until c.count) {
                //加入新資料
                items.add(
                    "${c.getString(0)}, ${c.getString(2)}," +
                            " ${c.getString(3)}"
                )
                c.moveToNext() //移動到下一筆
            }
            adapter.notifyDataSetChanged() //更新列表資料
            c.close() //關閉 Cursor
        } else {
            items.add(
                "尚未有資料"
            )
        }

        //回上一頁
        btn_back3.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}