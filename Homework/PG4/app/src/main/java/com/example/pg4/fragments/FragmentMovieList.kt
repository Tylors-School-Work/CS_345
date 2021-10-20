package com.example.pg4.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pg4.MainMovieActivity
import com.example.pg4.MovieSelectedActivity
import com.example.pg4.R
import com.example.pg4.data.Movie
import com.example.pg4.data.MovieLab
import java.util.*

private const val ARG_MOVIE_ID = "movie_selected"
private const val LOG = "FragmentMovieList"

class FragmentMovieList : Fragment() {

    interface Callbacks {
        fun onMovieSelected(movieId: UUID?)
    }

    private lateinit var recyclerView: RecyclerView
    private var movieAdapter: MovieAdapter? = null
    private var callbacks: Callbacks? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onResume() {
        Log.d("FragmentMovieList", "onResume")
        super.onResume()
        updateUI() // When I come back to this activity, "onResume" will be called, and I'll updated my data
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_movie_list, container, false)
        recyclerView = view.findViewById(R.id.movie_recycler_view) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        //updateUI()

        return view
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    companion object {

        fun newInstance(): FragmentMovieList {
            return FragmentMovieList()
        }

        fun newInstance(movieId: UUID?): FragmentMovieList {
            val args = Bundle().apply {
                putSerializable(ARG_MOVIE_ID, movieId)
            }
            return FragmentMovieList().apply {
                arguments = args
            }
        }
    }

    // May not want to keep this method, just trying to understand what Poe was doing better
    private fun updateUI() {
        val movieLab = MovieLab
        val movies = movieLab.movies
        if(movieAdapter == null) {
            movieAdapter = MovieAdapter(movies)
            recyclerView.adapter = movieAdapter
        }
        else {
            movieAdapter!!.notifyDataSetChanged()
        }
    }

    private inner class MovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private var movieTitle: TextView?
        private var movieDirector: TextView?
        private var movieReleaseDate: TextView?
        private var movieStartRating: TextView?
        private var currentMovie: Movie? = null

        init {
            itemView.setOnClickListener(this)
            movieTitle = itemView.findViewById(R.id.movie_title_tv) as TextView
            movieDirector = itemView.findViewById(R.id.movie_director_tv) as TextView
            movieReleaseDate = itemView.findViewById(R.id.date_released_tv) as TextView
            movieStartRating = itemView.findViewById(R.id.star_count_tv) as TextView
        }

        fun bindMovie(movie: Movie?) {
            currentMovie = movie
            movieTitle!!.text = currentMovie!!.movieTitle
            movieDirector!!.text = currentMovie!!.movieDirector
            movieReleaseDate!!.text = currentMovie!!.movieReleaseDate
            movieStartRating!!.text = currentMovie!!.starRating.toString() // The start rating is a 0-5 scale
        }

        override fun onClick(view: View) {
            Toast.makeText(activity, "${currentMovie!!.movieTitle} has been clicked!", Toast.LENGTH_SHORT).show()
            callbacks?.onMovieSelected(currentMovie!!.uid)
        }

    }

    private inner class MovieAdapter(private val movieList: List<Movie>?) : RecyclerView.Adapter<MovieHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
            val layoutInflater = LayoutInflater.from(activity)
            val view = layoutInflater.inflate(R.layout.list_item_movie, parent, false)
            return MovieHolder(view)
        }

        override fun onBindViewHolder(holder: MovieHolder, position: Int) {
            val aMovie = movieList!![position]
            holder.bindMovie(aMovie)
        }

        override fun getItemCount(): Int = movieList!!.size

    }
}