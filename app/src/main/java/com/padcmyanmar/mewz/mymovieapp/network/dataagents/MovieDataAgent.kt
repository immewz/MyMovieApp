package com.padcmyanmar.mewz.mymovieapp.network.dataagents

import com.padcmyanmar.mewz.mymovieapp.data.vos.MovieVO

interface MovieDataAgent {

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