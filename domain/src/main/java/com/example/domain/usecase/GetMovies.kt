package com.example.domain.usecase

import com.example.domain.repository.MovieRepository

class GetMovies(private val movieRepository: MovieRepository) {
    suspend  operator fun invoke() = movieRepository.getMovies()
}

