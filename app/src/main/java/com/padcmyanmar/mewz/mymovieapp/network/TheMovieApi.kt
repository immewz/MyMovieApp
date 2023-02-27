package com.padcmyanmar.mewz.mymovieapp.network

import android.telecom.Call
import com.padcmyanmar.mewz.mymovieapp.network.responses.GetActorsResponse
import com.padcmyanmar.mewz.mymovieapp.network.responses.GetGenreResponse
import com.padcmyanmar.mewz.mymovieapp.utils.*
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

    @GET(API_GET_TOP_RATED_MOVIES)
    fun getTopRatedMovies(
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1,
    ) : retrofit2.Call<MovieListResponse>

    @GET(API_GET_GENRES)
    fun getGenres(
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
    ) : retrofit2.Call<GetGenreResponse>

    @GET(API_GET_MOVIES_BY_GENRES)
    fun getMoviesByGenre(
        @Query(PARAM_GENRE_ID) genreId : String,
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
    ) : retrofit2.Call<MovieListResponse>

    @GET(API_GET_ACTORS)
    fun getActors(
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1
    ) : retrofit2.Call<GetActorsResponse>

}