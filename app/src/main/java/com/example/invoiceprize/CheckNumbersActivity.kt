package com.example.invoiceprize

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class CheckNumbersActivity : AppCompatActivity() {
    private lateinit var db: SQLiteDatabase
    private lateinit var rdb: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.check_numbers)

        db = SQL_helpler(this).writableDatabase
        rdb = SQL_helpler2(this).writableDatabase

        //綁定元件
        val tv_YesOrNo=findViewById<TextView>(R.id.tv_YesOrNo)
        val tv_number=findViewById<TextView>(R.id.tv_number)
        val tv_award=findViewById<TextView>(R.id.tv_award)
        val tv_bonus=findViewById<TextView>(R.id.tv_bonus)
        val ed_number=findViewById<EditText>(R.id.ed_number)
        val btn_return=findViewById<Button>(R.id.btn_return)
        val btn_scan_1 = findViewById<Button>(R.id.btn_scan_1)

        //中獎顯示底下
        lateinit var query2: String
//        query2 = "SELECT * FROM passbook WHERE date == '11009'"
        query2 = "SELECT * FROM passbook"
        val c2 = rdb.rawQuery(query2, null)
        if (c2 != null && c2.moveToLast()){
            tv_number.text = c2.getString(0)
            tv_award.text = c2.getString(3)
            tv_bonus.text = c2.getString(2)
        }
        c2.close()

        //中獎號碼輸出
        val time = Time()
        time.run()
        lateinit var query: String
//        query = "SELECT prize_id FROM prize WHERE date == '${time.timeNow}'"
        query = "SELECT * FROM prize"
        val c = db.rawQuery(query, null)

        //監聽輸入文字
        ed_number.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int,
                after: Int
            ) {

            }
            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int,
                count: Int
            ) {
                if (ed_number.length() == 3) {
                    var bool = false
                    c.moveToFirst()
                    for (i in 0 until c.count){
                        val user = ed_number.text.toString()
                        if (c != null && user.toInt() == ((c.getString(0).toInt())%1000)){
                            bool = true
                            showToast("${ed_number.text}有中獎，快輸入完整發票號碼吧！")
                            val b = Bundle()
                            b.putString("date", "${c.getString(1)}")
                            b.putInt("index",i)
                            b.putString("user","${ed_number.text}")
                            ed_number.text.clear()
                            val intent = Intent(applicationContext, CheckNumbers2Activity::class.java)
                            intent.putExtras(b)
                            startActivity(intent)
                            finish()
                            break
                        }
                        c.moveToNext()
                    }
                    if (!bool) {
                        showToast("${ed_number.text}未中獎")
                        ed_number.text.clear()
                    }
                }
            }
            override fun afterTextChanged(s: Editable) {

            }
        })

        btn_scan_1.setOnClickListener{
            startActivity(Intent(this, qrActivity::class.java))
        }

        btn_return.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun showToast(text: String) =
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}