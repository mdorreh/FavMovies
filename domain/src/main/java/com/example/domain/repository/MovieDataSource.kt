package com.example.domain.repository

import com.example.domain.model.Movie
import com.example.domain.util.Result

interface MovieDataSource {
    suspend fun getMovies(): Result<List<Movie>, Unit>
    suspend fun getMovieDetails(imdbId: String?): Result<Movie, Unit>
    suspend fun getMovieByTitle(title: String?="", year: String?=""): Result<Movie, Unit>
    suspend fun addMovie(movie: Movie)
    suspend fun getAllMovies(): List<Movie>

}