package com.example.criminalintent

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.criminalintent.CrimeActivity.Companion.EXTRA_CRIME_ID
import java.util.*

class CrimePagerActivity : AppCompatActivity() {

    private var mViewPager: ViewPager2? = null
    private var mCrimes:List<Crime>? = null

    override fun onCreate (savedInstanceState: Bundle?) {
        Log.d ("CRIMEPAGERACTIVITY","onCreate")
        super.onCreate (savedInstanceState)
        setContentView (R.layout.activity_crime_pager)
        val crimeId = intent.getSerializableExtra(EXTRA_CRIME_ID) as UUID
        mViewPager = findViewById (R.id.crime_view_pager)
        mCrimes = CrimeLab.crimes
        val fragmentManager = supportFragmentManager
        mViewPager!!.adapter = object : FragmentStateAdapter(fragmentManager,lifecycle) {
            //When the pager is on page "position" what should it show?
            override fun createFragment (position:Int) : Fragment {
                val crime = mCrimes!![position]
                return CrimeFragment.newInstance(crime.mId)
            }

            override fun getItemCount():Int {
                return mCrimes!!.size
            }


        }
        //This sets where we are in the Pager View
        for (i in mCrimes!!.indices) {
            if (mCrimes!![i].mId==crimeId) {
                mViewPager!!.currentItem = i
                break
            }
        }

     }

    override fun onStart() {
        Log.d ("CRIMEPAGERACTIVITY","onStart")
        super.onStart()
    }
    override fun onRestart() {
        Log.d ("CRIMEPAGERACTIVITY","onRestart")
        super.onRestart()
    }
    override fun onPause() {
        Log.d ("CRIMEPAGERACTIVITY","onPause")
        super.onPause()
    }
    override fun onResume() {
        Log.d ("CRIMEPAGERACTIVITY","onResume")
        super.onResume()
    }
    override fun onDestroy() {
        Log.d ("CRIMEPAGERACTIVITY","onDestroy")
        super.onDestroy()
    }
    override fun onStop() {
        Log.d ("CRIMEPAGERACTIVITY","onStop")
        super.onStop()
    }

    companion object {
        fun newIntent (packageContext: Context, crimeId:UUID?): Intent {
            val intent = Intent(packageContext,CrimePagerActivity::class.java)
            intent.putExtra(EXTRA_CRIME_ID,crimeId);
            return intent
        }
    }
}