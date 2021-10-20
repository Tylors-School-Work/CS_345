/*
    This is my singleton class that creates all 30 movies
*/

package com.example.pg4.data

import java.util.*

object MovieLab {

    private val allMovies: MutableList<Movie>?

    val movies: List<Movie>? get() = allMovies

    fun getMovie(id: UUID?): Movie? {
        for(movie in allMovies!!)
            if(movie.uid == id) return movie
        return null
    }

    fun changeMovieRating(newRating: Int, movieId: UUID?) {
        for(i in allMovies!!.indices) {
            if(allMovies[i].uid == movieId) allMovies[i].starRating = newRating
        }
    }

    init {
        allMovies = ArrayList()

        // Add all 30 movies here
        // Currently dummy data, will turn in again tomorrow evening with everything fully fleshed out
        val movie1 = Movie()
        movie1.movieTitle = "Halloween"
        movie1.movieDirector = "Rob Zombie"
        movie1.movieReleaseDate = "1978"
        allMovies.add(movie1)

        val movie2 = Movie()
        movie2.movieTitle = "The Conjuring"
        movie2.movieDirector = "James Wan"
        movie2.movieReleaseDate = "2013"
        allMovies.add(movie2)

        val movie3 = Movie()
        movie3.movieTitle = "The Visit"
        movie3.movieDirector = "M. Night Shyamalan"
        movie3.movieReleaseDate = "2015"
        allMovies.add(movie3)

        val movie4 = Movie()
        movie4.movieTitle = "Prometheus"
        movie4.movieDirector = "Ridley Scott"
        movie4.movieReleaseDate = "2012"
        allMovies.add(movie4)

        val movie5 = Movie()
        movie5.movieTitle = "Wrinkles the Clown"
        movie5.movieDirector = "Michael Beach Nichols"
        movie5.movieReleaseDate = "2019"
        allMovies.add(movie5)

        val movie6 = Movie()
        movie6.movieTitle = "The Purge"
        movie6.movieDirector = "James DeMonaco"
        movie6.movieReleaseDate = "2016"
        allMovies.add(movie6)

        val movie7 = Movie()
        movie7.movieTitle = "IT"
        movie7.movieDirector = "Tommy Lee Wallace"
        movie7.movieReleaseDate = "1990"
        allMovies.add(movie7)
    }
}