package com.example.domain.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("Title") val title: String,
    @SerializedName("imdbID") val imdbId: String="",
    @SerializedName("Year") val year: String="",
    @SerializedName("Poster") val posterUrl: String,
    @SerializedName("Rated") val rated: String ="",
    @SerializedName("Runtime") val runTime: String="",
    @SerializedName("Genre") val genre: String="",
    @SerializedName("Director") val director: String="",
    @SerializedName("Actors") val actors: String="",
    @SerializedName("Plot") val plot: String="",
    @SerializedName("Awards") val awards: String="",
    @SerializedName("imdbRating") val imdbRating: String="",
    @SerializedName("BoxOffice") val boxOffice: String="",
    @SerializedName("Production") val production: String=""
)
