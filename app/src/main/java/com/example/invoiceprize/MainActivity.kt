package com.example.invoiceprize

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //綁定元件
        val btn_reward = findViewById<Button>(R.id.btn_back)

        //前往對獎專區
        btn_reward.setOnClickListener{
            startActivity(Intent(this,RewardActivity::class.java))
        }
    }
}