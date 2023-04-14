package com.omerbartu.eryazmovieapp.app.di

import com.omerbartu.eryazmovieapp.app.service.ApiService
import com.omerbartu.eryazmovieapp.app.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun retrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun getServiceInstance(retrofit: Retrofit): ApiService {

        return retrofit.create(ApiService::class.java)

    }
}