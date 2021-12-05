/**
 *  Tylor J. Hanshaw
 */

package com.example.pg5.localdata.model

import java.util.*

/**
 *  Data class that holds the model for each database entry
 */

data class Movie(
    val movieName: String? = null,
    val releaseYear: Int? = null,
    val genre: String? = null,
    val description: String? = null,
    val id: String = UUID.randomUUID().toString() // ID for each movie
)
