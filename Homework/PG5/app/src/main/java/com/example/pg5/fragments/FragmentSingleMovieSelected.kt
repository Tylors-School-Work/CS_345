/**
 *  Tylor J. Hanshaw
 */

package com.example.pg5.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.pg5.MainActivity
import com.example.pg5.R
import com.example.pg5.localdata.model.Movie
import com.example.pg5.viewmodels.SingleMovieViewModel

private const val LOG = "FragmentSingleMovieSelected:"

/**
 *  This fragment is used in my ViewPager2 widget, and displays all of the added movies as you scroll left and right
 */

class FragmentSingleMovieSelected : Fragment() {

    // Widgets
    private lateinit var movieTitle: TextView
    private lateinit var movieReleaseDate: TextView
    private lateinit var movieGenre: TextView
    private lateinit var movieDescription: TextView
    private lateinit var deleteBtn: Button

    private lateinit var movieToDisplay: Movie

    // Movie ID to display
    private lateinit var movieID: String

    // ViewModel
    private val singleMovieVM by activityViewModels<SingleMovieViewModel>()

    @SuppressLint("LongLogTag") // Just here because of my long "LOG"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?  {
        val view = inflater.inflate(R.layout.fragment_single_movie_selected, container, false)

        val movieIndex = arguments?.getInt(POS_IN_ARRAY)

        movieTitle = view.findViewById(R.id.movie_title_single)
        movieReleaseDate = view.findViewById(R.id.selected_year)
        movieGenre = view.findViewById(R.id.selected_genre)
        movieDescription = view.findViewById(R.id.selected_description)
        deleteBtn = view.findViewById(R.id.delete_btn)

         movieToDisplay = singleMovieVM.fundMovieDetails(movieIndex!!)

        Log.d(LOG, "Movie selected UID: ${movieToDisplay.id}")

         setViews()

        deleteBtn.setOnClickListener {
            singleMovieVM.deleteMovieFromDB(
                requireContext(),
                movieToDisplay
            )
            startActivity(MainActivity.newInstance(requireContext()))

        }

        return view
    }

    private fun setViews() {
        movieTitle.text = movieToDisplay.movieName
        movieReleaseDate.text = movieToDisplay.releaseYear.toString()
        movieGenre.text = movieToDisplay.genre
        movieDescription.text = movieToDisplay.description
    }

    companion object {
        const val POS_IN_ARRAY = "com.example.pg5.pos_in_array"
        fun newInstance(position: Int): FragmentSingleMovieSelected {
            val fragment = FragmentSingleMovieSelected().apply {
                arguments = Bundle().apply {
                    putInt(POS_IN_ARRAY, position)
                }
            }
            return fragment
        }
    }
}