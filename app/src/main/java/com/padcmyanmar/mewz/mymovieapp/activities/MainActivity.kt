package com.padcmyanmar.mewz.mymovieapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.padcmyanmar.mewz.mymovieapp.R
import com.padcmyanmar.mewz.mymovieapp.adapters.BannerAdapter
import com.padcmyanmar.mewz.mymovieapp.adapters.ShowCaseAdapter
import com.padcmyanmar.mewz.mymovieapp.data.model.MovieModel
import com.padcmyanmar.mewz.mymovieapp.data.model.MovieModelImpl
import com.padcmyanmar.mewz.mymovieapp.databinding.ActivityMainBinding
import com.padcmyanmar.mewz.mymovieapp.delegate.BannerViewHolderDelegate
import com.padcmyanmar.mewz.mymovieapp.delegate.MovieViewHolderDelegate
import com.padcmyanmar.mewz.mymovieapp.delegate.ShowcaseViewHolderDelegate
import com.padcmyanmar.mewz.mymovieapp.dummy.dummyGenreList
import com.padcmyanmar.mewz.mymovieapp.viewpods.MovieListViewPod

class MainActivity : AppCompatActivity(),BannerViewHolderDelegate, ShowcaseViewHolderDelegate, MovieViewHolderDelegate {

    private lateinit var binding: ActivityMainBinding

    private lateinit var mBannerAdapter: BannerAdapter
    private lateinit var mShowcaseAdapter: ShowCaseAdapter

    private lateinit var mBestPopularMovieListViewPod: MovieListViewPod
    private lateinit var mMovieByGenreViewPod: MovieListViewPod

    /// Model
    private val mMovieModel: MovieModel = MovieModelImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpToolbar()
        setUpBannerViewPager()

        setUpGenreTabLayout()
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
                // Show Error Message
            }
        )

        // Popular Movies
        mMovieModel.getPopularMovies(
            onSuccess = {
//                mBestPopularMovieListViewPod.setData(it)
            },
            onFailure = {
                // Show Error Message
            }
        )

        // Top Rated Movies
        mMovieModel.getTopRatedMovies(
            onSuccess = {
                print("mMovieModel.getTopRatedMovies : $it")
                mShowcaseAdapter.setNewData(it)
            },
            onFailure = {
                // Show Error Message
            }
        )
    }

    private fun setUpViewPods() {
        mBestPopularMovieListViewPod = binding.vpBestPopularMovieList as MovieListViewPod
        mMovieByGenreViewPod = binding.vpMovieByGenre as MovieListViewPod

//        mBestPopularMovieListViewPod.setUpMovieListViewPod(this)
//        mMovieByGenreViewPod.setUpMovieListViewPod(this)
    }

    private fun setUpShowcaseRecyclerView() {
        mShowcaseAdapter = ShowCaseAdapter(this)
        binding.rvShowCases.adapter = mShowcaseAdapter
        binding.rvShowCases.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setUpListeners() {
        binding.tabLayoutGenre.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                Snackbar.make(window.decorView, tab?.text?: "", Snackbar.LENGTH_LONG).show()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }

    private fun setUpGenreTabLayout() {
        dummyGenreList.forEach{
            binding.tabLayoutGenre.newTab().apply {
                text = it
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

    override fun onTapMovieFormBanner() {
        startActivity(MovieDetailActivity.newIntent(this))
    }

    override fun onTapMovieFromShowcase() {
        startActivity(MovieDetailActivity.newIntent(this))
    }

    override fun onTapMovie() {
        startActivity(MovieDetailActivity.newIntent(this))
    }

}