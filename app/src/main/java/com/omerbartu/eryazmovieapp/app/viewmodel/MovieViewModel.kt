package com.omerbartu.eryazmovieapp.app.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.omerbartu.eryazmovieapp.app.datamodel.Model
import com.omerbartu.eryazmovieapp.app.datamodel.Movie
import com.omerbartu.eryazmovieapp.app.datamodel.Video
import com.omerbartu.eryazmovieapp.app.datamodel.VideoInformation
import com.omerbartu.eryazmovieapp.app.service.ApiService
import com.omerbartu.eryazmovieapp.app.service.MovieDatabaseRoom
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class MovieViewModel @Inject constructor(
    private val apiService: ApiService,
    application: Application
) : BaseViewModel(application) {

    private val dao = MovieDatabaseRoom.getDatabase(getApplication()).movieDao()
    val movies = MutableLiveData<List<Movie>>()
    var favoriteMovie: LiveData<List<Movie>>
    private var allMovieFromRoom: LiveData<List<Movie>>
    val videos = MutableLiveData<List<VideoInformation>>()
    val page = MutableLiveData<Int>()

    init {
        favoriteMovie = dao.getFavoriteMovies()
        allMovieFromRoom = dao.getAllMovie()
    }


    fun refreshData(pageNum: String) {

        getDataFromApi(pageNum)

    }

    fun getDataFromRoom() {

        favoriteMovie = dao.getFavoriteMovies()
    }

    private fun getDataFromApi(pageNum: String) {

        val service = apiService.getMovie(pageNum)

        service.enqueue(object : Callback<Model> {
            override fun onResponse(call: Call<Model>, response: Response<Model>) {

                response.body()?.results?.let {

                    movies.value = it
                    //storeInRoom(it)

                }
                response.body()?.page.let {

                    page.value = it!!

                }

            }

            override fun onFailure(call: Call<Model>, t: Throwable) {

                t.printStackTrace()
            }


        })
    }

    fun getVideo(movieId: Int) {

        val service = apiService.getVideo(movieId)
        service.enqueue(object : Callback<Video> {
            override fun onResponse(call: Call<Video>, response: Response<Video>) {

                response.body()?.results?.let {
                    videos.value = it
                }
            }

            override fun onFailure(call: Call<Video>, t: Throwable) {

            }

        })
    }

//    fun storeInRoom(list: List<Movie>) {
//
//        launch {
//            dao.deleteCountry()
//            val listLong = dao.insertAllMovies(*list.toTypedArray())
//            var i = 0
//            while (i < list.size) {
//                list[i].id = listLong[i].toInt()
//                i++
//            }
//        }
//    }

    fun updateMovie(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {

            dao.updateMovie(movie)
        }
    }
}