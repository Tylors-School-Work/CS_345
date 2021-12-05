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
 *
 *   -- Should be all set now, fully commented everything and removed redundant/unnecessary code
 *   -- I also added something new in this submission:
 *      -- There are now two buttons in my "FragmentMovie" fragment
 *          -- One to change the selected movies star rating
 *          -- And another to return to my "FragmentMovieList" fragment
 *      -- Now when the user enters a new valid star rating the displayed star rating in the fragment will be updated
 *          -- If the user enters an invalid rating, they will be told so
 *      -- The star rating image will also be updated depending on what the user chooses to rate the movie as
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