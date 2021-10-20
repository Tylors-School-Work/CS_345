package com.example.pg4

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.pg4.data.Movie
import com.example.pg4.fragments.FragmentMovie
import com.example.pg4.fragments.FragmentMovieList
import java.util.*

/**
 *
 *   - So far this code does allow me to pick a movie, see more data about it and change the star-rating
 *   - But it's definitely not finished, I'm very close but the way I'm handling changing the star rating of each movie is problematic
 *   - Going to turn it in "as is", but will be turning it back in tomorrow 10/21/2021 fixed up
 *
* */

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
    }

    // Ended up not using this, but when I touch it up tonight (10/19/2021) I may do something with it
    companion object {
        val EXTRA_MOVIE_ID = "com.example.pg4.movie_id"

        fun newIntent(context: Context, mId: UUID?): Intent {
            val intent = Intent(context, MovieSelectedActivity::class.java).apply {
                putExtra(MovieSelectedActivity.MOVIE_ID, mId)
            }
            return intent
        }
    }
}