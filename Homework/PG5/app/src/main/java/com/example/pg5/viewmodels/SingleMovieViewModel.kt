/**
 *  Tylor J. Hanshaw
 */

package com.example.pg5.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.pg5.localdata.model.Movie
import com.example.pg5.repository.DataRepository

private const val LOG = "SingleMovieViewModel"

/**
 *  Following with the MVVM architecture, this ViewModel is tied to the ViewPager2 and the fragments associated with that
 *  It's actually tied to the activity that hosts them, since each ViewModel is tied to only one activity, generally
 *  But this ViewModel deals with any and all information passing between the DataRepository and the Views it is associated with
 */

class SingleMovieViewModel: ViewModel() {

    private val movieNameList: ArrayList<Movie> = arrayListOf()
    val getMovieNameList: List<Movie> get() = movieNameList

    // I could have had this just return whatever DataRepository returns, but I did it this way to keep the data persistent
    fun setMovieList(context: Context) {
        val tempList = DataRepository.getListOfMovieTitles(context)
        tempList.forEach { movie ->
            movieNameList.add(movie)
        }
        movieNameList.sortBy { movie ->
            movie.genre?.get(0)
        }
    }

    fun fundMovieDetails(position: Int): Movie = movieNameList[position]

    fun deleteMovieFromDB(context: Context, movieToDelete: Movie) {
        DataRepository.deleteMovieFromDB(context, movieToDelete)
    }
}