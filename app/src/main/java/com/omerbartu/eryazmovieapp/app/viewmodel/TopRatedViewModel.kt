package com.omerbartu.eryazmovieapp.app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.omerbartu.eryazmovieapp.app.datamodel.Model
import com.omerbartu.eryazmovieapp.app.datamodel.Movie
import com.omerbartu.eryazmovieapp.app.service.Retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopRatedViewModel:ViewModel() {

    val movies = MutableLiveData<List<Movie>>()

    fun refreshData(){

        getDataFromApi()

    }

    fun getDataFromApi(){

        Retrofit.getData().getTopRatedMovie().enqueue(object:Callback<Model>{
            override fun onResponse(call: Call<Model>, response: Response<Model>) {

                response.body()?.let {
                    response.body()!!.results?.let {

                        movies.value=it

                    }

                }

            }

            override fun onFailure(call: Call<Model>, t: Throwable) {
                t.printStackTrace()
            }


        })

    }
}