package com.omerbartu.eryazmovieapp.app.datamodel

import com.google.gson.annotations.SerializedName

data class Video(
    @SerializedName("id")
    val id: Int,
    @SerializedName("results")
    val results:List<VideoInformation>
)