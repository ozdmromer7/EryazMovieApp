package com.omerbartu.eryazmovieapp.app.service

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.omerbartu.eryazmovieapp.app.datamodel.Movie

@Dao
interface MovieDAO {

    @Query(value = "SELECT * FROM movie WHERE isChecked=1 ORDER BY id ASC")
    fun getFavoriteMovies():LiveData<List<Movie>>

    @Query(value = "SELECT * FROM movie")
    fun getAllMovie():LiveData<List<Movie>>

    @Insert
    suspend fun insertAllMovies(vararg movie:Movie):List<Long>

    @Update
    suspend fun updateMovie(movie: Movie)

    @Query(value = "DELETE FROM movie")
    suspend fun deleteCountry()
}