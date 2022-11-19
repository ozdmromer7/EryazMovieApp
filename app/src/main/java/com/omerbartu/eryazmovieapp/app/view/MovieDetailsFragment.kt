package com.omerbartu.eryazmovieapp.app.view

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.youtube.player.YouTubeStandalonePlayer
import com.omerbartu.eryazmovieapp.app.util.Constant.IMAGE_BASE_URL
import com.omerbartu.eryazmovieapp.app.util.Constant.YOUTUBE_API_KEY
import com.omerbartu.eryazmovieapp.app.viewmodel.MovieViewModel
import com.omerbartu.eryazmovieapp.databinding.FragmentMovieDetailsBinding


class MovieDetailsFragment : Fragment() {

    private var _binding: FragmentMovieDetailsBinding?= null

    private val binding get() = _binding!!
    private lateinit var viewModel: MovieViewModel

    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel= ViewModelProvider(this)[MovieViewModel::class.java]


        val direction =args.direction

        val movie = args.movie

        if (movie != null) {
            viewModel.getVideo(movie.id)
        }

        if (movie != null) {
            Glide.with(requireContext()).load(IMAGE_BASE_URL+movie.posterPath).into(binding.imageViewDetails)
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

        viewModel.videos.observe(viewLifecycleOwner) {

            it?.let { list ->

                binding.constraint.setOnClickListener {

                    val intent = YouTubeStandalonePlayer.createVideoIntent(
                        requireContext() as Activity?,
                        YOUTUBE_API_KEY,
                        list[0].key
                    )
                    startActivity(intent)
                }
                binding.imageView3.setOnClickListener {

                    val intent = YouTubeStandalonePlayer.createVideoIntent(
                        requireContext() as Activity?,
                        YOUTUBE_API_KEY,
                        list[0].key
                    )
                    startActivity(intent)

                }

            }
        }


    }

}