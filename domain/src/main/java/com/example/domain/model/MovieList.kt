package com.example.domain.model

import com.google.gson.annotations.SerializedName

data class MovieList(
    @SerializedName("Search") val movies: List<Movie>
)
