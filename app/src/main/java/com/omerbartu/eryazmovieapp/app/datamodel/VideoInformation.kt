package com.omerbartu.eryazmovieapp.app.datamodel

import com.google.gson.annotations.SerializedName

data class VideoInformation(
    @SerializedName("name")
     val name:String,
    @SerializedName("key")
     val key:String
)