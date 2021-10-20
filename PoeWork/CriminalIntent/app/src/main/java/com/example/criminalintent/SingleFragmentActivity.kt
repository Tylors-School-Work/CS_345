package com.example.criminalintent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager

abstract class SingleFragmentActivity : FragmentActivity() {

    protected abstract fun createFragment():Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d ("SINGLEFRAGMENTACTIVITY","onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        val fm: FragmentManager? = supportFragmentManager
        var fragment: Fragment? = fm!!.findFragmentById(R.id.fragment_container)
        if (fragment==null) {
            fragment = createFragment()
            fm.beginTransaction().add(R.id.fragment_container,fragment).commit()
        }
    }

    override fun onStart() {
        Log.d ("SINGLEFRAGMENTACTIVITY","onStart")
        super.onStart()
    }
    override fun onRestart() {
        Log.d ("SINGLEFRAGMENTACTIVITY","onRestart")
        super.onRestart()
    }
    override fun onPause() {
        Log.d ("SINGLEFRAGMENTACTIVITY","onPause")
        super.onPause()
    }
    override fun onResume() {
        Log.d ("SINGLEFRAGMENTACTIVITY","onResume")
        super.onResume()
    }
    override fun onDestroy() {
        Log.d ("SINGLEFRAGMENTACTIVITY","onDestroy")
        super.onDestroy()
    }
    override fun onStop() {
        Log.d ("SINGLEFRAGMENTACTIVITY","onStop")
        super.onStop()
    }
}
