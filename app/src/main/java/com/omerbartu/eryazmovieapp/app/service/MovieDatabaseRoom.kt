package com.omerbartu.eryazmovieapp.app.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.omerbartu.eryazmovieapp.app.datamodel.Movie

@Database(entities = arrayOf(Movie::class), version = 1)
abstract class MovieDatabaseRoom :RoomDatabase(){
    abstract fun movieDao():MovieDAO

    companion object{

        @Volatile
        private var INSTANCE :MovieDatabaseRoom ?= null


        fun getDatabase(context:Context) : MovieDatabaseRoom{

            var tempInstance= INSTANCE
            if (tempInstance!=null){

                return tempInstance
            }
            synchronized(this){

                val instance = Room.databaseBuilder(context,MovieDatabaseRoom::class.java,"movie_database")
                    .build()
                tempInstance=instance
                return tempInstance as MovieDatabaseRoom
            }

        }
    }
}