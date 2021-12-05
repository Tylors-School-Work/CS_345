package com.example.pg6

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.TextView
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class WebActivity : AppCompatActivity() {

    private lateinit var webAddress: String
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        webView = findViewById(R.id.web_view)
        webAddress = intent.getStringExtra(WEB_ADDRESS)!!

        webView.settings.javaScriptEnabled = true
        webView.loadUrl(webAddress)

    }

    private fun readWebView() {
        val obj = URL(webAddress)
        obj.content
        val connection:HttpURLConnection = obj.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        if(connection.responseCode == HttpURLConnection.HTTP_OK) {
            val infile = BufferedReader(InputStreamReader(connection.inputStream))
            while(true) {
                val read = infile.read()
                if(read == -1) break

            }
        }
    }

    companion object {
        const val WEB_ADDRESS = "com.example.pg6.web_address"
        fun newIntent(context: Context, webAddress: String): Intent {
            return Intent(context, WebActivity::class.java).apply {
                putExtra(WEB_ADDRESS, webAddress)
            }
        }
    }
}