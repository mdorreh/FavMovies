package com.example.data.network

import android.content.Context
import com.example.data.db.DatabaseService
import com.example.data.db.MovieEntity
import com.example.domain.model.Movie
import com.example.domain.repository.MovieDataSource
import com.example.domain.util.Result
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OmdbService @Inject constructor(private val omdbApi: OmdbApi, context: Context) : MovieDataSource {

    private val movieDao = DatabaseService.getInstance(context).movieDao()

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

    override suspend fun getMovieDetails(imdbId: String?): Result<Movie, Unit> {
        return withContext(Dispatchers.IO) {
            try {
                val response = omdbApi.getMovieDetails(imdbId)

                if (response.isSuccessful && response.body() != null) {
                    return@withContext Result.Success(response.body()!!)
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

    override suspend fun getMovieByTitle(title: String?, year: String?): Result<Movie, Unit> {
        return withContext(Dispatchers.IO) {
            try {
                val response = omdbApi.getMovieByTitle(title, year)
                if (response.isSuccessful && response.body() != null) {
                    return@withContext Result.Success(response.body()!!)
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

    override suspend fun addMovie(movie: Movie) = movieDao.addMovieEntity(MovieEntity.fromMovie(movie))

    override suspend fun getAllMovies(): List<Movie> = movieDao.getAllMovieEntity().map {
        it.toMovie()
    }
}