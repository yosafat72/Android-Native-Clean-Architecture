package com.example.clean.presentation.view.dashboard

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.clean.R
import com.example.clean.databinding.ActivityMovieDetailBinding
import com.example.clean.domain.model.movie.MovieDetailItemModel
import com.example.clean.domain.model.movie.MovieDetailModel
import com.example.clean.presentation.viewmodel.movie.MovieDetailViewModel
import com.example.clean.utils.Urls
import com.example.clean.utils.ViewState
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding

    private val viewModel: MovieDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initViewModel()

        val movieId = intent.getIntExtra("movieId", 0)

        viewModel.detailMovie(movieId = movieId)
    }

    private fun initView() {
        binding.btnBack.setOnClickListener { finish() }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initViewModel(){
        viewModel.movieDetailState.observe(this) { state ->
            when (state) {
                is ViewState.Loading -> {
                    Log.d("IS_LOADING", "LOADING")
                }
                is ViewState.Success -> {

                    val movie = state.data as MovieDetailItemModel
                    Log.d("DETAIL_MOVIES", movie.toString())
                    updateView(data = movie)

                }
                is ViewState.Error -> {
                    val errorMessage = state.error
                    Log.d("ID_ERROR", errorMessage)
                    Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateView(data: MovieDetailItemModel){
        Glide.with(this)
            .load(Urls.BASE_IMAGE_POSTER_URL + data.backdropPath)
            .placeholder(R.drawable.image_placeholder)
            .error(R.drawable.image_placeholder)
            .into(binding.imgMoviePoster)

        binding.tvTitle.text = data.title
        binding.tvVote.text = data.voteCount.toString() + "Vote"
        binding.tvStatus.text = data.status
        binding.tvLanguage.text = data.spokenLanguages?.get(0)?.name ?: "English"
        binding.tvPopularity.text = data.popularity.toString()
        binding.tvOverview.text = data.overview
    }

}