package com.example.domain.repository

import com.example.domain.model.Movie

class MovieRepository(private val dataSource: MovieDataSource) {
    suspend fun getMovies() = dataSource.getMovies()
    suspend fun getMovieDetails(imdbId: String?) = dataSource.getMovieDetails(imdbId)
    suspend fun getMovieByTitle(title:String?,year:String?) = dataSource.getMovieByTitle(title,year)
    suspend fun addMovie(movie: Movie)= dataSource.addMovie(movie)
    suspend fun getAllMovies() = dataSource.getAllMovies()
}