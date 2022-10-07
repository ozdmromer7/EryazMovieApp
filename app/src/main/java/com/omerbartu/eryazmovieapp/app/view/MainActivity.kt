package com.omerbartu.eryazmovieapp.app.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
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


        var navController=findNavController(R.id.fragmentContainerView2)
        binding.chipNavigationBar.setupWithNavController(navController)
        navController.addOnDestinationChangedListener(NavController.OnDestinationChangedListener{controller, destination, arguments ->

            when(destination.id){

                R.id.movies -> binding.chipNavigationBar.visibility=View.VISIBLE
                R.id.toprated -> binding.chipNavigationBar.visibility=View.VISIBLE
                R.id.nowplay -> binding.chipNavigationBar.visibility=View.VISIBLE
                else-> binding.chipNavigationBar.visibility=View.GONE
            }
        })




        }

}
