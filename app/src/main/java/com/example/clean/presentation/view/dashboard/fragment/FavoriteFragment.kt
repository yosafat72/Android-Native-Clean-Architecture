package com.example.clean.presentation.view.dashboard.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.clean.databinding.FragmentProfileBinding
import com.example.clean.domain.model.movie.MovieDetailModel
import com.example.clean.domain.model.person.PersonDetailModel
import com.example.clean.presentation.viewmodel.movie.MovieViewModel
import com.example.clean.utils.ViewState
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    private val viewModel: MovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        initViewModel()

        viewModel.loadFavoriteMovie(page = 1)

        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initViewModel(){
        viewModel.movieFavoriteListState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ViewState.Loading -> {
                    Log.d("IS_LOADING", "LOADING")
                }
                is ViewState.Success -> {

                    val movies = state.data as List<MovieDetailModel>

                    Log.d("IS_DATA_FAVORITE", movies.toString())

                }
                is ViewState.Error -> {
                    val errorMessage = state.error
                    Log.d("ID_ERROR", errorMessage)
                    Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

}