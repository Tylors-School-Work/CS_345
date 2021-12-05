package com.example.database

import android.database.Cursor
import android.database.CursorWrapper

class MovieCursorWrapper (cursor: Cursor) : CursorWrapper(cursor) {

    val stuff:MovieStuff
    get() {
        val TITLE = getString (getColumnIndex(MovieTable.Cols.TITLE))
        val GENRE = getString (getColumnIndex(MovieTable.Cols.GENRE))
        val YEAR = getInt (getColumnIndex(MovieTable.Cols.YEAR))
        val DESCRIPTION = getString (getColumnIndex(MovieTable.Cols.DESCRIPTION))
        val ID = getInt (getColumnIndex(MovieTable.Cols.ID))
        return MovieStuff (TITLE,GENRE,YEAR,DESCRIPTION,ID)
    }
}