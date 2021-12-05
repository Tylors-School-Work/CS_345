/**
 *  Tylor J. Hanshaw
 */

package com.example.pg5

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.pg5.fragments.AddMovieFragment
import com.example.pg5.fragments.FragmentMovieList
import com.example.pg5.interfaces.Navigation
import com.example.pg5.viewmodels.ListMovieViewModel

private const val LOG = "MainActivity:"

/**
 *  This is the MainActivity that hosts two fragments, my movie RecyclerView and the add plant fragment
 *  I've used a custom interface called Navigation to navigate between the two fragments
 */

class MainActivity : AppCompatActivity(), Navigation {

    private val listMovieVM by viewModels<ListMovieViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listMovieVM.setMovieList(this)
        listMovieVM.sortMoviesByGenre() // This is sorting by genre, sort of - sorting my the genre's first letter

        val fragment = FragmentMovieList.newInstance()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, fragment, "fragment_movie_list")
            .commit()

    }

    companion object {
        fun newInstance(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun navigateToAddMovieForm() {
        val fragment = AddMovieFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, fragment, "fragment_add_movie")
            .commit()
    }
}

