package com.omerbartu.eryazmovieapp.app.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.omerbartu.eryazmovieapp.app.datamodel.Model
import com.omerbartu.eryazmovieapp.app.datamodel.Movie
import com.omerbartu.eryazmovieapp.app.datamodel.Video
import com.omerbartu.eryazmovieapp.app.datamodel.VideoInformation
import com.omerbartu.eryazmovieapp.app.service.MovieDatabaseRoom
import com.omerbartu.eryazmovieapp.app.service.Retrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel(application: Application) : BaseViewModel(application) {

    val dao = MovieDatabaseRoom.getDatabase(getApplication()).movieDao()
    val movies = MutableLiveData<List<Movie>>()
    var favoriteMovie: LiveData<List<Movie>>
    var allMovieFromRoom: LiveData<List<Movie>>
    val videos =MutableLiveData<List<VideoInformation>>()

    init {
        favoriteMovie = dao.getFavoriteMovies()
        allMovieFromRoom = dao.getAllMovie()
    }


    fun refreshData() {

        getDataFromApi()

    }

    fun getDataFromRoom() {

        favoriteMovie = dao.getFavoriteMovies()
    }

    fun getDataFromApi() {

        val service = Retrofit.getData()

        service.getMovie().enqueue(object : Callback<Model> {
            override fun onResponse(call: Call<Model>, response: Response<Model>) {

                response.body()?.results?.let {

                    movies.value = it
                    //storeInRoom(it)

                }

            }

            override fun onFailure(call: Call<Model>, t: Throwable) {

                t.printStackTrace()
            }


        })
    }

    fun getVideo(movieId :Int){

        val service =Retrofit.getData()
        service.getVideo(movieId).enqueue(object: Callback<Video>{
            override fun onResponse(call: Call<Video>, response: Response<Video>) {

                response.body()?.results?.let {
                    videos.value=it
                }
            }

            override fun onFailure(call: Call<Video>, t: Throwable) {


            }


        })
    }

    fun storeInRoom(list: List<Movie>) {

        launch {
            dao.deleteCountry()
            var listLong = dao.insertAllMovies(*list.toTypedArray())
            var i = 0
            while (i < list.size) {
                list.get(i).id = listLong.get(i).toInt()
                i++
            }
        }
    }

    fun updateMovie(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {

            dao.updateMovie(movie)
        }
    }
}