package com.omerbartu.eryazmovieapp.app.view

import android.content.ContentValues.TAG
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.omerbartu.eryazmovieapp.R
import com.omerbartu.eryazmovieapp.app.adapter.AllMovieAdapter
import com.omerbartu.eryazmovieapp.app.datamodel.Movie
import com.omerbartu.eryazmovieapp.app.viewmodel.MovieViewModel
import com.omerbartu.eryazmovieapp.databinding.FragmentMovieBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MovieFragment : Fragment(), AllMovieAdapter.Listener, SearchView.OnQueryTextListener {

    private var _binding: FragmentMovieBinding? = null

    private val binding get() = _binding!!

    private lateinit var adapter: AllMovieAdapter

    private val args:MovieFragmentArgs by navArgs()

    private lateinit var sp : SharedPreferences

    @Inject
    lateinit var viewModel: MovieViewModel
    private var movieList = arrayListOf<Movie>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        binding.moviesToolbar.setOnQueryTextListener(this)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val comeFromDetails =args.comeFromDetails

        sp = requireActivity().applicationContext.getSharedPreferences("X", Context.MODE_PRIVATE)
        val edit = sp.edit()
        val page = sp.getInt("page", 1)
        if (page == 1) {
            binding.backButtonn.visibility = View.INVISIBLE
        }


        observeLiveData()

        if (comeFromDetails){
            viewModel.refreshData(sp.getInt("page",1).toString())
        }
        else{
            viewModel.refreshData("1")
        }



        binding.allMovieRecycler.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        adapter = AllMovieAdapter(movieList, this, requireContext(), onItemClick = {
            val action = MovieFragmentDirections.actionMovieFragmentToMovieDetailsFragment()
            action.movie = it
            action.direction = "1"
            Navigation.findNavController(view).navigate(action)
        })

        binding.allMovieRecycler.adapter = adapter

        binding.nextButton.setOnClickListener {

            binding.backButtonn.visibility = View.VISIBLE
            var page = sp.getInt("page", 1)
            page++
            edit.putInt("page", page)
            edit.apply()
            observeLiveData()
            viewModel.refreshData(page.toString())

        }
        binding.backButtonn.setOnClickListener {

            var page = sp.getInt("page", 1)
            page--
            if (page == 1) {
                binding.backButtonn.visibility = View.INVISIBLE
            }
            edit.putInt("page", page)
            edit.apply()
            observeLiveData()
            viewModel.refreshData(page.toString())

        }

    }

    private fun observeLiveData() {

        viewModel.movies.observe(viewLifecycleOwner) {

            it?.let {
                adapter.updateMovieList(it)
            }
        }
    }

    private fun search(searchWords: String) {

        val searchMovieList: ArrayList<Movie> = ArrayList()
        viewModel.movies.value?.forEach { movie ->
            if (movie.title.lowercase().contains(searchWords.lowercase())) {
                searchMovieList.add(movie)

            }
        }
        adapter.updateMovieList(searchMovieList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(movie: Movie) {

        viewModel.updateMovie(movie)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {

        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {

        Log.i(TAG, "$newText")

        newText?.let {
            search(it)
        }
        return true
    }

}