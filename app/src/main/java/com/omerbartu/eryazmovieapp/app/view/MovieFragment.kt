package com.omerbartu.eryazmovieapp.app.view

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.omerbartu.eryazmovieapp.R
import com.omerbartu.eryazmovieapp.app.adapter.AllMovieAdapter
import com.omerbartu.eryazmovieapp.app.datamodel.Movie
import com.omerbartu.eryazmovieapp.app.viewmodel.MovieViewModel
import com.omerbartu.eryazmovieapp.databinding.FragmentMovieBinding

class MovieFragment : Fragment(),SearchView.OnQueryTextListener{

    private var _binding: FragmentMovieBinding? = null

    private val binding get() = _binding!!

    private lateinit var adapter: AllMovieAdapter
    private lateinit var viewModel: MovieViewModel
    private var movieList= arrayListOf<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //(activity as AppCompatActivity?)!!.setSupportActionBar(binding.moviesToolbar as Toolbar?)

        viewModel=ViewModelProvider(this).get(MovieViewModel::class.java)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        val view = binding.root
        setHasOptionsMenu(true)
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel=ViewModelProvider(this).get(MovieViewModel::class.java)


        viewModel.refreshData()

        observeLiveData()

        binding.allMovieRecycler.layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)

        adapter= AllMovieAdapter(movieList,requireContext(),onItemClick={
            val action=MovieFragmentDirections.actionMovieFragmentToMovieDetailsFragment()
            action.movie=it
            action.direction="1"
            Navigation.findNavController(view).navigate(action)
        })

        binding.allMovieRecycler.adapter=adapter

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        activity?.menuInflater?.inflate(R.menu.search_menu,menu)
        val item=menu.findItem(R.id.search)
        val searchView=item.actionView as SearchView
        searchView.setOnQueryTextListener(this@MovieFragment)

        super.onCreateOptionsMenu(menu, inflater)
    }
    fun observeLiveData(){

        viewModel.movies.observe(viewLifecycleOwner, Observer {

            it?.let {
                adapter.updateMovieList(it)
            }

        })

    }

    fun search(searchWords:String){

            val searchMovieList: ArrayList<Movie> = ArrayList()
            viewModel.movies.value?.forEach {  movie->
                if (movie.title.contains(searchWords)){
                    searchMovieList.add(movie)
                }
            }
            adapter.updateMovieList(searchMovieList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            search(query)
            Log.e("harg",query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            search(newText)
            Log.e("harf",newText)
        }
        return true
    }
}