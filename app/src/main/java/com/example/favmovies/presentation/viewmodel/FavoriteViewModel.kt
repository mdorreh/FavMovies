package com.example.favmovies.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.Movie
import com.example.domain.usecase.GetAllMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val getAllMoviesUseCase: GetAllMoviesUseCase) : ViewModel() {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    val movies = MutableLiveData<List<Movie>>()

    fun getMovies() {
        coroutineScope.launch {
            movies.postValue(getAllMoviesUseCase.invoke())
        }
    }
}