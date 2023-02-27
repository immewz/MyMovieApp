package com.padcmyanmar.mewz.mymovieapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.padcmyanmar.mewz.mymovieapp.R
import com.padcmyanmar.mewz.mymovieapp.adapters.BannerAdapter
import com.padcmyanmar.mewz.mymovieapp.adapters.ShowCaseAdapter
import com.padcmyanmar.mewz.mymovieapp.data.model.MovieModel
import com.padcmyanmar.mewz.mymovieapp.data.model.MovieModelImpl
import com.padcmyanmar.mewz.mymovieapp.data.vos.GenreVO
import com.padcmyanmar.mewz.mymovieapp.databinding.ActivityMainBinding
import com.padcmyanmar.mewz.mymovieapp.delegate.BannerViewHolderDelegate
import com.padcmyanmar.mewz.mymovieapp.delegate.MovieViewHolderDelegate
import com.padcmyanmar.mewz.mymovieapp.delegate.ShowcaseViewHolderDelegate
import com.padcmyanmar.mewz.mymovieapp.viewpods.ActorListViewPod
import com.padcmyanmar.mewz.mymovieapp.viewpods.MovieListViewPod

class MainActivity : AppCompatActivity(),BannerViewHolderDelegate, ShowcaseViewHolderDelegate, MovieViewHolderDelegate {

    private lateinit var binding: ActivityMainBinding

    private lateinit var mBannerAdapter: BannerAdapter
    private lateinit var mShowcaseAdapter: ShowCaseAdapter

    private lateinit var mBestPopularMovieListViewPod: MovieListViewPod
    private lateinit var mMoviesByGenreViewPod: MovieListViewPod
    private lateinit var mMovieByGenreViewPod: MovieListViewPod
    private lateinit var mActorListViewPod: ActorListViewPod

    /// Model
    private val mMovieModel: MovieModel = MovieModelImpl

    // Data
    private var mGenre: List<GenreVO>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpToolbar()
        setUpBannerViewPager()

        // setUpGenreTabLayout()
        setUpListeners()

        setUpShowcaseRecyclerView()
        setUpViewPods()

        requestData()
    }

    private fun requestData() {
        // Now Playing Movies
        mMovieModel.getNowPlayingMovies(
            onSuccess = {
                mBannerAdapter.setNewData(it)
            },
            onFailure = {
                showErrorMessage()
            }
        )

        // Popular Movies
        mMovieModel.getPopularMovies(
            onSuccess = {
                mBestPopularMovieListViewPod.setData(it)
            },
            onFailure = {
                showErrorMessage()
            }
        )

        // Top Rated Movies
        mMovieModel.getTopRatedMovies(
            onSuccess = {
                mShowcaseAdapter.setNewData(it)
            },
            onFailure = {
                showErrorMessage()
            }
        )

        // Get Genre
        mMovieModel.getGenres(
            onSuccess = {
                mGenre = it
                setUpGenreTabLayout(it)

                // Get Movies By Genre For First Genre
                it.firstOrNull()?.id?.let { genreId ->
                    getMoviesByGenre(genreId)
                }

            },
            onFailure = {
                showErrorMessage()
            }
        )

        // Get Actor
        mMovieModel.getActors(
            onSuccess = {
                mActorListViewPod.setData(it)
            },
            onFailure = {
                showErrorMessage()
            }
        )
    }

    private fun getMoviesByGenre(genreId: Int) {
        mMovieModel.getMoviesByGenre(genreId = genreId.toString(),
            onSuccess = {
                mMovieByGenreViewPod.setData(it)
            },
            onFailure = {
                showErrorMessage()
            })
    }

    private fun setUpViewPods() {
        mBestPopularMovieListViewPod = binding.vpBestPopularMovieList.root
        mMovieByGenreViewPod = binding.vpMovieByGenre.root
        mActorListViewPod = binding.vpBestActor.root

        mBestPopularMovieListViewPod.setUpMovieListViewPod(this)
        mMovieByGenreViewPod.setUpMovieListViewPod(this)
        mActorListViewPod.setUpActorListViewPod()
    }

    private fun setUpShowcaseRecyclerView() {
        mShowcaseAdapter = ShowCaseAdapter(this)
        binding.rvShowCases.adapter = mShowcaseAdapter
        binding.rvShowCases.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setUpListeners() {
        binding.tabLayoutGenre.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                mGenre?.get(tab?.position ?: 0)?.id?.let {
                    getMoviesByGenre(it)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }

    private fun setUpGenreTabLayout(genreList: List<GenreVO>) {
        genreList.forEach{
            binding.tabLayoutGenre.newTab().apply {
                text = it.name
                binding.tabLayoutGenre.addTab(this)
            }
        }
    }

    private fun setUpBannerViewPager() {
        mBannerAdapter = BannerAdapter(this)
        binding.viewPagerBanner.adapter = mBannerAdapter

        binding.dotsIndicatorBanner.attachTo(binding.viewPagerBanner)
    }

    private fun setUpToolbar() {
        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_discover, menu)
        return true
    }

    override fun onTapMovieFormBanner(movieId: Int) {
        //Toast.makeText(this,"Movie Banner",Toast.LENGTH_LONG).show()
        startActivity(MovieDetailActivity.newIntent(this, movieId = movieId))
    }

    override fun onTapMovieFromShowcase(movieId: Int) {
        //Toast.makeText(this,"Movie ShowCase",Toast.LENGTH_LONG).show()
        startActivity(MovieDetailActivity.newIntent(this, movieId = movieId))
    }

    override fun onTapMovie(movieId: Int) {
        //Toast.makeText(this,"Movie Tap",Toast.LENGTH_LONG).show()
        startActivity(MovieDetailActivity.newIntent(this, movieId = movieId))
    }

    private fun showErrorMessage() {
        Toast.makeText(this,"Movie Failure",Toast.LENGTH_LONG).show()
    }

}