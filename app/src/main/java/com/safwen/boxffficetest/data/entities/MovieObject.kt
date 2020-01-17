package com.safwen.boxffficetest.data.entities

import com.google.gson.annotations.SerializedName

data class MovieObject(
    @SerializedName("Title")
    var movieTitle: String?,
    @SerializedName("Year")
    var movieYear: String?,
    @SerializedName("Poster")
    var moviePoster: String?,
    @SerializedName("imdbID")
    var imdbID: String?,
    @SerializedName("Released")
    var releasedDate: String?,
    @SerializedName("Ratings")
    var rating: ArrayList<RatingObject>?
)