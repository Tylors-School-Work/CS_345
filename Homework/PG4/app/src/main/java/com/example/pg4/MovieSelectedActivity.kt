package com.example.pg4

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.pg4.MainMovieActivity.Companion.EXTRA_MOVIE_ID
import com.example.pg4.data.Movie
import com.example.pg4.data.MovieLab
import com.example.pg4.fragments.FragmentMovie
import com.example.pg4.fragments.FragmentMovieList
import java.util.*

class MovieSelectedActivity : AppCompatActivity(), FragmentMovieList.Callbacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_selected)

        val movieId = intent.getSerializableExtra(MOVIE_ID) as UUID

        val currentFragment = supportFragmentManager.findFragmentById(R.id.secondary_fragment_container)

        if(currentFragment == null) {
            val frags = FragmentMovie.newInstance(movieId)
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.secondary_fragment_container, frags)
                .commit()
        }

    }

    override fun onMovieSelected(movieId: UUID?) {
        val frags = FragmentMovie.newInstance(movieId!!)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.secondary_fragment_container, frags)
            .addToBackStack(null)
            .commit()
    }

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