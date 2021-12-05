package com.example.testingautocomplete

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView

class MainActivity : AppCompatActivity() {

    private lateinit var autoCompleteET: AutoCompleteTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        autoCompleteET = findViewById(R.id.autoComplete)

//        val listOfPlants = arrayListOf(
//            "Raspberry",
//            "Wild Columbine",
//            "Harebell",
//            "Common Boneset",
//            "Smooth Aster",
//            "Red Maple"
//        )

        val listOfPlants = mutableListOf(
            "Raspberry",
            "Wild Columbine",
            "Harebell",
            "Common Boneset",
            "Smooth Aster",
            "Red Maple"
        )

        val stringAdapter = ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, listOfPlants)

        autoCompleteET.setAdapter(stringAdapter)

    }
}