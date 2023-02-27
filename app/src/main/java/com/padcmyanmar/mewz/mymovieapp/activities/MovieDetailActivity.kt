package com.padcmyanmar.mewz.mymovieapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.padcmyanmar.mewz.mymovieapp.databinding.ActivityMovieDetailBinding
import com.padcmyanmar.mewz.mymovieapp.databinding.ViewPodActorListBinding

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding

    companion object{
        fun newIntent(context: Context, movieId: Int) : Intent{
            return  Intent(context,MovieDetailActivity::class.java)
        }
    }

    lateinit var actorsViewPod: ViewPodActorListBinding
    lateinit var creatorsViewPod: ViewPodActorListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViewPod()
        setUpListeners()
    }

    private fun setUpListeners() {
        binding.btnBack.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun setUpViewPod() {
        actorsViewPod = binding.vpActors
//        actorsViewPod.setUpActorViewPod(
//            backgroundColorReference = R.color.colorPrimary,
//            titleText = getString(R.string.lbl_actors),
//            moreTitleText = ""
//        )

        creatorsViewPod = binding.vpCreators
//        creatorsViewPod.setUpActorViewPod(
//            backgroundColorReference = R.color.colorPrimary,
//            titleText = getString(R.string.lbl_creators),
//            moreTitleText = getString(R.string.lbl_more_creator)
//        )
    }
}