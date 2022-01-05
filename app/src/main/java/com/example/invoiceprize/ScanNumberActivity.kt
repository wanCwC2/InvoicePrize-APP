package com.example.invoiceprize

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ScanNumberActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.scan_number)
        val btn_fast_3= findViewById<Button>(R.id.btn_fast_3)
        val btn_QR=findViewById<Button>(R.id.btn_QR)
        val btn_return=findViewById<Button>(R.id.btn_return)
        val tv_information_2=findViewById<TextView>(R.id.tv_information_2)
        btn_fast_3.setOnClickListener{
            startActivity(Intent(this,CheckNumbersActivity::class.java))
        }
        btn_QR.setOnClickListener{
            startActivity(Intent(this,qrActivity::class.java))
        }
        btn_return.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}