/**
 *  Tylor J. Hanshaw
 */

package com.example.pg5.repository

import android.content.Context
import com.example.pg5.localdata.MovieDatabaseHelper
import com.example.pg5.localdata.model.Movie

private const val LOG = "DataRepository"

/**
 *  This is where I talk to my local database
 *  I'm following the MVVM architecture for my senior project and figured I'd throw a little of that in here
 *  It's LL bout separation of concerns, and so this object "singleton" is the only thing that knows how to get the data
 */

object DataRepository {

    private var movieListCount = 1
    val getMovieListCount: Int get() = movieListCount

    fun getListOfMovieTitles(context: Context): List<Movie> {
        val movieNameList: MutableList<Movie> = mutableListOf()
        val localDatabaseReference = MovieDatabaseHelper(context).readableDatabase
        val cursor = localDatabaseReference.rawQuery("SELECT * FROM Movies", null) // I rather select all "Movie Name"'s from the table instead
        while(cursor.moveToNext()) {
            movieNameList.add(
                Movie(
                    cursor.getString(cursor.getColumnIndexOrThrow("Title")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("Year")),
                    cursor.getString(cursor.getColumnIndexOrThrow("Genre")),
                    cursor.getString(cursor.getColumnIndexOrThrow("Description")),
                    cursor.getString(cursor.getColumnIndexOrThrow("ID"))
                ))
            movieListCount++
        }
        cursor.close()
        return movieNameList
    }

    fun addMovieToDB(context: Context, movieToAdd: Movie): Boolean {
        val localDatabaseReference = MovieDatabaseHelper(context)
        val result = localDatabaseReference.addMovie(movieToAdd)
        if(result) return true
        return false
    }

    fun deleteMovieFromDB(context: Context, movieToDelete: Movie) {
        val localDatabaseReference = MovieDatabaseHelper(context)
        localDatabaseReference.deleteMovie(movieToDelete)
    }

}