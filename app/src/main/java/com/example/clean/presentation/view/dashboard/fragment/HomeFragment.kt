package com.example.clean.presentation.view.dashboard.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.clean.databinding.FragmentHomeBinding
import com.example.clean.domain.model.MovieDetailModel
import com.example.clean.domain.model.UserDetailModel
import com.example.clean.presentation.viewmodel.MovieViewModel
import com.example.clean.utils.ViewState
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: MovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        initViewModel();

        viewModel.loadUpcoming(page = 1)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initViewModel(){
        viewModel.movieListState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ViewState.Loading -> {
                    Log.d("IS_LOADING", "Loading bro")
                }
                is ViewState.Success -> {
                    val users = state.data as List<MovieDetailModel>
                    Log.d("Movie Data ", users.toString())

                }
                is ViewState.Error -> {
                    val errorMessage = state.error
                    Log.d("Error", "Error Bro")
                    Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }
}