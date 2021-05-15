package com.example.data.network

import com.example.domain.model.Movie
import com.example.domain.repository.MovieDataSource
import com.example.domain.util.Result
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OmdbService @Inject constructor(private val omdbApi: OmdbApi) : MovieDataSource {

    override suspend fun getMovies(): Result<List<Movie>, Unit> {
        return withContext(Dispatchers.IO) {
            try {
                val response = omdbApi.getMovies()
                if (response.isSuccessful && response.body() != null) {
                    return@withContext Result.Success(response.body()!!.movies)
                } else {
                    return@withContext Result.Failure(Unit)
                }
            } catch (t: Throwable) {
                if (t !is CancellationException) {
                    return@withContext Result.Failure(Unit)
                } else {
                    throw t
                }
            }
        }
    }
}