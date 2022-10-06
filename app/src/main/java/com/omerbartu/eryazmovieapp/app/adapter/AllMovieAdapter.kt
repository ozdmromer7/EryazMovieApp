package com.omerbartu.eryazmovieapp.app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.omerbartu.eryazmovieapp.app.datamodel.Movie
import com.omerbartu.eryazmovieapp.app.view.MovieFragmentDirections
import com.omerbartu.eryazmovieapp.databinding.RecyclerRowBinding

class AllMovieAdapter(var movieList:ArrayList<Movie>, var context:Context):RecyclerView.Adapter<AllMovieAdapter.MovieAdapter>() {

    class MovieAdapter(val binding: RecyclerRowBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter {

        val binding=RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieAdapter(binding)
    }

    override fun onBindViewHolder(holder: MovieAdapter, position: Int) {

        val BASE_URL="https://image.tmdb.org/t/p/w92"
        println(movieList.get(position).posterPath)
        holder.binding.movieName.text= movieList.get(position).title
        Glide.with(context).load(BASE_URL+movieList.get(position).posterPath)
            .into(holder.binding.imageView)
        holder.binding.imageView.setOnClickListener {
            val action=MovieFragmentDirections.actionMovieFragmentToMovieDetailsFragment()
            Navigation.findNavController(it).navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return movieList.size

    }

    fun updateMovieList(list:List<Movie>){

        movieList.clear()
        movieList.addAll(list)
        notifyDataSetChanged()


    }

}