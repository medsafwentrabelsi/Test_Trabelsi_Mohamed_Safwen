package com.safwen.boxffficetest.data.entities

import com.google.gson.annotations.SerializedName

class SearchMovieResponse (
    @SerializedName("Search")
    var searchResult : List<MovieObject>
)