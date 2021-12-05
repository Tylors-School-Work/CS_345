package com.example.pg6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private lateinit var webAddressET: EditText
    private lateinit var goToWebAddrBTN: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webAddressET = findViewById(R.id.web_address_ET)
        goToWebAddrBTN = findViewById(R.id.go_to_web_addr_btn)

        goToWebAddrBTN.setOnClickListener {
            val intent = WebActivity.newIntent(this, webAddressET.text.toString())
            startActivity(intent)
        }
    }
}