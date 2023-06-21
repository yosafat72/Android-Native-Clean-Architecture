package com.example.clean.presentation.view.dashboard.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.clean.databinding.FragmentHomeBinding
import com.example.clean.domain.model.MovieDetailModel
import com.example.clean.presentation.view.adapter.movie.UpcomingAdapter
import com.example.clean.presentation.viewmodel.MovieViewModel
import com.example.clean.utils.ViewState
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: UpcomingAdapter
    private val upcomingList = mutableListOf<MovieDetailModel>()

    private val viewModel: MovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        initFeaturedRecyclerView()
        initViewModel();

        viewModel.loadUpcoming(page = 1)
        return binding.root
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
    }
}