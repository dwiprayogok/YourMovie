package com.example.yourmovie.Interface

import com.example.yourmovie.Model.Movie

interface OnGetMoviesCallback {
    fun onSuccess(movies: List<Movie?>?)

    fun onError()
}