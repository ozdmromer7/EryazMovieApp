package com.omerbartu.eryazmovieapp.app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.omerbartu.eryazmovieapp.app.datamodel.Movie
import com.omerbartu.eryazmovieapp.app.util.Constant
import com.omerbartu.eryazmovieapp.databinding.RecyclerRowBinding


class NowPlayAdapter(val movieList:ArrayList<Movie>,val context:Context,val onItemClick: (Movie) -> Unit):RecyclerView.Adapter<NowPlayAdapter.NowPlayHolder>() {
    class NowPlayHolder(val binding: RecyclerRowBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayHolder {
        val binding=RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NowPlayHolder(binding)
    }

    override fun onBindViewHolder(holder: NowPlayHolder, position: Int) {


        holder.binding.movieName.text= movieList.get(position).title.uppercase()
        Glide.with(context).load(Constant.IMAGE_BASE_URL+movieList.get(position).posterPath)
            .into(holder.binding.imageView)
        holder.binding.imageView.setOnClickListener {
            onItemClick(movieList[position])
        }
    }

    override fun getItemCount(): Int {
       return movieList.count()
    }
    fun updateMovieList(list:List<Movie>){

        movieList.clear()
        movieList.addAll(list)
        notifyDataSetChanged()

    }
}