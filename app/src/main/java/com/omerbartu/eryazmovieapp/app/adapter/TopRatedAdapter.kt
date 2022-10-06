package com.omerbartu.eryazmovieapp.app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.omerbartu.eryazmovieapp.app.datamodel.Movie
import com.omerbartu.eryazmovieapp.databinding.RecyclerRowBinding

class TopRatedAdapter(val topMovieList:ArrayList<Movie>,val context: Context):RecyclerView.Adapter<TopRatedAdapter.TopRatedHolder>(){
    class TopRatedHolder(val binding: RecyclerRowBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedHolder {
        val binding=RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TopRatedHolder(binding)
    }

    override fun onBindViewHolder(holder: TopRatedHolder, position: Int) {
        val BASE_URL="https://image.tmdb.org/t/p/w92"
        println(topMovieList.get(position).posterPath)
        holder.binding.movieName.text= topMovieList.get(position).title
        Glide.with(context).load(BASE_URL+topMovieList.get(position).posterPath)
            .into(holder.binding.imageView)
    }

    override fun getItemCount(): Int {
        return topMovieList.count()
    }
    fun updateMovieList(movieList:List<Movie>){
        topMovieList.clear()
        topMovieList.addAll(movieList)
        notifyDataSetChanged()
    }
}