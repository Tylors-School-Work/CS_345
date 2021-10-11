package com.example.favoritemovie

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.example.favoritemovie.viewmodels.FilmSelectionViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.w3c.dom.Text

private const val REQUEST_GENRE_CODE = 0
private const val STRING_KEY = "MainActivityKey"
private const val LOG = "MainActivity:"

class MainActivity : AppCompatActivity() {

    private lateinit var thrillerGenre: TextView
    private lateinit var horrorGenre: TextView
    private lateinit var fantasyGenre: TextView
    private lateinit var comedyGenre: TextView
    private lateinit var actionGenre: TextView
    private lateinit var title: TextView

    private var genreSelected = ""
    private lateinit var submitBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = findViewById(R.id.main_activity_title)
        thrillerGenre = findViewById(R.id.thriller_tv)
        horrorGenre = findViewById(R.id.horror_tv)
        fantasyGenre = findViewById(R.id.fantasy_tv)
        comedyGenre = findViewById(R.id.comedy_tv)
        actionGenre = findViewById(R.id.action_tv)
        submitBtn = findViewById(R.id.submit_genre_btn)

        submitBtn.setOnClickListener {
            if(genreSelected != "") {
                val intent = FilmSelectionActivity.newIntent(this@MainActivity, genreSelected)
                startActivity(intent)
            }
        }

        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if(result.resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "This has been hit!", Toast.LENGTH_SHORT).show()
                hideAllGenres()
                submitBtn.isVisible = false
                "The film you have chosen is ${result.data?.getStringExtra(MOVIE_PICKED)}".also {
                    title.text = it
                }
            }
            else Toast.makeText(this, "Something bad happened.", Toast.LENGTH_SHORT).show()
        }

        thrillerGenre.setOnClickListener { genreSelected = "thriller" }

        horrorGenre.setOnClickListener { genreSelected = "horror" }

        fantasyGenre.setOnClickListener { genreSelected = "fantasy" }

        comedyGenre.setOnClickListener { genreSelected = "comedy" }

        actionGenre.setOnClickListener { genreSelected = "action" }
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        // This is true if we did not receive the expected intent
//        if(resultCode != RESULT_OK) return
//
//        if(requestCode == REQUEST_GENRE_CODE) {
//            hideAllGenres()
//            submitBtn.isVisible = false
//            "The film you have chosen is ${data?.getStringExtra(MOVIE_PICKED)}".also {
//                title.text = it
//            }
//        }
//    }

    // This hides all genre options when the user has picked a movie
    private fun hideAllGenres() {
        thrillerGenre.isVisible = false
        horrorGenre.isVisible = false
        fantasyGenre.isVisible = false
        comedyGenre.isVisible = false
        actionGenre.isVisible = false
    }
}