package com.omerbartu.eryazmovieapp.app.view

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.omerbartu.eryazmovieapp.app.adapter.AllMovieAdapter
import com.omerbartu.eryazmovieapp.app.datamodel.Movie
import com.omerbartu.eryazmovieapp.app.viewmodel.MovieViewModel
import com.omerbartu.eryazmovieapp.databinding.FragmentMovieBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieFragment : Fragment(),AllMovieAdapter.Listener{

    private var _binding: FragmentMovieBinding? = null

    private val binding get() = _binding!!

    private lateinit var adapter: AllMovieAdapter
    @Inject
    lateinit var viewModel: MovieViewModel
    private var movieList= arrayListOf<Movie>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sp = requireActivity().applicationContext.getSharedPreferences("X",Context.MODE_PRIVATE)
        val edit = sp.edit()
        edit.putInt("page",1)
        edit.apply()
        var page= sp!!.getInt("page",1)
        if (page==1){
            binding.backButtonn.visibility=View.INVISIBLE
        }

        observeLiveData()


        viewModel.refreshData("1")


        binding.allMovieRecycler.layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)

        adapter= AllMovieAdapter(movieList,this,requireContext(),onItemClick={
            val action=MovieFragmentDirections.actionMovieFragmentToMovieDetailsFragment()
            action.movie=it
            action.direction="1"
            Navigation.findNavController(view).navigate(action)
        })

        binding.allMovieRecycler.adapter=adapter

        binding.nextButton.setOnClickListener {

            binding.backButtonn.visibility=View.VISIBLE
            var page= sp!!.getInt("page",1)
            page++
            edit.putInt("page",page)
            edit.apply()
            observeLiveData()
            viewModel.refreshData(page.toString())

        }
        binding.backButtonn.setOnClickListener {

            var page= sp!!.getInt("page",1)
            page--
            if (page==1){
                binding.backButtonn.visibility=View.INVISIBLE
            }
            edit.putInt("page",page)
            edit.apply()
            observeLiveData()
            viewModel.refreshData(page.toString())

        }

    }

    private fun observeLiveData(){

        viewModel.movies.observe(viewLifecycleOwner){

            it?.let {
                adapter.updateMovieList(it)
            }
        }
    }
//    fun search(searchWords:String){
//
//            val searchMovieList: ArrayList<Movie> = ArrayList()
//            viewModel.movies.value?.forEach {  movie->
//                if (movie.title.contains(searchWords)){
//                    searchMovieList.add(movie)
//                }
//            }
//            adapter.updateMovieList(searchMovieList)
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(movie: Movie) {

        viewModel.updateMovie(movie)
    }

}