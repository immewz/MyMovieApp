package com.padcmyanmar.mewz.mymovieapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.padcmyanmar.mewz.mymovieapp.R
import com.padcmyanmar.mewz.mymovieapp.data.model.MovieModel
import com.padcmyanmar.mewz.mymovieapp.data.model.MovieModelImpl
import com.padcmyanmar.mewz.mymovieapp.data.vos.GenreVO
import com.padcmyanmar.mewz.mymovieapp.data.vos.MovieVO
import com.padcmyanmar.mewz.mymovieapp.databinding.ActivityMovieDetailBinding
import com.padcmyanmar.mewz.mymovieapp.databinding.ViewPodActorListBinding
import com.padcmyanmar.mewz.mymovieapp.utils.IMAGE_BASE_URL
import com.padcmyanmar.mewz.mymovieapp.viewpods.ActorListViewPod

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding

    private lateinit var mActorListViewPod: ActorListViewPod
    private lateinit var mCreatorListViewPod: ActorListViewPod

    /// Model
    private val mMovieModel: MovieModel = MovieModelImpl

    companion object{

        private const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
        fun newIntent(context: Context, movieId: Int) : Intent{
            val intent = Intent(context,MovieDetailActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_ID, movieId)
            return intent
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

        val movieId = intent?.getIntExtra(EXTRA_MOVIE_ID,0)
        movieId?.let { 
            requestData(it)
        }
    }

    private fun requestData(movieId: Int) {
        mMovieModel.getMovieDetails(
            movieId = movieId.toString(),
            onSuccess = {
                bindData(it)
            },
            onFailure = {
                showErrorMessage()
            }
        )

        mMovieModel.getCreditsByMovie(
            movieId = movieId.toString(),
            onSuccess = {
                mActorListViewPod.setData(it.first)
                mCreatorListViewPod.setData(it.second)
            },
            onFailure = {
                showErrorMessage()
            }
        )

    }

    private fun bindData(movie: MovieVO) {
        Glide.with(this)
            .load("$IMAGE_BASE_URL${movie.posterPath}")
            .into(binding.ivMovieDetailImage)

        binding.collapsingTitle.title = movie.title ?: ""

        binding.tvMovieDetailName.text = movie.title ?: ""
        binding.tvMovieReleaseYear.text = movie.releaseDate?.substring(0,4)
        binding.tvRatingScore.text = movie.voteAverage?.toString() ?: ""

        movie.voteCount?.let {
            binding.tvNumberOfVotes.text = "$it VOTES"
        }

        binding.rbMovieDetail.rating = movie.getRatingBasedOnFiveStars()

        bindGenres(movie, movie.genres ?: listOf())

        binding.tvOverview.text = movie.overview ?: ""
        binding.tvOriginalTitle.text = movie.title ?: ""
        binding.tvType.text = movie.getGenresAsCommaSeparatedString()
        binding.tvProduction.text = movie.getProductionCountriesAsCommaSeparatedString()
        binding.tvDescription.text = movie.overview ?: ""
    }

    private fun bindGenres(movie: MovieVO, genre: List<GenreVO>) {
        movie.genres?.count()?.let {
            binding.tvFirstGenre.text = genre.firstOrNull()?.name ?: ""
            binding.tvSecondGenre.text = genre.getOrNull(1)?.name ?: ""
            binding.tvThirdGenre.text = genre.getOrNull(2)?.name ?: ""

            if(it<3) {
                binding.tvThirdGenre.visibility = View.GONE
            }else if (it<2){
                binding.tvSecondGenre.visibility = View.GONE
            }
        }

    }

    private fun showErrorMessage() {
        Toast.makeText(this,"MovieDetail Error",Toast.LENGTH_LONG).show()
    }

    private fun setUpListeners() {
        binding.btnBack.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun setUpViewPod() {
        mActorListViewPod = binding.vpActors.root
        mActorListViewPod.setUpActorViewPod(
            backgroundColorReference = R.color.colorPrimary,
            titleText = getString(R.string.lbl_actors),
            moreTitleText = ""
        )
        mActorListViewPod.setUpActorListViewPod()

        mCreatorListViewPod= binding.vpCreators.root
        mCreatorListViewPod.setUpActorViewPod(
            backgroundColorReference = R.color.colorPrimary,
            titleText = getString(R.string.lbl_creators),
            moreTitleText = getString(R.string.lbl_more_creator)
        )
        mCreatorListViewPod.setUpActorListViewPod()
    }
}