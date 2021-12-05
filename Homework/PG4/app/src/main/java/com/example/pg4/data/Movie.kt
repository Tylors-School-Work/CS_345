/*
*       Tylor J. Hanshaw
*
*   - This is my data class, defines what a movie is in this program
* */

package com.example.pg4.data

import java.util.*

/*   ************************
 *   ******RATING SCALE******
 *   ************************
 *
 *   Empty star image {
 *      0 -> Worst movie I've ever seen
 *      1 -> Won't ever see it again, bad but not the worst moving ever
 *   }
 *
 *   Half filled star image {
 *      2 -> I probably won't watch it again, just kind of "alright"
 *      3 -> I wouldn't go out of my way to watch it again, but if it was on TV i'd watch it - pretty OK
 *      4 -> I'd probably watch this movie again, pretty good
 *   }
 *
 *   Totally filled star image {
 *      5 -> Best movie I've ever seen, coming back to the theaters next weekend!
 *   }
**/

data class Movie(
    val uid: UUID? = UUID.randomUUID(),
    var movieTitle: String = "",
    var movieDirector: String = "",
    var movieReleaseDate: String = "",
    var starRating: Int = 0, // All movies start with a rating of 0
    val imagePath: String? = null
)
