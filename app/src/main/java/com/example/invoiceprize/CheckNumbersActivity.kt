package com.example.invoiceprize

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class CheckNumbersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.check_numbers)
        val tv_YesOrNo=findViewById<TextView>(R.id.tv_YesOrNo)
        val tv_number=findViewById<TextView>(R.id.tv_number)
        val tv_award=findViewById<TextView>(R.id.tv_award)
        val tv_bonus=findViewById<TextView>(R.id.tv_bonus)
        val ed_number=findViewById<EditText>(R.id.ed_number)
        val btn_return=findViewById<Button>(R.id.btn_return)
        val btn_scan_1 = findViewById<Button>(R.id.btn_scan_1)
        btn_scan_1.setOnClickListener{
            startActivity(Intent(this, QrcodeActivity::class.java))
        }
        btn_return.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}