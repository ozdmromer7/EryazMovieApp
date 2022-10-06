package com.omerbartu.eryazmovieapp.app.datamodel

import com.google.gson.annotations.SerializedName

data class Model(
    @SerializedName("page")
    var page:Int,
    @SerializedName("results")
    var results:List<Movie>
)