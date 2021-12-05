package com.example.mp3stuff

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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