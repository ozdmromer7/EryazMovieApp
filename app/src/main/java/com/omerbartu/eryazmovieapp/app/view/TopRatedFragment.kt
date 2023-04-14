package com.omerbartu.eryazmovieapp.app.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.omerbartu.eryazmovieapp.app.adapter.TopRatedAdapter
import com.omerbartu.eryazmovieapp.app.datamodel.Movie
import com.omerbartu.eryazmovieapp.app.viewmodel.TopRatedViewModel
import com.omerbartu.eryazmovieapp.databinding.FragmentTopRatedBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TopRatedFragment : Fragment() {

    private var _binding: FragmentTopRatedBinding? = null
    private val binding get() = _binding!!
    @Inject
    lateinit var viewModel:TopRatedViewModel
    private var movieList= arrayListOf<Movie>()
    private lateinit var adapter:TopRatedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTopRatedBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.refreshData()

        observeLiveData()

        binding.topRatedRecycler.layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        adapter= TopRatedAdapter(movieList,requireContext(),onItemClick={
            val action=TopRatedFragmentDirections.actionTopRatedFragmentToMovieDetailsFragment()
            action.movie=it
            action.direction="2"
            Navigation.findNavController(view).navigate(action)
        })

        binding.topRatedRecycler.adapter=adapter


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun observeLiveData(){
        viewModel.movies.observe(viewLifecycleOwner, Observer {

            it?.let {
                adapter.updateMovieList(it)
            }

        })

    }

}