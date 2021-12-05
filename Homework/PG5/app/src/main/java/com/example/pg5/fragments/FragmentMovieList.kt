/**
 *  Tylor J. Hanshaw
 */

package com.example.pg5.fragments

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.cardview.widget.CardView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pg5.R
import com.example.pg5.databinding.SingleMovieViewBinding
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.pg5.SingleMovieSelectedActivity
import com.example.pg5.interfaces.Navigation
import com.example.pg5.localdata.model.Movie
import com.example.pg5.viewmodels.ListMovieViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

private const val LOG = "FragmentMovieList:"

/**
 *  This fragment handles displaying the list of movies added by the user
 *  The Recyclerview will be updated when you add and remove a movie
 */

class FragmentMovieList : Fragment(), AddMovieFragment.DatabaseAPI {

    // XML widgets
    private lateinit var recyclerView: RecyclerView
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var addBtn: FloatingActionButton

    // ViewModel
    private val listMovieVM by activityViewModels<ListMovieViewModel>()

    private var navigation: Navigation? = null

    private val observer = Observer<Movie> { movie ->
        movieAdapter.updateRecyclerView(movie)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigation = context as Navigation
    }

    override fun onDetach() {
        super.onDetach()
        navigation = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)

        recyclerView = view.findViewById(R.id.main_recycler_view)
        recyclerView.apply {
            layoutManager = GridLayoutManager(context, 1)
            movieAdapter = MovieAdapter(listMovieVM.getMovieNameList)
        }
        recyclerView.adapter = movieAdapter

        listMovieVM.getNewMovieAdded.observeForever(observer)

        addBtn = view.findViewById(R.id.add_movie_btn)

        addBtn.setOnClickListener {
            navigation!!.navigateToAddMovieForm()
        }

        return view
    }

    companion object {
        fun newInstance(): FragmentMovieList { return FragmentMovieList() }
    }

    override fun updateRecyclerView(movie: Movie) {
        movieAdapter.updateRecyclerView(movie)
    }

    // RecyclerView requirements
    private inner class MovieHolder(
        private val cardItemView: SingleMovieViewBinding
    ) : RecyclerView.ViewHolder(cardItemView.root) {

        private var movietitle: TextView
        private var headerCard: CardView
        private var headerText: TextView

        init {
            movietitle = cardItemView.movieTitle
            headerCard = cardItemView.headerCardView
            headerText = cardItemView.headerText
        }

        fun bindView(movie: Movie?, position: Int) {
            headerText.text = movie!!.genre
            movietitle.text = movie.movieName
            cardItemView.singleMovieCard.setOnClickListener {
                Log.d(LOG, "Movie UID: ${movie.id}")
                val intent = SingleMovieSelectedActivity.newInstance(requireContext(), position)
                startActivity(intent)
            }
        }

    }

    private inner class MovieAdapter(
        val movieTitleList: ArrayList<Movie>
        ) : RecyclerView.Adapter<MovieHolder>() {

            private val HEADER = 0
            private val MOVIE = 1

            override fun getItemViewType(position: Int): Int {
                return if(position % 5 == 0) HEADER else MOVIE
            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
                val layoutInflater = LayoutInflater.from(context)
                val cardViewBinding = SingleMovieViewBinding.inflate(layoutInflater)
                return MovieHolder(cardViewBinding)
            }

            override fun onBindViewHolder(holder: MovieHolder, position: Int) {
                val aMovie = movieTitleList[position]
                holder.bindView(aMovie, position)
            }

            override fun getItemCount(): Int = movieTitleList.size

        // This updates the RecyclerView and also organizes the list by genre (sort of)
        // I'm really just organizing by the first letter of the genre name
            fun updateRecyclerView(movie: Movie) {
                movieTitleList[movieTitleList.size - 1] = movie
                movieTitleList.sortBy {
                    it.genre!![0]
                }
                notifyDataSetChanged()
            }

    }
}