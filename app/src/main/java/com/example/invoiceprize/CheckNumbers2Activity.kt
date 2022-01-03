package com.example.invoiceprize

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import com.example.invoiceprize.prize.*

class CheckNumbers2Activity : AppCompatActivity() {
    private lateinit var db: SQLiteDatabase
    private lateinit var rdb: SQLiteDatabase
    var bool = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.check_numbers2)

        db = SQL_helpler(this).writableDatabase
        rdb = SQL_helpler2(this).writableDatabase

        //綁定元件
        val ed_invoiceNumber = findViewById<EditText>(R.id.ed_invoiceNumber)

        val data: Array<Prize> = arrayOf<Prize>(
            SpecialPrize(),
            GrandPrize(),
            FirstPrize(),
            TwoPrize(),
            ThreePrize(),
            FourPrize(),
            FivePrize(),
            SixPrize(),
            AddsixPrize(),
            NotPrize()
        )

        //中獎號碼輸出
        lateinit var query: String
//        query = "SELECT prize_id FROM prize WHERE date == '${time}'"
        query = "SELECT prize_id FROM prize WHERE date == '11009'"
        val c = db.rawQuery(query, null)
        c.moveToFirst()

        //監聽輸入文字
        ed_invoiceNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int,
                after: Int
            ) {

            }
            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int,
                count: Int
            ) {
                if (ed_invoiceNumber.length() == 8) {
                    for (i in 0 until 6){
                        compare(i, ed_invoiceNumber.text.toString().toInt(), c.getString(0).toInt())
                        c.moveToFirst()
                    }
                    if (bool == 0) {
                        rdb.execSQL(
                            "INSERT INTO passbook(invoice_id, date, money, award)" +
                                    "VALUES('${ed_invoiceNumber.text}','11009','${data[9].bonus}','${data[9].name}')"
                        )
                        showToast("${ed_invoiceNumber.text}未中獎")
                        ed_invoiceNumber.text.clear()
                        val intent = Intent(applicationContext, CheckNumbers2Activity::class.java)
                        startActivity(intent)
                    }
                }
            }
            override fun afterTextChanged(s: Editable) {

            }
        })
    }

    fun compare (index: Int, enter: Int, prize: Int){
        rdb = SQL_helpler2(this).writableDatabase
        val data: Array<Prize> = arrayOf<Prize>(
            SpecialPrize(),
            GrandPrize(),
            FirstPrize(),
            TwoPrize(),
            ThreePrize(),
            FourPrize(),
            FivePrize(),
            SixPrize(),
            AddsixPrize(),
            NotPrize()
        )

        if (index == 0) {
            if (enter == prize) {
                rdb.execSQL(
                    "INSERT INTO passbook(invoice_id, date, money, award)" +
                            "VALUES('${enter}','11009','${data[0].bonus}','${data[0].name}')"
                )
            }
        } else if (index == 1) {
            if (enter == prize) {
                rdb.execSQL(
                    "INSERT INTO passbook(invoice_id, date, money, award)" +
                            "VALUES('${enter}','11009','${data[1].bonus}','${data[1].name}')"
                )
            }
        } else if (index == 2 || index == 3 || index == 4) {
            var number = 2
            val counter = 100000000
            number = firstPrize(enter, prize, counter, number)
            if (number != 9) {
                rdb.execSQL(
                    "INSERT INTO passbook(invoice_id, date, money, award)" +
                            "VALUES('${enter}','11009','${data[number].bonus}','${data[number].name}')"
                )
            }
        } else if (index == 5 || index == 6) {
            if (enter % 1000 === prize) {
                rdb.execSQL(
                    "INSERT INTO passbook(invoice_id, date, money, award)" +
                            "VALUES('${enter}','11009','${data[8].bonus}','${data[8].name}')"
                )
            }
        }
    }

    fun firstPrize(enter: Int, prize: Int, counter: Int, number: Int): Int {
        return if (counter < 1000) {
            9
        } else {
            if (enter % counter == prize % counter) {
                number
            } else {
                firstPrize(enter, prize, counter / 10, number + 1)
            }
        }
    }

    private fun showToast(text: String) =
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}