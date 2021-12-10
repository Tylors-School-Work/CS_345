/*
*       Tylor J. Hanshaw
* */

package com.example.pg4.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.pg4.R
import com.example.pg4.data.Movie
import com.example.pg4.data.MovieLab
import java.util.*

// Used for passing arguments in my companion object below
private const val ARG_MOVIE_ID = "movie_selected"

// Used for debugging/logging
private const val LOG = "FragmentMovie"

// Fragment that's hosted by MovieSelectedActivity
// Dedicated to displaying the details of the currently selected movie
class FragmentMovie : Fragment() {

    // Setting up all widgets in my fragment
    private lateinit var submitRatingBtn: Button
    private lateinit var backBtn: Button
    private lateinit var movieTitleTV: TextView
    private lateinit var movieDirectorTV: TextView
    private lateinit var dateReleasedTV: TextView
    private lateinit var starRatingTV: TextView
    private lateinit var starRatingED: EditText
    private lateinit var starImage: ImageView

    // Star rating related variables
    private var selectedMovie: Movie? = null
    private var movieStarRating: Int = 0

    // This is so I can grab the argument that's passed to this fragment when it is created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val movieId = arguments?.getSerializable(ARG_MOVIE_ID) as UUID
        selectedMovie = MovieLab.getMovie(movieId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_movie, container, false)

        // Initializing all widgets
        submitRatingBtn = view.findViewById(R.id.submit_btn)
        backBtn = view.findViewById(R.id.back_btn)
        movieTitleTV = view.findViewById(R.id.display_movie_title_tv)
        movieDirectorTV = view.findViewById(R.id.display_director_tv)
        dateReleasedTV = view.findViewById(R.id.display_date_released_tv)
        starRatingTV = view.findViewById(R.id.display_star_rating_tv)
        starRatingED = view.findViewById(R.id.star_rating_ET)
        starImage = view.findViewById(R.id.star_rating_IV)

        movieTitleTV.text = selectedMovie!!.movieTitle
        movieDirectorTV.text = "Director: ${selectedMovie!!.movieDirector}"
        dateReleasedTV.text = "Date released: ${selectedMovie!!.movieReleaseDate}"
        starRatingTV.text = "Star rating: ${selectedMovie!!.starRating}"

        // First change the star image based on the selected movies star rating
        updateStarRating(selectedMovie!!)

        // I hold on to the original star rating to check if it has changed when I call "updateStarRating()
        movieStarRating = selectedMovie!!.starRating

        // Changes the star rating and star image based on user input
        // If the rating is not within the allowed range then the user will be warned
        submitRatingBtn.setOnClickListener {
            if(isNumber(starRatingED.text.toString()) && (starRatingED.text.toString().toInt() in 0..5)) { // This is totally new to me, Android suggested it, but it's pretty cool!
                val starRatingGiven = starRatingED.text.toString().toInt()
                MovieLab.changeMovieRating(starRatingGiven, selectedMovie!!.uid)
                Toast.makeText(context, "Thank you for rating this movie!", Toast.LENGTH_SHORT).show()
                updateStarRating(selectedMovie!!)
            }
            else Toast.makeText(context, "Please enter a valid star rating.", Toast.LENGTH_SHORT).show()
        }

        // Finishes this fragment and its parent activity, returning to the original recycler view
        backBtn.setOnClickListener { activity?.finish() }

        return view
    }

    // Deciding which star image to use based off of its star rating
    // n < 2 is empty star, n > 1 && n < 5 is half filled star, n == 5 is full star
    private fun updateStarRating(movie: Movie) {
        when {
            movie.starRating < 2 -> {
                starImage.setImageResource(R.drawable.star_rating_icon_empty)
            }
            movie.starRating in 2..4 -> {
                starImage.setImageResource(R.drawable.star_rating_icon_half)
            }
            else -> {
                starImage.setImageResource(R.drawable.star_rating_icon_full)
            }
        }
        if(movieStarRating != movie.starRating) {
            movieStarRating = movie.starRating
            starRatingTV.text = "Star rating: ${movieStarRating}"
        }
    }

    // Used for calling this fragment and passing the data needed to it
    // I attach an argument which is the selected movies UUID
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

    // Helper method to check if the string input is an integer or not, need this to check if the input from starRatingEDn is an int or not
    // I originally was doing this with just an if statement, but even if the input type is "number" in the XML, if nothing is inputted it is read as an empty string
    // Pulled from this website, all credits to them! --> https://www.techiedelight.com/determine-string-is-number-kotlin/
    private fun isNumber(s: String?): Boolean {
        return if (s.isNullOrEmpty()) false else s.all { Character.isDigit(it) }
    }
}