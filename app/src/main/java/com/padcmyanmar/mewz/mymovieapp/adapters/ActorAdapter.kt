package com.padcmyanmar.mewz.mymovieapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.mewz.mymovieapp.R
import com.padcmyanmar.mewz.mymovieapp.data.vos.MovieVO
import com.padcmyanmar.mewz.mymovieapp.viewholders.ActorViewHolder

class ActorAdapter: RecyclerView.Adapter<ActorViewHolder>() {
//    private var mMovieList : List<> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_actor_holder,parent,false)
        return ActorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.bindData()
    }

    override fun getItemCount(): Int {
        return 10
    }
}