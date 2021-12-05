/**
 *  Tylor J. Hanshaw
 */

package com.example.pg5.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pg5.localdata.model.Movie
import com.example.pg5.repository.DataRepository

private const val LOG = "MovieViewModel:"

/**
 *  Following with the MVVM architecture, this ViewModel is tied to the RecyclerView and the add plant fragment
 *  It's actually tied to the activity that hosts them, since each ViewModel is tied to only one activity, generally
 *  But this ViewModel deals with any and all information passing between the DataRepository and the Views it is associated with
 */

class ListMovieViewModel : ViewModel() {

    private val movieNameList: ArrayList<Movie> = arrayListOf()
    val getMovieNameList: ArrayList<Movie> get() = movieNameList

    fun sortMoviesByGenre() {
        movieNameList.sortBy { movie ->
            movie.genre?.get(0)
        }
    }

    // I could have had this just return whatever DataRepository returns, but I did it this way to keep the data persistent
    fun setMovieList(context: Context) {
        val tempList = DataRepository.getListOfMovieTitles(context)
        tempList.forEach { movie ->
            movieNameList.add(movie)
        }
    }

    fun addMovieToDB(context: Context, movieToAdd: Movie): Boolean {
        val result = DataRepository.addMovieToDB(context, movieToAdd)
        if(result) {
            movieNameList.add(movieToAdd)
            return true
        }
        return false
    }

    private val newMovieAdded: MutableLiveData<Movie> = MutableLiveData()
    val getNewMovieAdded: LiveData<Movie> get() = newMovieAdded

    fun addMovieToRecyclerView(newMovie: Movie) {
        newMovieAdded.postValue(newMovie)
    }

}