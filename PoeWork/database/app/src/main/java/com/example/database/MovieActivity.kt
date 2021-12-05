package com.example.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.io.BufferedInputStream
import java.io.File
import java.io.FileOutputStream

class MovieActivity : AppCompatActivity() {

    companion object {
        var mContext: Context? = null
    }
    var mDatabase: SQLiteDatabase? = null
    var mBasicInfo: TextView? = null
    var A:ArrayList<String> = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mContext = this
        mBasicInfo = findViewById(R.id.BasicInfo)
        val path: String = mContext!!.applicationInfo.dataDir + "/databases/movies.db"
        /*
        mContext!!.applicationInfo.dataDir is a folder on the Android that an app
        can use to store data.  The data stored remains there so that if the app
        is killed and restarted, the data remains stored there between iterations.

        This is NOT the same as the assets folder which is where you load information
        at programming time.  This dataDir is where you store information during
        runtime.
         */
        /* The first thing I want to do is copy the database from the assets folder
           to the dataDir...if I haven't already done this.
         */
        if (File(path).exists())
            mBasicInfo!!.text = "EXISTS"
        else {
            mBasicInfo!!.text = "DOES NOT EXIST"
            val assetfile: BufferedInputStream? =
                BufferedInputStream(mContext!!.assets.open("movies.db"))
            try {
                File(mContext!!.applicationInfo.dataDir + "/databases").mkdir()
            } catch (e: Exception) {
            }
            val dbfile: FileOutputStream? = FileOutputStream(path)
            while (true) {
                val ch: Int = assetfile!!.read()
                if (ch == -1) break
                dbfile!!.write(ch)
            }
            assetfile.close()
            dbfile!!.flush()
            dbfile.close()
        }
        mDatabase = MovieDBHelper().writableDatabase
        var values = getContentValues()
        mDatabase!!.delete (MovieTable.NAME,"${MovieTable.Cols.YEAR} = 1989",null)
        mDatabase!!.insert (MovieTable.NAME,null,values)
        val cursor = query (null,null) //Give me a dump of the table
//        val cursor = query ("Title = ?",arrayOf<String>("Jurassic Park")) //Give me a dump of the table
//        val cursor = query ("Year = 1993",null) //Give me a dump of the table
        cursor.moveToFirst()
        while (!cursor.isAfterLast) {
            val ss = cursor.stuff
            println ("${ss.title} ${ss.genre} ${ss.year} ${ss.description} ${ss.id}")
            A.add ("${ss.title} ${ss.genre} ${ss.year} ${ss.description} ${ss.id}")
            cursor.moveToNext()
        }
        cursor.close()
        mDatabase!!.close()
    }

    fun query (whereClause:String?,whereArgs:Array<String>?):MovieCursorWrapper {
        val cursor = mDatabase!!.query (MovieTable.NAME,null,whereClause,whereArgs,null,null,null)
        return MovieCursorWrapper (cursor)
    }

    fun getContentValues ():ContentValues { //ContentValues is a built-in Kotlin class for use in SQLite databases
        //It's just a wrapper class around a set of column names and column values

        val values = ContentValues ()
        values.put(MovieTable.Cols.TITLE,"Back To The Future Part II")
        values.put(MovieTable.Cols.GENRE,"Science Fiction")
        values.put(MovieTable.Cols.YEAR,1989)
        values.put(MovieTable.Cols.DESCRIPTION,"Where We've All Gone Before")
        return values
    }
}