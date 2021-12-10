/*
    Tylor J. Hanshaw

    -- This is my singleton class that creates all 30 movies
    -- This acts as a sort of ViewModel, all data is essentially held here
*/

package com.example.pg4.data

import java.util.*

// This acts as a ViewModel, storing data while View's do their magic and display the data
object MovieLab {

    private val allMovies: MutableList<Movie>?

    val movies: List<Movie>? get() = allMovies

    fun getMovie(id: UUID?): Movie? {
        for(movie in allMovies!!) if(movie.uid == id) return movie
        return null
    }

    // This method is only called in my "FragmentMovie" fragment
    // Used to update the selected movies star rating, so when the user navigates back to "FragmentMovieList" they will see the changes made
    fun changeMovieRating(newRating: Int, movieId: UUID?) {
        for(i in allMovies!!.indices) if(allMovies[i].uid == movieId) allMovies[i].starRating = newRating
    }

    init {
        allMovies = ArrayList()

        // Add all 30 movies here
        // #1
        var movie1 = Movie()
        movie1.movieTitle = "Halloween"
        movie1.movieDirector = "Rob Zombie"
        movie1.movieReleaseDate = "1978"
        allMovies.add(movie1)

        // #2
        movie1 = Movie()
        movie1.movieTitle = "The Conjuring"
        movie1.movieDirector = "James Wan"
        movie1.movieReleaseDate = "2013"
        allMovies.add(movie1)

        // #3
        movie1 = Movie()
        movie1.movieTitle = "The Visit"
        movie1.movieDirector = "M. Night Shyamalan"
        movie1.movieReleaseDate = "2015"
        allMovies.add(movie1)

        // #4
        movie1 = Movie()
        movie1.movieTitle = "Prometheus"
        movie1.movieDirector = "Ridley Scott"
        movie1.movieReleaseDate = "2012"
        allMovies.add(movie1)

        // #5
        movie1 = Movie()
        movie1.movieTitle = "Wrinkles the Clown"
        movie1.movieDirector = "Michael Beach Nichols"
        movie1.movieReleaseDate = "2019"
        allMovies.add(movie1)

        // #6
        movie1 = Movie()
        movie1.movieTitle = "The Purge"
        movie1.movieDirector = "James DeMonaco"
        movie1.movieReleaseDate = "2016"
        allMovies.add(movie1)

        // #7
        movie1 = Movie()
        movie1.movieTitle = "IT"
        movie1.movieDirector = "Tommy Lee Wallace"
        movie1.movieReleaseDate = "1990"
        allMovies.add(movie1)

        // #8
        movie1 = Movie()
        movie1.movieTitle = "Back to the Future"
        movie1.movieDirector = "Robert Zemeckis"
        movie1.movieReleaseDate = "1985"
        allMovies.add(movie1)

        // #9
        movie1 = Movie()
        movie1.movieTitle = "1922"
        movie1.movieDirector = "Zak Hilditch"
        movie1.movieReleaseDate = "2017"
        allMovies.add(movie1)

        // #10
        movie1 = Movie()
        movie1.movieTitle = "Gerald's Game"
        movie1.movieDirector = "Mike Flanagan"
        movie1.movieReleaseDate = "2017"
        allMovies.add(movie1)

        // #11
        movie1 = Movie()
        movie1.movieTitle = "Carrie"
        movie1.movieDirector = "Brian De Palma"
        movie1.movieReleaseDate = "1976"
        allMovies.add(movie1)

        // #12
        movie1 = Movie()
        movie1.movieTitle = "Prometheus"
        movie1.movieDirector = "Ridley Scott"
        movie1.movieReleaseDate = "2012"
        allMovies.add(movie1)

        // #13
        movie1 = Movie()
        movie1.movieTitle = "The Green Inferno"
        movie1.movieDirector = "Eli Roth"
        movie1.movieReleaseDate = "2014"
        allMovies.add(movie1)

        // #14
        movie1 = Movie()
        movie1.movieTitle = "Cannibal Holocaust"
        movie1.movieDirector = "Ruggero Deodato"
        movie1.movieReleaseDate = "1985"
        allMovies.add(movie1)

        // #15
        movie1 = Movie()
        movie1.movieTitle = "The Thing"
        movie1.movieDirector = "Christian Nyby"
        movie1.movieReleaseDate = "1951"
        allMovies.add(movie1)

        // #16
        movie1 = Movie()
        movie1.movieTitle = "Psycho"
        movie1.movieDirector = "Alfred Hitchcock"
        movie1.movieReleaseDate = "1960"
        allMovies.add(movie1)

        // #17
        movie1 = Movie()
        movie1.movieTitle = "The Blair Witch Project"
        movie1.movieDirector = "Eduardo Sánchez, Daniel Myrick"
        movie1.movieReleaseDate = "1999"
        allMovies.add(movie1)

        // #18
        movie1 = Movie()
        movie1.movieTitle = "Paranormal Activity"
        movie1.movieDirector = "Oren Peli"
        movie1.movieReleaseDate = "2009"
        allMovies.add(movie1)

        // #19
        movie1 = Movie()
        movie1.movieTitle = "The Grudge"
        movie1.movieDirector = "Takashi Shimizu"
        movie1.movieReleaseDate = "2004"
        allMovies.add(movie1)

        // #20
        movie1 = Movie()
        movie1.movieTitle = "Candyman"
        movie1.movieDirector = "Bernard Rose"
        movie1.movieReleaseDate = "1992"
        allMovies.add(movie1)

        // #21
        movie1 = Movie()
        movie1.movieTitle = "Scream"
        movie1.movieDirector = "Wes Craven"
        movie1.movieReleaseDate = "1996"
        allMovies.add(movie1)

        // #22
        movie1 = Movie()
        movie1.movieTitle = "Wrong Turn"
        movie1.movieDirector = "Rob Schmidt"
        movie1.movieReleaseDate = "2003"
        allMovies.add(movie1)

        // #23
        movie1 = Movie()
        movie1.movieTitle = "Room 237"
        movie1.movieDirector = "Rodney Ascher"
        movie1.movieReleaseDate = "2013"
        allMovies.add(movie1)

        // #24
        movie1 = Movie()
        movie1.movieTitle = "1408"
        movie1.movieDirector = "Mikael Håfström"
        movie1.movieReleaseDate = "2007"
        allMovies.add(movie1)

        // #25
        movie1 = Movie()
        movie1.movieTitle = "The Mist"
        movie1.movieDirector = "Frank Darabont"
        movie1.movieReleaseDate = "2007"
        allMovies.add(movie1)

        // #26
        movie1 = Movie()
        movie1.movieTitle = "Shutter Island"
        movie1.movieDirector = "Martin Scorsese"
        movie1.movieReleaseDate = "2010"
        allMovies.add(movie1)

        // #27
        movie1 = Movie()
        movie1.movieTitle = "The Shining"
        movie1.movieDirector = "Stanley Kubrick"
        movie1.movieReleaseDate = "1980"
        allMovies.add(movie1)

        // #28
        movie1 = Movie()
        movie1.movieTitle = "Christine"
        movie1.movieDirector = "John Carpenter"
        movie1.movieReleaseDate = "1983"
        allMovies.add(movie1)

        // #29
        movie1 = Movie()
        movie1.movieTitle = "The Predator"
        movie1.movieDirector = "Shane Black"
        movie1.movieReleaseDate = "2018"
        allMovies.add(movie1)

        // #30
        movie1 = Movie()
        movie1.movieTitle = "Tremors"
        movie1.movieDirector = "Ron Underwood"
        movie1.movieReleaseDate = "1990"
        allMovies.add(movie1)
    }
}