package com.example.favmovies.di

import android.content.Context
import com.example.data.network.OmdbApi
import com.example.data.network.OmdbService
import com.example.domain.repository.MovieRepository
import com.example.domain.usecase.*
import com.example.favmovies.presentation.viewmodel.HomeViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideGetMovieDetailsUseCase(movieRepository: MovieRepository) = GetMovieDetailsUseCase(
        movieRepository
    )

    @Provides
    fun provideGetMovieByTitleUseCase(movieRepository: MovieRepository) = GetMovieByTitleUseCase(
        movieRepository
    )

    @Provides
    fun provideAddMovieUseCase(movieRepository: MovieRepository) = AddMovieUseCase(movieRepository)

    @Provides
    fun provideGetAllMovies(movieRepository: MovieRepository) = GetAllMoviesUseCase(movieRepository)

    @Provides
    fun provideRepository(omdbApi: OmdbApi, @ApplicationContext appContext: Context) =
        MovieRepository(OmdbService(omdbApi, appContext))

}
