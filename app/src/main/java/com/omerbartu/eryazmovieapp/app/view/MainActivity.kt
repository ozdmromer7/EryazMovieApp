package com.omerbartu.eryazmovieapp.app.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.omerbartu.eryazmovieapp.R
import com.omerbartu.eryazmovieapp.app.datamodel.Model
import com.omerbartu.eryazmovieapp.app.datamodel.Movie
import com.omerbartu.eryazmovieapp.app.service.Retrofit
import com.omerbartu.eryazmovieapp.app.service.RetrofitClient
import com.omerbartu.eryazmovieapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)




        binding.chipNavigationBar.setOnItemSelectedListener {

            when(it){
                R.id.movies-> changeFragment(MovieFragment())
                R.id.nowplay-> changeFragment(NowPlayFragment())
                R.id.popular-> changeFragment(PopularFragment())
                R.id.toprated-> changeFragment(TopRatedFragment())

                else ->{


                }
            }
            return@setOnItemSelectedListener
        }

        }


    fun changeFragment(fragment: Fragment){

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.linearLayout, fragment)
        fragmentTransaction.commit()


    }

}
