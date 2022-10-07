package com.omerbartu.eryazmovieapp.app.service

import com.omerbartu.eryazmovieapp.app.datamodel.Model
import com.omerbartu.eryazmovieapp.app.util.Constant

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Headers

interface ApiService {


    @GET("3/movie/popular?api_key=${Constant.API_KEY}")
    fun getMovie() : Call<Model>

    @GET("3/movie/top_rated?api_key=${Constant.API_KEY}")
    fun getTopRatedMovie() : Call<Model>

    @GET("3/movie/now_playing?api_key=${Constant.API_KEY}")
    fun getNowPlayingMovie() : Call<Model>

}