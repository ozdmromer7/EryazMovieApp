package com.omerbartu.eryazmovieapp.app.service

import com.omerbartu.eryazmovieapp.app.util.Constant.BASE_URL
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

class Retrofit {

    companion object {

        fun retrofitBuild(): Retrofit {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit
        }

        fun getData(): ApiService {

            return retrofitBuild().create(ApiService::class.java)

        }
    }
}