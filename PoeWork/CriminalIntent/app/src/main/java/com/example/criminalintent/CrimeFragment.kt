/*
A fragment is a mini-screen (more or less) that you can put into an activity.
You can put more than one fragment in an activity.  And you can more than one of the
same kind of fragment into an activity.
 */


package com.example.criminalintent

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.EditText
import java.util.*


class CrimeFragment : Fragment() {
    // TODO: Rename and change types of parameters

    var mCrime:Crime? = null
    var mTitleField: EditText? = null
    var mSolvedCheckBox: CheckBox? = null
    var mDateButton:Button? =  null

    companion object {
        val ARG_CRIME_ID = "crime_id"

        fun newInstance (crimeID: UUID?):CrimeFragment {
            val args = Bundle()
            args.putSerializable(ARG_CRIME_ID,crimeID)
            val fragment = CrimeFragment()
            fragment.arguments = args
            return fragment
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("CRIMEFRAGMENT", "onCreate")
        val crimeID:UUID? = requireArguments().getSerializable(ARG_CRIME_ID) as UUID?
        mCrime = CrimeLab.getCrime(crimeID)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        Log.d("CRIMEFRAGMENT", "onCreateView")
        val v:View? = inflater.inflate(R.layout.fragment_crime, container, false)
        mTitleField = v!!.findViewById (R.id.crime_title)
        mTitleField!!.setText (mCrime!!.mTitle)
        mTitleField!!.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
//This runs every single time the text changes, even slightly in the edittext box
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                mCrime!!.mTitle = s!!.toString()
            }

            override fun afterTextChanged (s: Editable) {

            }
        })

        mSolvedCheckBox = v.findViewById(R.id.crime_solved);
        mSolvedCheckBox!!.isChecked = mCrime!!.mSolved
        mSolvedCheckBox!!.setOnCheckedChangeListener { _, isChecked ->
            mCrime!!.mSolved = isChecked
        }
        //In reality, we are creating a new object and overriding a fun in
        //that object.  Since a listener usually only has one fun that can
        //be overridden, there is no need to give all the details of that
        //fun, like the name, or sometimes the parameters, just write the code
        mDateButton = v.findViewById(R.id.crime_date)
        mDateButton!!.text = mCrime!!.mDate!!.toString()
        mDateButton!!.isEnabled = false

         return v
    }


    override fun onStart() {
        Log.d("CRIMEFRAGMENT", "onStart")
        super.onStart()
    }

    override fun onStop() {
        Log.d("CRIMEFRAGMENT", "onStop")
        super.onStop()
    }

    override fun onDetach() {
        Log.d("CRIMEFRAGMENT", "onDetach")
        super.onDetach()
    }

    override fun onDestroyView() {
        Log.d("CRIMEFRAGMENT", "onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d("CRIMEFRAGMENT", "onDestroy")
        super.onDestroy()
    }

    override fun onPause() {
        Log.d("CRIMEFRAGMENT", "onPause")
        super.onPause()
    }

    override fun onResume() {
        Log.d("CRIMEFRAGMENT", "onResume")
        super.onResume()
    }



}