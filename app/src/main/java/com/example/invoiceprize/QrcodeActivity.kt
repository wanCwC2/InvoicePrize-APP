package com.example.invoiceprize

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class QrcodeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qrcode)
        val btn_fast_2= findViewById<Button>(R.id.btn_fast_2)
        val btn_123=findViewById<Button>(R.id.btn_123)
        val btn_return=findViewById<Button>(R.id.btn_return)
        val tv_information_1=findViewById<TextView>(R.id.tv_information_2)
        btn_fast_2.setOnClickListener{
            startActivity(Intent(this, CheckNumbersActivity::class.java))
        }
        btn_123.setOnClickListener{
            startActivity(Intent(this, ScanNumberActivity::class.java))
        }
        btn_return.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}