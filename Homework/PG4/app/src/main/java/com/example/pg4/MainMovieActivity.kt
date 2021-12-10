/*
*       Tylor J. Hanshaw
*
*   - My main activity, the activity that is ran on start up, hosts the FragmentMovieList
*   - Also initiates the transition from this activity to MovieSelectedActivity
* */

package com.example.pg4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.pg4.fragments.FragmentMovieList
import java.util.*

/**
 *                          Brief Description of the App
 *
 *   -- This program uses two activities that each host one fragment respectively
 *   -- You are able to rate a given movie on a rating from 0-5 "star" rating
 *
* */

// Used for debugging/logging
private const val LOG = "MainMovieActivity"

class MainMovieActivity : AppCompatActivity(), FragmentMovieList.Callbacks {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_movie)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.main_fragment_container)

        if(currentFragment == null) {
            val fragment = FragmentMovieList.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.main_fragment_container, fragment)
                .commit()
        }
    }

    override fun onMovieSelected(movieId: UUID?) {
        val intent = MovieSelectedActivity.newIntent(this, movieId)
        startActivity(intent)
        Log.d(LOG, "Called from FragmentMovieList")
    }
}