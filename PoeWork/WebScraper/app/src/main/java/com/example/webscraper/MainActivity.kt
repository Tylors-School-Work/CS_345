package com.example.webscraper

import android.content.Context
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.widget.TextView
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    var webthread = false
    var context: Context? = null
    var tmpdir:String? = null


    private lateinit var read_TV: TextView

    private var textFromWeb = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        var web1: WebView? = null
//        web1 = findViewById (R.id.web1)
//        web1!!.settings.javaScriptEnabled = true
//        web1!!.loadUrl ("http://philos.nmu.edu/index.html")

        read_TV = findViewById(R.id.read)

        val executor = Executors.newSingleThreadExecutor()//create a new thread
        context = this@MainActivity
        tmpdir = context!!.cacheDir.toString()

        executor.execute {
            textFromWeb = doInBackground() // start it running
            read_TV.text = textFromWeb
        }

        while (!webthread);

//        val infile:BufferedReader = BufferedReader (InputStreamReader(FileInputStream("$tmpdir/tempweb.html")))
//        while (true) {
//            val c:Int = infile.read()
//            if (c==-1) break
////            textFromWeb += c.toString()
////            read_TV.text = textFromWeb
//            print (c.toChar())
//        }
//        infile.close()


    }

        private fun doInBackground (): String {
            var readText = ""
            val obj = URL ("http://philos.nmu.edu/cs345-01-21f/sample/notes20210930.txt")
            val con:HttpURLConnection = obj.openConnection () as HttpURLConnection
            con.requestMethod = "GET"
            val responseCode = con.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                val infile = BufferedReader (InputStreamReader (con.inputStream))
                val outfile = PrintWriter ("$tmpdir/tempweb.html")
                while (true) {
                    val c:Int = infile.read()
                    if (c==-1) break
                    readText += c.toChar()
                    outfile.print(c.toChar())
                }
                infile.close()
                outfile.close()
            }
            webthread = true
            return readText
        }

    override fun onResume () {
        super.onResume()
        try {
            val mp = MediaPlayer()
            mp.setDataSource ("http://philos.nmu.edu/polka/polka.mp3")
            mp.prepare()
            mp.start()
            try {
                TimeUnit.SECONDS.sleep (10)
            } catch (e:Exception) {
            }
            mp.pause ()
            try {
                TimeUnit.SECONDS.sleep (5)
            } catch (e:Exception) {
            }
            mp.start ()
            try {
                TimeUnit.SECONDS.sleep (5)
            } catch (e:Exception) {
            }
            mp.reset()
            mp.setDataSource ("http://philos.nmu.edu/polka/polka.mp3")
            mp.prepare()
            mp.start()

        } catch (e:Exception) {
            e.printStackTrace()
        }

    }

}

/*
HTTP:  GET and POST
  GET:  the internal name of the command that requests web page information from a server.
  The client sends the GET command, the server sends back the HTML code, and the client parses
  that code and draws a pretty website on the screen conforming to that code.

  POST is when you send information to the web, perhaps in the form of an online form.

  Android has a restriction--the Kotlin language does not have the restriction--all network
  requests must be made from outside the main thread.

  A thread is a single execution path in a process.  A process can have any number of threads.  This
  means that multiple threads can be running in the process simultaneously, all with
  access to the same memory, but executing on its own path.  It's a way to emulate parallelism.
  Your program can do things at once.

  Just as a process can create another process and both can run simultaneously (e.g. the fork() command in Linux,
  A thread can create other threads within the process and all run simultaneously.

  The difference is that all threads share the same memory within the process, but processes
  each have their own memory.
 */