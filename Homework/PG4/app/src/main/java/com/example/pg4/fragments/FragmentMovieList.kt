/*
*       Tylor J. Hanshaw
* */

package com.example.pg4.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pg4.R
import com.example.pg4.data.Movie
import com.example.pg4.data.MovieLab
import java.util.*

// Used for debugging/logging
private const val LOG = "FragmentMovieList"

class FragmentMovieList : Fragment() {

    // My "Callbacks" interface, used to navigate between my fragments and activities
    // I override the "onMovieSelected" method in my "MainMovieActivity" activity, and in mu "MovieSelectedActivity" activity
    interface Callbacks { fun onMovieSelected(movieId: UUID?) }

    private lateinit var recyclerView: RecyclerView
    private var movieAdapter: MovieAdapter? = null
    private var callbacks: Callbacks? = null

    // Overrode this method so I can initialize my "callbacks" variable whenever "onAttach" is called
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    // This is so data in my recycler view can be updated whenever the star rating is changed
    // So whenever "onResume" is called the UI will be updated, if it needs to be
    override fun onResume() {
        Log.d("FragmentMovieList", "onResume")
        super.onResume()
        updateUI() // When I come back to this activity, "onResume" will be called, and I'll update my data
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

        // This just adds a vertical line between each view in my recycler view
        // Just looks a little better/cleaner this way
        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL))

        return view
    }

    // Setting my "callbacks" variable to null whenever "onDetach" is called
    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    // Used to create an instance of "FragmentMovieList" mor elegantly
    companion object {
        fun newInstance(): FragmentMovieList { return FragmentMovieList() }
    }

    // This method will try to update any/all data in my recycler view whenever it's called (when "onResume" is called for the most part)
    private fun updateUI() {
        val movies = MovieLab.movies
        if(movieAdapter == null) {
            movieAdapter = MovieAdapter(movies)
            recyclerView.adapter = movieAdapter
        }
        else movieAdapter!!.notifyDataSetChanged()
    }

    // Used to display the star rating as actual stars --> "*"
    private fun generateStarRating(starRating: Int): String {
        var rating = ""
        if(starRating == 0) return "0"
        for(i in 0 until starRating) rating += "*"
        return rating
    }

    // This is my RecyclerViewHolder, required when working with a recycler view
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
            movieTitle!!.text = "Title: ${currentMovie!!.movieTitle}"
            movieDirector!!.text = "Director: ${currentMovie!!.movieDirector}"
            movieReleaseDate!!.text = currentMovie!!.movieReleaseDate
            movieStartRating!!.text = generateStarRating(movie!!.starRating)


            //movieStartRating!!.text = "Star rating: ${currentMovie!!.starRating}" // The start rating is a 0-5 scale
        }

        // When a view in my recycler view is clicked, I will call the "onMovieSelected" method from my "Callbacks" interface
        // This will essentially navigate me to my "MovieSelectedActivity" activity, an eventually to my "FragmentMovie" fragment
        override fun onClick(view: View) { callbacks?.onMovieSelected(currentMovie!!.uid) }
    }

    // This is my RecyclerViewAdapter, required when working with a recycler view
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