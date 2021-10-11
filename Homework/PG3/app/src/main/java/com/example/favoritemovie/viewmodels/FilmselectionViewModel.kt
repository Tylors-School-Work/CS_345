package com.example.favoritemovie.viewmodels

import androidx.lifecycle.ViewModel

class FilmSelectionViewModel : ViewModel() {

    var genreSelected: String = ""

    // 5 arrays that contain the film options for a given genre
    private val thriller = arrayOf(
        "thriller",
        "Nobody",
        "The Number 23",
        "The Women in the Window",
        "The Silence of the Lambs",
        "Gone girl")

    private val horror = arrayOf(
        "horror",
        "The conjuring",
        "Midsommer",
        "The Exorcism of Emily Rose",
        "The Thing",
        "Blood Red sky")

    private val fantasy = arrayOf(
        "fantasy",
        "Lord of the Rings: Fellowship of the Ring",
        "The Dark Tower",
        "The Hobbit: And Unexpected Journey",
        "Lord of the Rings: The Two Towers",
        "Start Wars: The Rise of Skywalker")

    private val comedy = arrayOf(
        "comedy",
        "Office Space",
        "Step Brothers",
        "Hot Rod",
        "Anchorman",
        "Coneheads")

    private val action = arrayOf(
        "action",
        "Snowpiercer",
        "Batman Begins",
        "Bright",
        "Den of Thieves",
        "John Wick")

    fun displayFilms(genre: String): Array<String> {
        return when(genre) {
            thriller[0] -> thriller
            horror[0] -> horror
            fantasy[0] -> fantasy
            comedy[0] -> comedy
            else -> action
        }
    }
}