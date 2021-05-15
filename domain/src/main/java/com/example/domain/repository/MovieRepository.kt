package com.example.domain.repository

class MovieRepository(private val dataSource: MovieDataSource) {
    suspend fun getMovies()=dataSource.getMovies()
}