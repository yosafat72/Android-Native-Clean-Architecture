package com.example.clean.presentation.view.dashboard

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.clean.R
import com.example.clean.databinding.ActivityMovieDetailBinding
import com.example.clean.domain.model.movie.GenresItem
import com.example.clean.domain.model.movie.MovieDetailItemModel
import com.example.clean.domain.model.movie.ProductionCompaniesItem
import com.example.clean.presentation.view.adapter.movie.GenreMovieAdapter
import com.example.clean.presentation.view.adapter.movie.ProductionMovieAdapter
import com.example.clean.presentation.viewmodel.movie.MovieDetailViewModel
import com.example.clean.utils.DateConverter
import com.example.clean.utils.Urls
import com.example.clean.utils.ViewState
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding

    private lateinit var adapterGenre: GenreMovieAdapter
    private lateinit var adapterProductionMovieAdapter: ProductionMovieAdapter

    private val genresList = mutableListOf<GenresItem>()
    private val productionCompanyList = mutableListOf<ProductionCompaniesItem>()

    private val viewModel: MovieDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initViewModel()
        initGenreRecyclerView()
        initProductionCompanyRecyclerView()

        val movieId = intent.getIntExtra("movieId", 0)

        viewModel.detailMovie(movieId = movieId)
    }

    private fun initProductionCompanyRecyclerView() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerProduction.layoutManager = layoutManager

        adapterProductionMovieAdapter = ProductionMovieAdapter(productionCompanyList)

        binding.recyclerProduction.adapter = adapterProductionMovieAdapter
    }

    private fun initGenreRecyclerView() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerGenre.layoutManager = layoutManager

        adapterGenre = GenreMovieAdapter(genresList)

        binding.recyclerGenre.adapter = adapterGenre
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
        binding.tvVote.text = data.voteCount.toString() + " Vote from the audience"
        binding.tvReleaseData.text = data.releaseDate?.let { DateConverter().convertDate(it) }
        binding.tvLanguage.text = data.originalLanguage
        binding.tvPopularity.text = data.popularity.toString()
        binding.tvOverview.text = data.overview

        adapterGenre.addData(item = data.genres as List<GenresItem>)
        adapterProductionMovieAdapter.addData(item = data.productionCompanies as List<ProductionCompaniesItem>)
    }

}