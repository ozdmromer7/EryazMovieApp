package com.omerbartu.eryazmovieapp.app.service

class RetrofitClient {

    companion object{



        fun getData(): ApiService{

           return Retrofit.retrofitBuild().create(ApiService::class.java)

        }

    }
}