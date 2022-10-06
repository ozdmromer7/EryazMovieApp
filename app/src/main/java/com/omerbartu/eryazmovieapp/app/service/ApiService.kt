package com.omerbartu.eryazmovieapp.app.service

import com.omerbartu.eryazmovieapp.app.datamodel.Model
import com.omerbartu.eryazmovieapp.app.service.util.Constant

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Headers

interface ApiService {


    @GET("3/movie/popular?api_key=bed61d549939767402884846d5325c72")
    fun getMovie() : Call<Model>


    @GET("3/movie/top_rated?apiKey=bed61d549939767402884846d5325c72")
    fun getTopRatedMovie() : Call<Model>

}