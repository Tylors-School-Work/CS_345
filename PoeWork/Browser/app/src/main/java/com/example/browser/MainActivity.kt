package com.example.browser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView

class MainActivity : AppCompatActivity() {

    var web1: WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        web1 = findViewById (R.id.web1)
        web1!!.settings.javaScriptEnabled = true
        web1!!.loadUrl ("http://philos.nmu.edu/index.html")
    }
}