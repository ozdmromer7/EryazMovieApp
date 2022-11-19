package com.omerbartu.eryazmovieapp.app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.omerbartu.eryazmovieapp.app.datamodel.Movie
import com.omerbartu.eryazmovieapp.app.util.Constant.IMAGE_BASE_URL
import com.omerbartu.eryazmovieapp.databinding.RecyclerRowBinding

class AllMovieAdapter(private var movieList:ArrayList<Movie>,private val listener:Listener, private var context:Context,val onItemClick: (Movie) -> Unit):RecyclerView.Adapter<AllMovieAdapter.MovieAdapter>() {

    class MovieAdapter(val binding: RecyclerRowBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter {

        val binding=RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieAdapter(binding)
    }

    override fun onBindViewHolder(holder: MovieAdapter, position: Int) {

        val currentMovie= movieList[position]

        holder.binding.movieName.text= currentMovie.title.uppercase()
        Glide.with(context).load(IMAGE_BASE_URL+currentMovie.posterPath)
            .into(holder.binding.imageView)
        holder.binding.imageView.setOnClickListener {
            onItemClick(currentMovie)
        }

        if (currentMovie.isChecked==1){
            holder.binding.checkBox.isChecked=true
        }

        holder.binding.checkBox.setOnClickListener {
            if (holder.binding.checkBox.isChecked){
                currentMovie.isChecked=1
                Toast.makeText(context,"${currentMovie.title} Favorilere Eklendi",Toast.LENGTH_SHORT).show()
            }
            else{
                currentMovie.isChecked=0
                Toast.makeText(context,"${currentMovie.title} Favorilerden Çıkartıldı",Toast.LENGTH_SHORT).show()

            }
            listener.onItemClick(currentMovie)
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

    interface Listener{

        fun onItemClick(movie: Movie)

    }

}