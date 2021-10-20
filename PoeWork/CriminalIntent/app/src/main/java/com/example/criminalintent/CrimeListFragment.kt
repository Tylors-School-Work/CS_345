package com.example.criminalintent

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*


class CrimeListFragment : Fragment() {

    private var mCrimeRecyclerView: RecyclerView? = null
    //A RecyclerView holds a list...but it's cool, and it "recycles":
    //When information scrolls off the screen, it is deallocated (not held in memory)
    //since you don't need it.  When it is scrolled back, it is recreated.
    private var mAdapter: CrimeAdapter? = null  //This is a class invented in this code

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//This is like the start method in the fragment lifecycle
        Log.d("CRIMELISTFRAGMENT", "onCreateView")
        val view = inflater.inflate(R.layout.fragment_crime_list, container, false)
        mCrimeRecyclerView = view.findViewById (R.id.crime_recycler_view)
        mCrimeRecyclerView!!.layoutManager = LinearLayoutManager(activity)
        updateUI()
        return view
    }


    private fun updateUI() {
        val crimeLab = CrimeLab
        val crimes = crimeLab.crimes
        if (mAdapter == null) {
            mAdapter = CrimeAdapter(crimes)
            mCrimeRecyclerView!!.adapter = mAdapter
        } else {
           mAdapter!!.notifyDataSetChanged()
        }
    }
//This method is part of the fragment lifecycle, and is called when control
    //is returned
    override fun onResume() {
        Log.d("CRIMELISTFRAGMENT", "onResume")
        super.onResume()
        updateUI() //update any changes made
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("CRIMELISTFRAGMENT", "onCreate")
        super.onCreate(savedInstanceState)
   }

    override fun onStart() {
        Log.d("CRIMELISTFRAGMENT", "onStart")
        super.onStart()
    }

    override fun onStop() {
        Log.d("CRIMELISTFRAGMENT", "onStop")
        super.onStop()
    }

    override fun onDetach() {
        Log.d("CRIMELISTFRAGMENT", "onDetach")
        super.onDetach()
    }

    override fun onDestroyView() {
        Log.d("CRIMELISTFRAGMENT", "onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d("CRIMELISTFRAGMENT", "onDestroy")
        super.onDestroy()
    }

    override fun onPause() {
        Log.d("CRIMELISTFRAGMENT", "onPause")
        super.onPause()
    }
    


    //An inner class is a class that can only be used within this file
    private inner class CrimeHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private val mTitleTextView: TextView?
        private val mDateTextView: TextView?
        private val mSolvedCheckBox: CheckBox?
        private var mCrime: Crime? = null

        init {
            itemView.setOnClickListener(this)
            mTitleTextView = itemView.findViewById(R.id.list_item_crime_title_text_view) as TextView
            mDateTextView = itemView.findViewById(R.id.list_item_crime_date_text_view) as TextView
            mSolvedCheckBox = itemView.findViewById(R.id.list_item_crime_solved_check_box) as CheckBox
            mSolvedCheckBox.isClickable = false
        }

        fun bindCrime(crime: Crime?) {
            mCrime = crime
            mTitleTextView!!.text = mCrime!!.mTitle
            mDateTextView!!.text = mCrime!!.mDate!!.toString()
            mSolvedCheckBox!!.isChecked = mCrime!!.mSolved
        }

        override fun onClick(v: View) {

            Toast.makeText(activity, mCrime!!.mTitle!! + " Clicked!", Toast.LENGTH_SHORT).show()
            val intent = CrimePagerActivity.newIntent(activity as Context, mCrime!!.mId)
            startActivity(intent)

        }
    }

    private inner class CrimeAdapter(private val mCrimes: List<Crime>?) : RecyclerView.Adapter<CrimeHolder>() {
//This runs when the RecyclerView is initialized
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeHolder {
            Log.d("RECYCLER", "onCreateViewHolder")
            val layoutInflater = LayoutInflater.from(activity)
            val view = layoutInflater.inflate(R.layout.list_item_crime, parent, false)
            return CrimeHolder(view)
        }
//This is code that runs when something goes off the screen and is deallocated
        override fun onDetachedFromRecyclerView(rv: RecyclerView) {
            Log.d("RECYCLER", "DEAD!!!")
            super.onDetachedFromRecyclerView(rv)
        }
//This is what runs when something in the recyclerview scrolls into view
        override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
            val crime = mCrimes!![position]
            holder.bindCrime(crime)
        }

        override fun getItemCount(): Int {
            return mCrimes!!.size
        }
    }
}
