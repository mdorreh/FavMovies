package com.example.favmovies.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Movie
import com.example.domain.usecase.GetMovies
import com.example.domain.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getMovies: GetMovies) : ViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    val movies = MutableLiveData<List<Movie>>()

    fun getMovies() {
        coroutineScope.launch {
            val result = getMovies.invoke()
            if (result is Result.Success) {
                result.a.also { movies.postValue(it) }
            } else {
                throw RuntimeException("fetch failed")
            }
        }
    }
}