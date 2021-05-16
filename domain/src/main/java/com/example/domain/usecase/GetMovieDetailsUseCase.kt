package com.example.domain.usecase

import com.example.domain.repository.MovieRepository

class GetMovieDetailsUseCase(private val movieRepository: MovieRepository) {
    suspend  operator fun invoke(imdbId:String?) = movieRepository.getMovieDetails(imdbId)
}
