package com.omerbartu.eryazmovieapp.app.datamodel

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie")
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
    var overview:String,
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var isChecked:Int=0):java.io.Serializable