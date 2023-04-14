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
import com.omerbartu.eryazmovieapp.app.adapter.NowPlayAdapter
import com.omerbartu.eryazmovieapp.app.datamodel.Movie
import com.omerbartu.eryazmovieapp.app.viewmodel.NowPlayViewModel
import com.omerbartu.eryazmovieapp.databinding.FragmentNowPlayBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NowPlayFragment : Fragment() {

    private var _binding: FragmentNowPlayBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: NowPlayAdapter
    private val movieList = arrayListOf<Movie>()

    @Inject
    lateinit var viewModel: NowPlayViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNowPlayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.refreshData()

        observeLiveData()

        binding.nowPlayRecycler.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        adapter = NowPlayAdapter(movieList, requireContext(), onItemClick = {
            val action = NowPlayFragmentDirections.actionNowPlayFragment2ToMovieDetailsFragment()
            action.movie = it
            action.direction = "3"
            Navigation.findNavController(view).navigate(action)
        })

        binding.nowPlayRecycler.adapter = adapter


    }

    fun observeLiveData() {

        viewModel.movies.observe(viewLifecycleOwner, Observer {

            it?.let {

                adapter.updateMovieList(it)

            }


        })
    }

}