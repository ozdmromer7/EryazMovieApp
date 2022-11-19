package com.omerbartu.eryazmovieapp.app.service

import com.omerbartu.eryazmovieapp.app.datamodel.Model
import com.omerbartu.eryazmovieapp.app.datamodel.Video
import com.omerbartu.eryazmovieapp.app.util.Constant

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @GET("3/movie/popular?api_key=${Constant.API_KEY}")
    fun getMovie(@Query("page") page:String) : Call<Model>

    @GET("3/movie/top_rated?api_key=${Constant.API_KEY}")
    fun getTopRatedMovie() : Call<Model>

    @GET("3/movie/now_playing?api_key=${Constant.API_KEY}")
    fun getNowPlayingMovie() : Call<Model>

    @GET("3/movie/{movieId}/videos?api_key=${Constant.API_KEY}")
    fun getVideo(@Path("movieId") movieId:Int) : Call<Video>
}