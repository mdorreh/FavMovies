package com.example.data.network

import com.example.domain.model.MovieList
import retrofit2.Response
import retrofit2.http.GET

interface OmdbApi {

    @GET("?apikey=" + "51808dc9" + "&s=friend&page=1&year=2021")
    suspend fun getMovies(): Response<MovieList>
}