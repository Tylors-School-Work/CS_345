package com.example.database

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MovieDBHelper: SQLiteOpenHelper (MovieActivity.mContext,MovieActivity.mContext!!.applicationInfo.dataDir+"/databases/movies.db",null,1) {
    override fun onCreate(p0: SQLiteDatabase?) {
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }
}