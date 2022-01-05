package com.example.invoiceprize

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.invoiceprize.prize.*

class CheckNumbers2Activity : AppCompatActivity() {
    private lateinit var db: SQLiteDatabase
    private lateinit var rdb: SQLiteDatabase
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
    var time: String = "0"
    var index: Int = 0
    var ed_number: String = "0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.check_numbers2)
        intent?.extras?.let {
            time = "${it.getString("date")}"
            index = it.getInt("index")
            ed_number = "${it.getString("user")}"
        }
        if (index > 5){
            index = index - 6
        }

        db = SQL_helpler(this).writableDatabase
        rdb = SQL_helpler2(this).writableDatabase

        //綁定元件
        val ed_invoiceNumber = findViewById<EditText>(R.id.ed_invoiceNumber)
        val tv_init = findViewById<TextView>(R.id.tv_init)
        val tv_test2 = findViewById<TextView>(R.id.tv_test2)

        tv_init.text = ed_number
        tv_init.textSize = 35F

        //中獎號碼輸出
        lateinit var query: String
        query = "SELECT * FROM prize WHERE date like '${time}'"
        val c = db.rawQuery(query, null)

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
                if (ed_invoiceNumber.length() == 5) {
                    c.moveToPosition(index)
                    val user = ed_invoiceNumber.text.toString()+ed_number
                    val bool = compare(user.toInt(), c.getString(0).toInt())
                    if (!bool) {
                        rdb.execSQL(
                            "INSERT INTO passbook(invoice_id, date, money, award)" +
                                    "VALUES('${user}','${time}','${data[9].bonus}','${data[9].name}')"
                        )
                        showToast("${user}未中獎")
                        ed_invoiceNumber.text.clear()
                        val intent = Intent(applicationContext, CheckNumbersActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            }
            override fun afterTextChanged(s: Editable) {

            }
        })
    }

    //比對中獎
    fun compare (enter: Int, prize: Int): Boolean {
        rdb = SQL_helpler2(this).writableDatabase
        val ed_invoiceNumber = findViewById<EditText>(R.id.ed_invoiceNumber)

        if (index == 0) {
            if (enter == prize) {
                rdb.execSQL(
                    "INSERT INTO passbook(invoice_id, date, money, award)" +
                            "VALUES('${enter}','${time}','${data[0].bonus}','${data[0].name}')"
                )
                showToast("${enter}中${data[0].name}")
                ed_invoiceNumber.text.clear()
                val intent = Intent(applicationContext, CheckNumbersActivity::class.java)
                startActivity(intent)
                finish()
                return true
            }
        } else if (index == 1) {
            if (enter == prize) {
                rdb.execSQL(
                    "INSERT INTO passbook(invoice_id, date, money, award)" +
                            "VALUES('${enter}','${time}','${data[1].bonus}','${data[1].name}')"
                )
                showToast("${enter}中${data[0].name}")
                ed_invoiceNumber.text.clear()
                val intent = Intent(applicationContext, CheckNumbersActivity::class.java)
                startActivity(intent)
                finish()
                return true
            }
        } else if (index == 2 || index == 3 || index == 4) {
            var number = 2
            val counter = 100000000
            number = firstPrize(enter, prize, counter, number)
            if (number != 9) {
                rdb.execSQL(
                    "INSERT INTO passbook(invoice_id, date, money, award)" +
                            "VALUES('${enter}','${time}','${data[number].bonus}','${data[number].name}')"
                )
                showToast("${enter}中${data[number].name}")
                ed_invoiceNumber.text.clear()
                val intent = Intent(applicationContext, CheckNumbersActivity::class.java)
                startActivity(intent)
                finish()
                return true
            }
        } else if (index == 5 || index == 6) {
            if (enter % 1000 === prize) {
                rdb.execSQL(
                    "INSERT INTO passbook(invoice_id, date, money, award)" +
                            "VALUES('${enter}','${time}','${data[8].bonus}','${data[8].name}')"
                )
                showToast("${enter}中${data[8].name}")
                ed_invoiceNumber.text.clear()
                val intent = Intent(applicationContext, CheckNumbersActivity::class.java)
                startActivity(intent)
                finish()
                return true
            }
        }
        return false

    }

    //針對頭獎不同末幾號而判斷
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