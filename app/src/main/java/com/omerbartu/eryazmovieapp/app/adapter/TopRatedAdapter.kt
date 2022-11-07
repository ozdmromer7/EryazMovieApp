package com.omerbartu.eryazmovieapp.app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.omerbartu.eryazmovieapp.app.datamodel.Movie
import com.omerbartu.eryazmovieapp.app.util.Constant
import com.omerbartu.eryazmovieapp.databinding.RecyclerRowBinding


class TopRatedAdapter(val topMovieList:ArrayList<Movie>,val context: Context, val onItemClick: (Movie) -> Unit):RecyclerView.Adapter<TopRatedAdapter.TopRatedHolder>(){
    class TopRatedHolder(val binding: RecyclerRowBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedHolder {
        val binding= RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TopRatedHolder(binding)
    }

    override fun onBindViewHolder(holder: TopRatedHolder, position: Int) {

        holder.binding.movieName.text= topMovieList.get(position).title.uppercase()
        Glide.with(context).load(Constant.IMAGE_BASE_URL+topMovieList.get(position).posterPath)
            .into(holder.binding.imageView)
        holder.binding.imageView.setOnClickListener {
            onItemClick(topMovieList[position])
        }
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