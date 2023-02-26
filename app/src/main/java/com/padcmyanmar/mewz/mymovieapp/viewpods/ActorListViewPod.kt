package com.padcmyanmar.mewz.mymovieapp.viewpods

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.padcmyanmar.mewz.mymovieapp.adapters.ActorAdapter
import com.padcmyanmar.mewz.mymovieapp.data.vos.MovieVO
import com.padcmyanmar.mewz.mymovieapp.databinding.ViewPodActorListBinding

class ActorListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {

    private lateinit var binding: ViewPodActorListBinding
    lateinit var mActorAdapter: ActorAdapter

    override fun onFinishInflate() {
//        setUpActorRecyclerView()
        super.onFinishInflate()
    }

//    fun setData(movieList: List<MovieVO>){
//        mActorAdapter
//    }

    fun setUpActorViewPod(backgroundColorReference: Int, titleText: String, moreTitleText: String){
        setBackgroundColor(ContextCompat.getColor(context,backgroundColorReference))
        binding.tvBestActor.text = titleText
        binding.tvMoreActors.text = moreTitleText
        binding.tvMoreActors.paintFlags = Paint.UNDERLINE_TEXT_FLAG
    }

    private fun setUpActorRecyclerView() {
        mActorAdapter = ActorAdapter()
        binding.rvBestActor.adapter = mActorAdapter
        binding.rvBestActor.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)
    }
}