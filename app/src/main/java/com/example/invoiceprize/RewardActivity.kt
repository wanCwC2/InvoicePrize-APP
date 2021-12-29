package com.example.invoiceprize

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

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
    }
}