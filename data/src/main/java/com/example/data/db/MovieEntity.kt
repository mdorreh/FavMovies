package com.example.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.Movie

@Entity(tableName = "movie")
data class MovieEntity(
    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "posterurl")
    val posterUrl: String,

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L
) {
    companion object {
        fun fromMovie(movie: Movie) = MovieEntity(movie.title, movie.posterUrl)
    }
    fun toMovie() = Movie(title=title,posterUrl = posterUrl)
}

