package com.padcmyanmar.mewz.mymovieapp.viewholders

import android.util.Log
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padcmyanmar.mewz.mymovieapp.R
import com.padcmyanmar.mewz.mymovieapp.Utils.IMAGE_BASE_URL
import com.padcmyanmar.mewz.mymovieapp.data.vos.MovieVO
import com.padcmyanmar.mewz.mymovieapp.delegate.BannerViewHolderDelegate


class BannerViewHolder(itemView: View, private val mDelegate: BannerViewHolderDelegate) :
    RecyclerView.ViewHolder(itemView) {

    private var mMovieVO: MovieVO? = null

    init {
        itemView.setOnClickListener {
            mMovieVO?.let {
                mDelegate.onTapMovieFormBanner()
            }
        }
    }


    fun bindData(movie: MovieVO) {
        Log.d("BannerViewHolder", "Movie.bannerImage: $${IMAGE_BASE_URL}${movie.posterPath}")
        Log.d("BannerViewHolder", "Movie.bannerMovieName: ${movie.title}")
        mMovieVO = movie
        Glide.with(itemView.context)
            .load("${IMAGE_BASE_URL}${movie.posterPath}")
            .into(itemView.findViewById(R.id.ivBannerImage))

        itemView.findViewById<AppCompatTextView>(R.id.tvBannerMovieName).text = movie.title
    }
}