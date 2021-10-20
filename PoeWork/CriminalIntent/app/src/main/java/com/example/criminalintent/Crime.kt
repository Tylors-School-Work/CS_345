/*
Crime consists of data only.  We're storing an ID number, the Title of a crime.
The date of a crime, and whether it is solved.
 */

package com.example.criminalintent

import java.util.*

class Crime {

    var mId: UUID? = UUID.randomUUID() //random ID number, Kotlin library
    var mTitle:String? = ""
    var mDate:Date? = Date() //Date also includes time
    var mSolved:Boolean = false


}