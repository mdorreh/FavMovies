package com.example.favmovies.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.Movie
import com.example.domain.usecase.GetMovieByTitleUseCase
import com.example.domain.usecase.GetMovies
import com.example.domain.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val getMovieByTitleUseCase: GetMovieByTitleUseCase) : ViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    val movie = MutableLiveData<Movie>()

    fun getMovieByTitle(title:String?,year:String?) {
        coroutineScope.launch {
            val result = getMovieByTitleUseCase.invoke(title,year)
            if (result is Result.Success) {
                result.a.also { movie.postValue(it) }
            } else {
                throw RuntimeException("fetch failed")
            }
        }
    }
}