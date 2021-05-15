package com.example.domain.util

import com.example.domain.util.Result as Result

sealed class Result<out T, out L> {

    data class Failure<out L>(val b: L) : Result<Nothing, L>()
    data class Success<out T>(val a: T) : Result<T, Nothing>()
}
