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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.check_numbers)

        db = SQL_helpler(this).writableDatabase

        //綁定元件
        val tv_YesOrNo=findViewById<TextView>(R.id.tv_YesOrNo)
        val tv_number=findViewById<TextView>(R.id.tv_number)
        val tv_award=findViewById<TextView>(R.id.tv_award)
        val tv_bonus=findViewById<TextView>(R.id.tv_bonus)
        val ed_number=findViewById<EditText>(R.id.ed_number)
        val btn_return=findViewById<Button>(R.id.btn_return)
        val btn_scan_1 = findViewById<Button>(R.id.btn_scan_1)
//        val test1=findViewById<TextView>(R.id.test1)
//        val test2=findViewById<TextView>(R.id.test2)

        //中獎號碼輸出
        lateinit var query: String
//        query = "SELECT prize_id FROM prize WHERE date == '${time}'"
        query = "SELECT prize_id FROM prize WHERE date == '11009'"
        val c = db.rawQuery(query, null)
        c.moveToFirst()

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
                for (i in 0 until 6){
                    if (ed_number.length() == 3) {

//                        test1.text = ed_number.text
//                        test2.text = (c.getString(0).toInt()%1000).toString()

                        if (ed_number.text.toString().toInt() == c.getString(0).toInt()%1000){
                            showToast("${ed_number.text}有中獎，快輸入完整發票號碼吧！")
//                            startActivity(Intent(this, CheckNumbers2Activity::class.java))
                        } else{
                            showToast("${ed_number.text}未中獎")
                        }
                    }
                }
                ed_number.text.clear()
            }
            override fun afterTextChanged(s: Editable) {

            }
        })

        btn_scan_1.setOnClickListener{
            startActivity(Intent(this, QrcodeActivity::class.java))
        }
        btn_return.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun showToast(text: String) =
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}