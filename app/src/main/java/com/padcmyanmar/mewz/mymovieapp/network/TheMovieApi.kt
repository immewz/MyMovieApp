package com.padcmyanmar.mewz.mymovieapp.network

import com.padcmyanmar.mewz.mymovieapp.Utils.*
import com.padcmyanmar.mewz.mymovieapp.network.responses.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieApi {

    @GET(API_GET_NOW_PLAYING)
    fun getNowPlayingMovies(
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1,
    ) : retrofit2.Call<MovieListResponse>

    @GET(API_GET_POPULAR_MOVIES)
    fun getPopularMovies(
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1,
    ) : retrofit2.Call<MovieListResponse>

    @GET(API_GET_NOW_PLAYING)
    fun getTopRatedMovies(
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1,
    ) : retrofit2.Call<MovieListResponse>

}