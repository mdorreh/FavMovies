package com.example.data.network

import com.example.domain.model.Movie
import com.example.domain.model.MovieList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface OmdbApi {

    @GET("?apikey=" + "51808dc9" + "&s=friend&page=1&year=2021")
    suspend fun getMovies(): Response<MovieList>

    @GET("?apikey=" + "51808dc9")
    suspend fun getMovieDetails(@Query("i") imdbId:String?):Response<Movie>

    @GET("?apikey=" + "51808dc9")
    suspend fun getMovieByTitle(@Query("t") title:String?,@Query("y") year:String?):Response<Movie>
}