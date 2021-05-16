package com.example.domain.repository

class MovieRepository(private val dataSource: MovieDataSource) {
    suspend fun getMovies() = dataSource.getMovies()
    suspend fun getMovieDetails(imdbId: String?) = dataSource.getMovieDetails(imdbId)
    suspend fun getMovieByTitle(title:String?,year:String?) = dataSource.getMovieByTitle(title,year)
}