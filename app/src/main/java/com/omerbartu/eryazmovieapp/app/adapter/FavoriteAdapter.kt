package com.omerbartu.eryazmovieapp.app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.omerbartu.eryazmovieapp.app.datamodel.Movie
import com.omerbartu.eryazmovieapp.app.util.Constant
import com.omerbartu.eryazmovieapp.databinding.RecyclerFavBinding

class FavoriteAdapter(private val favList: ArrayList<Movie>,val context: Context):RecyclerView.Adapter<FavoriteAdapter.FavHolder>() {
    class FavHolder(val binding:RecyclerFavBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavHolder {
        val binding =RecyclerFavBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FavHolder(binding)
    }

    override fun onBindViewHolder(holder: FavHolder, position: Int) {

        holder.binding.textFav.text= favList[position].title
        Glide.with(context).load(Constant.IMAGE_BASE_URL + favList[position].posterPath)
            .into(holder.binding.favImage)
    }

    override fun getItemCount(): Int {
        return favList.size
    }

    fun updateMovieList(list:List<Movie>){

        favList.clear()
        favList.addAll(list)
        notifyDataSetChanged()

    }
}