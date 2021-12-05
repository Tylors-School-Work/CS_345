/*
*       Tylor J. Hanshaw
* */

package com.example.pg4

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pg4.fragments.FragmentMovie
import com.example.pg4.fragments.FragmentMovieList
import java.util.*

class MovieSelectedActivity : AppCompatActivity(), FragmentMovieList.Callbacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_selected)

        val movieId = intent.getSerializableExtra(MOVIE_ID) as UUID
        val currentFragment = supportFragmentManager.findFragmentById(R.id.secondary_fragment_container)

        // If there isn't an instance of the "FragmentMovie" then I will create a new one and navigate to it
        if(currentFragment == null) {
            val frags = FragmentMovie.newInstance(movieId)
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.secondary_fragment_container, frags)
                .commit()
        }
    }

    // This is used to navigate from "MainMovieActivity" to this activity
    // I implemented the interface "Callbacks" in "FragmentMovieList", this allows me to access and override the "onMovieSelected" method
    override fun onMovieSelected(movieId: UUID?) {
        val frags = FragmentMovie.newInstance(movieId!!)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.secondary_fragment_container, frags)
            .addToBackStack(null)
            .commit()
    }

    // Used for calling this activity and passing the data needed to it
    // I attach an extra which is the selected movies UUID
    companion object {
        val MOVIE_ID = "com.example.pg4.movie_id"
        fun newIntent(context: Context, mId: UUID?): Intent {
            val intent = Intent(context, MovieSelectedActivity::class.java).apply {
                putExtra(MOVIE_ID, mId)
            }
            return intent
        }
    }
}