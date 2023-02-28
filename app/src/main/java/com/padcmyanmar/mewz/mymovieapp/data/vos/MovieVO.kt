package com.padcmyanmar.mewz.mymovieapp.data.vos

import com.google.gson.annotations.SerializedName

data class MovieVO(

    @SerializedName("adult")
    val adult: Boolean?,

    @SerializedName("backdrop_path")
    val backDropPath: String?,

    @SerializedName("belongs_to_collection")
    val belongsToCollection: List<CollectionVO>?,

    @SerializedName("budget")
    val budget: Double?,

    @SerializedName("genres")
    val genres: List<GenreVO>?,

    @SerializedName("homepage")
    val homepage: String?,

    @SerializedName("id")
    val id: Int?,

    @SerializedName("imdb_id")
    val imdbId: String?,

    @SerializedName("original_language")
    val originalLanguage: String?,

    @SerializedName("original_title")
    val originalTitle: String?,

    @SerializedName("overview")
    val overview: String?,

    @SerializedName("popularity")
    val popularity: Double?,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompaniesVO>?,

    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountriesVO>?,

    @SerializedName("release_date")
    val releaseDate: String?,

    @SerializedName("revenue")
    val revenue: Double?,

    @SerializedName("runtime")
    val runtime: Int?,

    @SerializedName("spoken_languages")
    val spokenLanguage: List<SpokenLanguageVO>?,

    @SerializedName("status")
    val status: String?,

    @SerializedName("tagline")
    val tagline: String?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("video")
    val video: Boolean?,

    @SerializedName("vote_average")
    val voteAverage: Double?,

    @SerializedName("vote_count")
    val voteCount: Int?
){
    fun getRatingBasedOnFiveStars(): Float{
        return voteAverage?.div(2)?.toFloat()?:0.0f
    }

    fun  getGenresAsCommaSeparatedString(): String{
        return genres?.map { it.name }?.joinToString (",") ?: ""
    }

    fun  getProductionCountriesAsCommaSeparatedString(): String{
        return productionCountries?.map { it.name }?.joinToString (",") ?: ""
    }
}


