package com.example.criminalintent

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import java.util.*

class CrimeActivity : SingleFragmentActivity() {

    companion object {
        val EXTRA_CRIME_ID = "com.example.apoe.fragment1.crime_id"

        fun newIntent (packageContext: Context?, crimeID:UUID?):Intent? {

            val intent = Intent (packageContext!!,CrimeActivity::class.java)
            intent.putExtra (EXTRA_CRIME_ID,crimeID)
            return intent
        }
    }
    override fun createFragment(): Fragment {
        val crimeID: UUID? = intent.getSerializableExtra(EXTRA_CRIME_ID) as UUID?
        return CrimeFragment.newInstance (crimeID)
    }

}