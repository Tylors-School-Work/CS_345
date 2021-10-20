package com.example.pg4.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.pg4.MainMovieActivity
import com.example.pg4.MovieSelectedActivity
import com.example.pg4.R
import com.example.pg4.data.Movie
import com.example.pg4.data.MovieLab
import java.util.*

private const val ARG_MOVIE_ID = "movie_selected"
private const val LOG = "FragmentMovie"

class FragmentMovie : Fragment() {

    // Setting up all widgets in my fragment
    private lateinit var submitBtn: Button
    private lateinit var movieTitleTV: TextView
    private lateinit var movieDirectorTV: TextView
    private lateinit var dateReleasedTV: TextView
    private lateinit var starRatingTV: TextView
    private lateinit var starRatingED: EditText

    private var selectedMovie: Movie? = null
    private var callbacks: FragmentMovieList.Callbacks? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val movieId = arguments?.getSerializable(ARG_MOVIE_ID) as UUID
        selectedMovie = MovieLab.getMovie(movieId)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_movie, container, false)

        // Initializing all widgets
        submitBtn = view.findViewById(R.id.submit_btn)
        movieTitleTV = view.findViewById(R.id.display_movie_title_tv)
        movieDirectorTV = view.findViewById(R.id.display_director_tv)
        dateReleasedTV = view.findViewById(R.id.display_date_released_tv)
        starRatingTV = view.findViewById(R.id.display_star_rating_tv)
        starRatingED = view.findViewById(R.id.star_rating_ET)

        movieTitleTV.text = selectedMovie!!.movieTitle
        movieDirectorTV.text = "Director: ${selectedMovie!!.movieDirector}"
        dateReleasedTV.text = "Date released: ${selectedMovie!!.movieReleaseDate}"
        starRatingTV.text = "Star rating: ${selectedMovie!!.starRating}"

        submitBtn.setOnClickListener {
            val starRatingGiven = starRatingED.text.toString().toInt()
            if(starRatingED.text.isNotEmpty()) {
                MovieLab.changeMovieRating(starRatingGiven, selectedMovie!!.uid)
                activity?.finish()
            }
            else activity?.finish()
        }

        return view
    }

    companion object {
        fun newInstance(movieId: UUID): FragmentMovie {
            val args = Bundle().apply {
                putSerializable(ARG_MOVIE_ID, movieId)
            }
            return FragmentMovie().apply {
                arguments = args
            }
        }
    }
}