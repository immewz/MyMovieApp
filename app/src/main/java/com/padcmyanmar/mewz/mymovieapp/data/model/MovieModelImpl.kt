package com.padcmyanmar.mewz.mymovieapp.data.model

import android.content.Context
import com.padcmyanmar.mewz.mymovieapp.data.vos.*
import com.padcmyanmar.mewz.mymovieapp.network.dataagents.MovieDataAgent
import com.padcmyanmar.mewz.mymovieapp.network.dataagents.RetrofitDataAgentImpl
import com.padcmyanmar.mewz.mymovieapp.persistance.MovieDatabase

object MovieModelImpl: MovieModel {

    private val mMovieDataAgent: MovieDataAgent = RetrofitDataAgentImpl

    // Database
    private var mMovieDatabase: MovieDatabase? = null

    fun  initDatabase(context: Context){
        mMovieDatabase = MovieDatabase.getDBInstance(context)
    }

    override fun getNowPlayingMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        // Database
        onSuccess(mMovieDatabase?.movieDao()?.getMoviesByType(type = NOW_PLAYING) ?: listOf())

        // Network
        mMovieDataAgent.getNowPlayingMovies(
            onSuccess = {
                        it.forEach { movie -> movie.type = NOW_PLAYING }
                mMovieDatabase?.movieDao()?.insertMovies(it)
                onSuccess(it)
        }, onFailure = onFailure)
    }

    override fun getPopularMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        // Database
        onSuccess(mMovieDatabase?.movieDao()?.getMoviesByType(type = POPULAR) ?: listOf())

        // Network
        mMovieDataAgent.getPopularMovies(
            onSuccess = {
                        it.forEach { movie -> movie.type = POPULAR }
                mMovieDatabase?.movieDao()?.insertMovies(it)
                onSuccess(it)
            }, onFailure = onFailure)
    }

    override fun getTopRatedMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        // Database
        onSuccess(mMovieDatabase?.movieDao()?.getMoviesByType(type = TOP_RATED) ?: listOf())

        // Network
        mMovieDataAgent.getTopRatedMovies(
            onSuccess = {
                        it.forEach { movie -> movie.type = TOP_RATED }
                mMovieDatabase?.movieDao()?.insertMovies(it)
                onSuccess(it)
            }, onFailure = onFailure)
    }

    override fun getGenres(onSuccess: (List<GenreVO>) -> Unit, onFailure: (String) -> Unit) {
        mMovieDataAgent.getGenres(onSuccess = onSuccess, onFailure = onFailure)
    }

    override fun getMoviesByGenre(
        genreId: String,
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieDataAgent.getMoviesByGenre(
            genreId = genreId,
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }

    override fun getActors(onSuccess: (List<ActorVO>) -> Unit, onFailure: (String) -> Unit) {
        mMovieDataAgent.getActors(onSuccess = onSuccess, onFailure = onFailure)
    }

    override fun getMovieDetails(
        movieId: String,
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        // Database
        val movieFromDatabase = mMovieDatabase?.movieDao()?.getMovieById(movieId = movieId.toInt())
        movieFromDatabase?.let{
            onSuccess(it)
        }

        mMovieDataAgent.getMovieDetails(
            movieId = movieId,
            onSuccess = {
                val movieFromDB =
                    mMovieDatabase?.movieDao()?.getMovieById(movieId = movieId.toInt())
                it.type = movieFromDB?.type
                mMovieDatabase?.movieDao()?.insertSingleMovie(it)
                onSuccess(it)
            },
            onFailure = onFailure
        )
    }

    override fun getCreditsByMovie(
        movieId: String,
        onSuccess: (Pair<List<ActorVO>, List<ActorVO>>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieDataAgent.getCreditsByMovie(
            movieId = movieId,
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }
}