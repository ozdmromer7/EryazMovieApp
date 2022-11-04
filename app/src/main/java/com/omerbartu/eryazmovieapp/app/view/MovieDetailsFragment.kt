package com.omerbartu.eryazmovieapp.app.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.omerbartu.eryazmovieapp.app.util.Constant
import com.omerbartu.eryazmovieapp.databinding.FragmentMovieDetailsBinding


class MovieDetailsFragment : Fragment() {

    private var _binding: FragmentMovieDetailsBinding?= null

    private val binding get() = _binding!!

    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val direction =args.direction

        val movie = args.movie

        if (movie != null) {
            Glide.with(requireContext()).load(Constant.IMAGE_BASE_URL+movie.posterPath).into(binding.imageViewDetails)
        }
        if (movie != null) {
            binding.movieName.text= movie.title
        }
        if (movie != null) {
            binding.overviewText.text=movie.overview
        }
        if (movie != null) {
            binding.voteText.text=movie.voteAverage.toString()+"/10"+" Vote: " +"${movie.voteCount}"
        }
        if (movie != null) {

            binding.ratingBar.rating=movie.voteAverage.toFloat()/2
            binding.ratingBar.isEnabled=false

        }
        binding.backButton.setOnClickListener {

            when(direction){
                "1" -> findNavController().navigate(MovieDetailsFragmentDirections.actionMovieDetailsFragmentToMovieFragment())
                "2" -> findNavController().navigate(MovieDetailsFragmentDirections.actionMovieDetailsFragmentToTopRatedFragment())
                "3" -> findNavController().navigate(MovieDetailsFragmentDirections.actionMovieDetailsFragmentToNowPlayFragment2())
            }
        }

    }



}