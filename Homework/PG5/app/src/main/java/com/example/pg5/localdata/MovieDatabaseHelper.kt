/**
 *  Tylor J. Hanshaw
 */

package com.example.pg5.localdata

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import android.util.Log
import com.example.pg5.localdata.model.Movie
import java.lang.reflect.Array.getInt

private const val LOG = "MovieDatabaseHelper:"

/**
 *  This is my database helper class that will physically add, remove, and obtain data from my local database
 */

class MovieDatabaseHelper(private val context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        private const val DB_VERSION = 2
        const val DB_NAME = "MovieData.db"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createDatabase = "CREATE TABLE Movies (ID INTEGER, Title TEXT, Year INTEGER, Genre TEXT, Description TEXT)"
        db!!.execSQL(createDatabase)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        onCreate(db)
    }

    override fun onOpen(db: SQLiteDatabase?) {
        super.onOpen(db)
    }

    fun addMovie(movieToAdd: Movie): Boolean {
        val db = this.writableDatabase
        val content = ContentValues()
        content.put("ID", movieToAdd.id) // This is the random UUID generated whenever an instance of a movie is created
        content.put("Title", movieToAdd.movieName)
        content.put("Year", movieToAdd.releaseYear)
        content.put("Genre", movieToAdd.genre)
        content.put("Description", movieToAdd.description)

        Log.d(LOG, "addMovie: Adding: $movieToAdd to Movies")

        val result = db.insert("Movies", null, content)
        if(result == -1L) return false
        return true
    }

    fun deleteMovie(movieToDelete: Movie) {
        val db = writableDatabase

        // I'm checking both the Id and title just to be extra careful
        val query = "DELETE FROM Movies WHERE ID = '${movieToDelete.id}' AND Title = '${movieToDelete.movieName}'"

        db.execSQL(query)
    }

}