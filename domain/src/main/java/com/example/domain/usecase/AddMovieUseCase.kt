package com.example.domain.usecase

import com.example.domain.model.Movie
import com.example.domain.repository.MovieRepository

class AddMovieUseCase(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(movie: Movie) = movieRepository.addMovie(movie)
}
