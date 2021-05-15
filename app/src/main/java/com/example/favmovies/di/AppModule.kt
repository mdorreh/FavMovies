package com.example.favmovies.di

import com.example.data.network.OmdbApi
import com.example.data.network.OmdbService
import com.example.domain.repository.MovieRepository
import com.example.domain.usecase.GetMovies
import com.example.favmovies.presentation.viewmodel.HomeViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideMainViewModel(getMoviesUseCase: GetMovies) =
        HomeViewModel(getMoviesUseCase)

    @Provides
    fun provideGetMoviesUseCase(movieRepository: MovieRepository) = GetMovies(
        movieRepository
    )

    @Provides
    fun provideRepository(omdbApi: OmdbApi)= MovieRepository(OmdbService(omdbApi))

}
