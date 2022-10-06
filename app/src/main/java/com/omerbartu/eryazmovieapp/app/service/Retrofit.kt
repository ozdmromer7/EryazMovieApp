package com.omerbartu.eryazmovieapp.app.service

import com.omerbartu.eryazmovieapp.app.service.util.Constant.BASE_URL
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit {

    companion object{

        fun retrofitBuild():retrofit2.Retrofit{
            val retrofit=retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit
        }

    }

}