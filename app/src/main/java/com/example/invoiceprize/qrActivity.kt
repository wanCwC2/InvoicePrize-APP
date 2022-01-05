package com.example.invoiceprize

import android.content.Intent
import android.content.pm.PackageManager
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode

private const val CAMERA_REQUEST_CODE = 101

class qrActivity : AppCompatActivity() {

    private lateinit var db: SQLiteDatabase
    private lateinit var rdb: SQLiteDatabase
    private lateinit var codeScanner: CodeScanner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qrcode)

        db = SQL_helpler(this).writableDatabase
        rdb = SQL_helpler2(this).writableDatabase

        val btn_fast_2= findViewById<Button>(R.id.btn_fast_2)
        val btn_123=findViewById<Button>(R.id.btn_123)
        val btn_return=findViewById<Button>(R.id.btn_return)
        val tv_information_2 =findViewById<TextView>(R.id.tv_information_2)
        val btn_scan_2 = findViewById<Button>(R.id.btn_scan_2)
        btn_fast_2.setOnClickListener{
            startActivity(Intent(this, CheckNumbersActivity::class.java))
        }
        btn_123.setOnClickListener{
            startActivity(Intent(this, ScanNumberActivity::class.java))
        }
        btn_return.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }
        btn_scan_2.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }

        setupPermissions()
        codeScanner()
    }

    private fun codeScanner(){
        val qrcode_scanner = findViewById<CodeScannerView>(R.id.qrcode_scanner)
        codeScanner = CodeScanner(this, qrcode_scanner)
        val tv_information_2 =findViewById<TextView>(R.id.tv_information_2)


        codeScanner.apply {
            camera = CodeScanner.CAMERA_BACK
            formats = CodeScanner.ALL_FORMATS

            autoFocusMode = AutoFocusMode.SAFE
            scanMode = ScanMode.CONTINUOUS
            isAutoFocusEnabled = true
            isFlashEnabled = false

            decodeCallback = DecodeCallback {
                runOnUiThread{
                    tv_information_2.text = it.text
                }
            }

            errorCallback = ErrorCallback {
                runOnUiThread{
                    Log.e("Main", "Camera initialization error: ${it.message}")
                }
            }
        }

        qrcode_scanner.setOnClickListener {
            codeScanner.startPreview()
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }

    private fun setupPermissions(){
        val permission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)

        if (permission != PackageManager.PERMISSION_GRANTED){
            makeRequest()
        }

    }
    private fun makeRequest(){
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), CAMERA_REQUEST_CODE)

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            CAMERA_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "You need the camera permission to be able to use this app!", Toast.LENGTH_SHORT).show()
                }else{
                    //successful
                }
            }
        }
    }


}