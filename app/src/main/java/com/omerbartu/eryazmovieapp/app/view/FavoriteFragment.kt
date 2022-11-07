package com.omerbartu.eryazmovieapp.app.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.omerbartu.eryazmovieapp.R
import com.omerbartu.eryazmovieapp.app.adapter.AllMovieAdapter
import com.omerbartu.eryazmovieapp.app.adapter.FavoriteAdapter
import com.omerbartu.eryazmovieapp.app.datamodel.Movie
import com.omerbartu.eryazmovieapp.app.viewmodel.MovieViewModel
import com.omerbartu.eryazmovieapp.databinding.FragmentFavoriteBinding
import com.omerbartu.eryazmovieapp.databinding.FragmentMovieBinding


class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: FavoriteAdapter
    private lateinit var viewModel: MovieViewModel
    private var movieList= arrayListOf<Movie>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel= ViewModelProvider(this).get(MovieViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.favRecycler.layoutManager=StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL)
        adapter= FavoriteAdapter(movieList,requireContext())
        binding.favRecycler.adapter=adapter

        viewModel.getDataFromRoom()

        observeFavList()

    }

    fun observeFavList(){

        viewModel.favoriteMovie.observe(viewLifecycleOwner,Observer{

           if (it.isEmpty()){
               Toast.makeText(requireContext(),"Boş",Toast.LENGTH_SHORT).show()

           }
            else{
                adapter.updateMovieList(it)
           }
        })

    }

}