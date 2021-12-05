/**
 *  Tylor J. Hanshaw
 */

package com.example.pg5.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.pg5.R
import com.example.pg5.localdata.model.Movie
import com.example.pg5.viewmodels.ListMovieViewModel

private const val LOG = "AddMovieFragment"

/**
 *  This fragment displays a form for adding a new movie to the RecyclerView
 *  Once all fields are filled out correctly you click "add movie" and a Toast message will tell you if it was added successfully
 *  Then hit the "back" button and you will return to your updated RecyclerView
 */

class AddMovieFragment : Fragment() {

    interface DatabaseAPI {
        fun updateRecyclerView(movie: Movie)
    }

    private lateinit var movieTitle: EditText
    private lateinit var releaseYear: EditText
    private lateinit var movieGenre: EditText
    private lateinit var movieDescription: EditText
    private lateinit var submitBtn: Button
    private lateinit var backBtn: Button

    // ViewModel
    private val listMovieVM by activityViewModels<ListMovieViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_movie, container, false)

        movieTitle = view.findViewById(R.id.movie_title_ET)
        releaseYear = view.findViewById(R.id.movie_release_date_ET)
        movieGenre = view.findViewById(R.id.movie_genre_ET)
        movieDescription = view.findViewById(R.id.movie_description_ET)
        submitBtn = view.findViewById(R.id.submit_btn)
        backBtn = view.findViewById(R.id.back_button)

        backBtn.setOnClickListener {
            activity!!.supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, FragmentMovieList.newInstance())
                .commit()
        }

        submitBtn.setOnClickListener {
            val newMovie = Movie(
                movieTitle.text.toString(),
                releaseYear.text.toString().toInt(),
                movieGenre.text.toString(),
                movieDescription.text.toString()
            )

            val result = listMovieVM.addMovieToDB(requireContext(), newMovie)
            if(result) {
                Log.d(LOG, "Movie UID: ${newMovie.id}")
                listMovieVM.addMovieToRecyclerView(newMovie)
                Toast.makeText(requireContext(), "Your movie has been added!", Toast.LENGTH_SHORT).show()
            }
            else Toast.makeText(requireContext(), "Please enter valid movie information and try again..", Toast.LENGTH_SHORT).show()
        }

        return view
    }

    companion object {

        fun newInstance(): AddMovieFragment {
            return AddMovieFragment()
        }
    }
}