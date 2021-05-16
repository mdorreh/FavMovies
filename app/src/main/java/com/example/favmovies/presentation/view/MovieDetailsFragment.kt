package com.example.favmovies.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.domain.model.Movie
import com.example.favmovies.databinding.FragmentMovieDetailsBinding
import com.example.favmovies.presentation.viewmodel.MovieDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private val movieDetailsViewModel: MovieDetailsViewModel by viewModels()
    private var _binding: FragmentMovieDetailsBinding? = null
    private lateinit var imdbId: String
    private lateinit var favMovie : Movie

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: MovieDetailsFragmentArgs by navArgs()

        imdbId = args.imdbId

        movieDetailsViewModel.saved.observe(viewLifecycleOwner,{
            if(it)
                Toast.makeText(activity,"your fav movie saved",Toast.LENGTH_SHORT)
        })

        movieDetailsViewModel.movieDetails.observe(viewLifecycleOwner, { movie ->
            _binding?.let {
                Glide.with(this@MovieDetailsFragment)
                    .load(movie.posterUrl)
                    .into(it.moviePoster)
                it.movieTitle.text = movie.title
                it.movieYear.text = movie.year
                it.movieActors.text = movie.actors
                it.movieAward.text = movie.awards
                it.movieBoxoffice.text = movie.boxOffice
                it.movieDirector.text = movie.director
                it.movieRated.text = movie.rated
                it.movieRuntime.text = movie.runTime
                it.movieImdbrating.text = movie.imdbRating
                it.movieProduction.text = movie.production
                it.movieGenre.text = movie.genre
                favMovie=movie
            }
        })
        _binding?.addToFav?.setOnClickListener {
            movieDetailsViewModel.saveMovie(favMovie)
        }
    }

    override fun onResume() {
        super.onResume()
        movieDetailsViewModel.getMovieDetails(imdbId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}