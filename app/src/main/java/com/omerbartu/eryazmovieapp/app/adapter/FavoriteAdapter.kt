package com.omerbartu.eryazmovieapp.app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.omerbartu.eryazmovieapp.app.datamodel.Movie
import com.omerbartu.eryazmovieapp.app.util.Constant
import com.omerbartu.eryazmovieapp.databinding.RecyclerFavBinding

class FavoriteAdapter(val list: ArrayList<Movie>,val context: Context):RecyclerView.Adapter<FavoriteAdapter.FavHolder>() {
    class FavHolder(val binding:RecyclerFavBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavHolder {
        val binding =RecyclerFavBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FavHolder(binding)
    }

    override fun onBindViewHolder(holder: FavHolder, position: Int) {
        holder.binding.textFav.text=list.get(position).title
        Glide.with(context).load(Constant.IMAGE_BASE_URL + list.get(position).posterPath)
            .into(holder.binding.favImage)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateMovieList(list1:List<Movie>){

        list.clear()
        list.addAll(list1)
        notifyDataSetChanged()

    }
}