package com.example.favmovies.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.Movie
import com.example.domain.usecase.AddMovieUseCase
import com.example.domain.usecase.GetMovieDetailsUseCase
import com.example.domain.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val addMovieUseCase: AddMovieUseCase
) :
    ViewModel() {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    val movieDetails = MutableLiveData<Movie>()
    val saved = MutableLiveData<Boolean>()

    fun saveMovie(movie: Movie) {
        coroutineScope.launch {
            addMovieUseCase.invoke(movie)
            saved.postValue(true)
        }
    }

    fun getMovieDetails(imdbId: String?) {
        coroutineScope.launch {
            val result = getMovieDetailsUseCase.invoke(imdbId)
            if (result is Result.Success) {
                result.a.also { movieDetails.postValue(it) }
            } else {
                throw RuntimeException("fetch failed")
            }
        }
    }
}