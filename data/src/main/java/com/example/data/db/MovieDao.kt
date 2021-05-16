package com.example.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovieEntity(movieEntity: MovieEntity)

    @Query("SELECT * FROM movie")
    suspend fun getAllMovieEntity(): List<MovieEntity>
}