package com.padcmyanmar.mewz.mymovieapp.viewholders

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padcmyanmar.mewz.mymovieapp.R
import com.padcmyanmar.mewz.mymovieapp.Utils.IMAGE_BASE_URL
import com.padcmyanmar.mewz.mymovieapp.data.vos.MovieVO
import com.padcmyanmar.mewz.mymovieapp.databinding.ViewHolderMovieBinding
import com.padcmyanmar.mewz.mymovieapp.databinding.ViewHolderShowcaseBinding
import com.padcmyanmar.mewz.mymovieapp.delegate.ShowcaseViewHolderDelegate

class ShowCaseViewHolder(itemView: View,mDelegate: ShowcaseViewHolderDelegate) : RecyclerView.ViewHolder(itemView) {

//    private lateinit var binding: ViewHolderShowcaseBinding
    private var mMovieVO: MovieVO? = null

    init {
        itemView.setOnClickListener {
            mMovieVO?.let {
                mDelegate.onTapMovieFromShowcase()
            }
        }
    }

    fun bindData(movie: MovieVO){
        mMovieVO = movie
        Glide.with(itemView.context)
            .load("${IMAGE_BASE_URL}${movie.posterPath}")
            .into(itemView.findViewById(R.id.ivShowCase))

        itemView.findViewById<AppCompatTextView>(R.id.tvShowCaseMovieName).text = movie.title
        itemView.findViewById<AppCompatTextView>(R.id.tvShowCaseMovieDate).text = movie.releaseDate

    }
}