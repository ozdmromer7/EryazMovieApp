package com.omerbartu.eryazmovieapp.app.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.omerbartu.eryazmovieapp.app.adapter.AllMovieAdapter
import com.omerbartu.eryazmovieapp.app.datamodel.Model
import com.omerbartu.eryazmovieapp.app.datamodel.Movie
import com.omerbartu.eryazmovieapp.app.service.RetrofitClient
import com.omerbartu.eryazmovieapp.app.viewmodel.MovieViewModel
import com.omerbartu.eryazmovieapp.databinding.FragmentMovieBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieFragment : Fragment() {

    private var _binding: FragmentMovieBinding? = null

    private val binding get() = _binding!!

    private lateinit var adapter: AllMovieAdapter
    private lateinit var viewModel: MovieViewModel
    private var movieList= arrayListOf<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel=ViewModelProvider(this).get(MovieViewModel::class.java)

        viewModel.refreshData()

        observeLiveData()

        adapter= AllMovieAdapter(movieList,requireContext())
        binding.allMovieRecycler.layoutManager=StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL)
        binding.allMovieRecycler.adapter=adapter

        binding.moviesToolbar.setNavigationOnClickListener {

        }




    }
    fun observeLiveData(){

        viewModel.movies.observe(viewLifecycleOwner, Observer {

            it?.let {
                adapter.updateMovieList(it)
            }




        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}