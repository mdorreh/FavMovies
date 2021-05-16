package com.example.domain.usecase

import com.example.domain.repository.MovieRepository

class GetMovieByTitleUseCase(private val movieRepository: MovieRepository) {
    suspend  operator fun invoke(title:String?,year:String?) = movieRepository.getMovieByTitle(title,year)
}