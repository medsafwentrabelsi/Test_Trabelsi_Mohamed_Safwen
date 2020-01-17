package com.safwen.boxffficetest.data.entities

import com.google.gson.annotations.SerializedName

data class RatingObject (
    @SerializedName("Source")
    var source:String,
    @SerializedName("Value")

    var value:String
)