package com.example.domain.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("Title") val title: String,
    @SerializedName("imdbID") val imdbId: String,
    @SerializedName("Year") val year: String,
    @SerializedName("Poster") val posterUrl: String
)
