package com.example.invoiceprize

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.text.Editable

import android.text.TextWatcher




class RewardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reward)

        //綁定元件
        val btn_back = findViewById<Button>(R.id.btn_back)
        val ed_number = findViewById<EditText>(R.id.ed_number)

        //回上一頁
        btn_back.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
        }

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
                if (ed_number.length() === 3) {
                    showToast("${ed_number.text}")
                    ed_number.text.clear()
                }
            }
            override fun afterTextChanged(s: Editable) {

            }
        })


    }
    private fun showToast(text: String) =
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}