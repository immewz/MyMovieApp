package com.padcmyanmar.mewz.mymovieapp.data.model

import com.padcmyanmar.mewz.mymovieapp.data.vos.MovieVO

interface MovieModel {

    fun getNowPlayingMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getPopularMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getTopRatedMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    )
}