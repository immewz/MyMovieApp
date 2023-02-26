package com.padcmyanmar.mewz.mymovieapp.data.model

import com.padcmyanmar.mewz.mymovieapp.data.vos.MovieVO
import com.padcmyanmar.mewz.mymovieapp.network.dataagents.MovieDataAgent
import com.padcmyanmar.mewz.mymovieapp.network.dataagents.RetrofitDataAgentImpl

object MovieModelImpl: MovieModel {

    private val mMovieDataAgent: MovieDataAgent = RetrofitDataAgentImpl

    override fun getNowPlayingMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieDataAgent.getNowPlayingMovies(onSuccess = onSuccess, onFailure = onFailure)
    }

    override fun getPopularMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieDataAgent.getNowPlayingMovies(onSuccess = onSuccess, onFailure = onFailure)
    }

    override fun getTopRatedMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieDataAgent.getNowPlayingMovies(onSuccess = onSuccess, onFailure = onFailure)
    }
}