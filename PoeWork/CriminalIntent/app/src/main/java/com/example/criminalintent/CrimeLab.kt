/*
CrimeLab is what we call a "singleton class".  There is only ever one of them,
and it's created the first time we use it.
 */

package com.example.criminalintent

import java.util.*

object CrimeLab {

    private val mCrimes:MutableList<Crime>?
//A private var or value is one for which automatic gets and sets are not
    //generated, so that it cannot be automatically accessed from outside the class

    val crimes:List<Crime>?
        get() = mCrimes

    fun getCrime (id: UUID?):Crime? {
        for (crime in mCrimes!!)
            if (crime.mId==id) return crime
        return null
    }

    init {  //This initializes our object; it runs ONCE
        mCrimes = ArrayList()
        for (i in 0..99) {
            val crime = Crime()
            crime.mTitle = "Crime #${i}"
            crime.mSolved = i%2==0
            mCrimes.add(crime)
        }
    }


}