package com.omerbartu.eryazmovieapp.app.datamodel

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("title")
    var title:String,
    @SerializedName("popularity")
     var popularity:Double,
    @SerializedName("release_date")
     var releaseDate:String,
    @SerializedName("vote_average")
    var voteAverage:Double,
    @SerializedName("poster_path")
    var posterPath:String,
    @SerializedName("vote_count")
    var voteCount:Int,
    @SerializedName("overview")
    var overview:String
):java.io.Serializable