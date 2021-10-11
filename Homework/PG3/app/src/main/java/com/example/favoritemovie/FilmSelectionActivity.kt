package com.example.favoritemovie

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.favoritemovie.viewmodels.FilmSelectionViewModel
import java.lang.reflect.TypeVariable
import java.util.*

const val MOVIE_PICKED = "com.example.favoritemovie.move_was_picked"
private const val KEY_INDEX ="index"
private const val LOG = "FilmSelectedActivity"

class FilmSelectionActivity : AppCompatActivity() {

    // Prepping my TextView's for use when onStart() is called
    private lateinit var f0: TextView
    private lateinit var f1: TextView
    private lateinit var f2: TextView
    private lateinit var f3: TextView
    private lateinit var f4: TextView
    private lateinit var submitBtn: Button

    // "movieClicked" stores the move that was chosen
    // I can then send the text associated with the clicked checkBox back to MainActivity
    private lateinit var movieClicked: String

    // Genre selected is for including the genre chosen in the title of FilmSelectionActivity
    private lateinit var genreSelected: String

    // Handles most of the data associated with displaying five films
    // It will grab the genre selected in MainActivity and include that into the title
    private val filmSelectionViewModel by lazy { FilmSelectionViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.film_selection_activity)

        // Initializing all of my TextView variables
        f0 = findViewById(R.id.film0)
        f1 = findViewById(R.id.film1)
        f2 = findViewById(R.id.film2)
        f3 = findViewById(R.id.film3)
        f4 = findViewById(R.id.film4)
        submitBtn = findViewById(R.id.submit_btn)

        genreSelected = intent.getStringExtra(MOVIE_PICKED).toString()
        "Pick the best move in the $genreSelected genre".also {
            findViewById<TextView>(R.id.film_selection_title).text = it
        }

        displayMovieOptions(genreSelected)

        submitBtn.setOnClickListener {
            setMoviePicked(movieClicked)
            finish()
        }

        f0.setOnClickListener {  movieClicked = f0.text.toString() }

        f1.setOnClickListener { movieClicked = f1.text.toString() }

        f2.setOnClickListener { movieClicked = f2.text.toString() }

        f3.setOnClickListener { movieClicked = f3.text.toString() }

        f4.setOnClickListener { movieClicked = f4.text.toString() }

    }

    private fun displayMovieOptions(genre: String) {
        val filmOptions = filmSelectionViewModel.displayFilms(genre)
        f0.text = filmOptions[1]
        f1.text = filmOptions[2]
        f2.text = filmOptions[3]
        f3.text = filmOptions[4]
        f4.text = filmOptions[5]
    }

    private fun setMoviePicked(movie: String?) {
        val data = Intent().apply {
            putExtra(MOVIE_PICKED, movie)
        }
        setResult(Activity.RESULT_OK, data)
    }

    // Here I'm creating a companion object for creating an intent with one "extra" for FilmSelectedActivity
    // If I wanted more "extra"'s, I'd add another parameter in the newIntent() function
    companion object {
        fun newIntent(context: Context, genre: String?): Intent {
            return Intent(context, FilmSelectionActivity::class.java).apply {
                putExtra(MOVIE_PICKED, genre)
            }
        }
    }

}