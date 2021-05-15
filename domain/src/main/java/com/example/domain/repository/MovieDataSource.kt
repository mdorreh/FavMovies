package com.example.domain.repository

import com.example.domain.model.Movie
import com.example.domain.util.Result

interface MovieDataSource {
    suspend fun getMovies():Result<List<Movie>,Unit>
}