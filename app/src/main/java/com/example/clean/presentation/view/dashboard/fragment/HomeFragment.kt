package com.example.clean.presentation.view.dashboard.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.example.clean.databinding.FragmentHomeBinding
import com.example.clean.domain.model.movie.MovieDetailModel
import com.example.clean.domain.model.person.PersonDetailModel
import com.example.clean.presentation.view.adapter.movie.NowPlayingAdapter
import com.example.clean.presentation.view.adapter.movie.UpcomingAdapter
import com.example.clean.presentation.view.adapter.person.TrendingPersonAdapter
import com.example.clean.presentation.viewmodel.movie.MovieViewModel
import com.example.clean.presentation.viewmodel.person.PersonViewModel
import com.example.clean.utils.ViewState
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var adapter: UpcomingAdapter
    private lateinit var adapterNowPlayingAdapter: NowPlayingAdapter
    private lateinit var adapterPerson: TrendingPersonAdapter

    private val upcomingList = mutableListOf<MovieDetailModel>()
    private val nowPlayingList = mutableListOf<MovieDetailModel>()
    private val trendingPersonList = mutableListOf<PersonDetailModel>()

    private val viewModel: MovieViewModel by viewModel()
    private val viewModelPerson: PersonViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        initFeaturedRecyclerView()
        initNowPlayingRecyclerView()
        initPersonRecyclerView()
        initViewModel();

        viewModel.loadUpcoming(page = 1)
        viewModel.loadNowPlaying(page = 1)
        viewModelPerson.loadTrendingPerson(timeWindow = "day")
        return binding.root
    }

    private fun initPersonRecyclerView() {
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.popularActorLayout.recyclerPopularActor.layoutManager = layoutManager

        adapterPerson = TrendingPersonAdapter(trendingPersonList)

        binding.popularActorLayout.recyclerPopularActor.adapter = adapterPerson
    }

    private fun initNowPlayingRecyclerView() {
        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.nowComingLayout.recyclerNowComing.layoutManager = layoutManager

        adapterNowPlayingAdapter = NowPlayingAdapter(nowPlayingList)

        binding.nowComingLayout.recyclerNowComing.adapter = adapterNowPlayingAdapter
    }

    private fun initFeaturedRecyclerView(){
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.featureLayout.recyclerFeatured.layoutManager = layoutManager

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.featureLayout.recyclerFeatured)

        adapter = UpcomingAdapter(upcomingList)

        binding.featureLayout.recyclerFeatured.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initViewModel(){
        viewModel.movieListState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ViewState.Loading -> {
                    Log.d("IS_LOADING", "Loading bro")
                }
                is ViewState.Success -> {
                    val movies = state.data as List<MovieDetailModel>
                    Log.d("Movie Data ", movies.toString())
                    adapter.addData(movies)

                }
                is ViewState.Error -> {
                    val errorMessage = state.error
                    Log.d("Error", errorMessage)
                    Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_LONG).show()
                }
            }
        }

        viewModel.movieNowPlayingListState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ViewState.Loading -> {
                    Log.d("IS_LOADING", "Loading bro")
                }
                is ViewState.Success -> {
                    val movies = state.data as List<MovieDetailModel>
                    Log.d("Movie Now Playing Data ", movies.toString())
                    adapterNowPlayingAdapter.addData(movies)

                }
                is ViewState.Error -> {
                    val errorMessage = state.error
                    Log.d("Error", errorMessage)
                    Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_LONG).show()
                }
            }
        }

        viewModelPerson.personTrendingListState.observe(viewLifecycleOwner) {state ->
            when (state) {
                is ViewState.Loading -> {
                    Log.d("IS_LOADING", "Loading bro")
                }
                is ViewState.Success -> {
                    val persons = state.data as List<PersonDetailModel>
                    Log.d("Trending Person Data ", persons.toString())
                    adapterPerson.addData(persons)

                }
                is ViewState.Error -> {
                    val errorMessage = state.error
                    Log.d("Error", errorMessage)
                    Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }
}