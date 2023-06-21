package com.example.clean.presentation.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clean.data.model.UserDetailEntity
import com.example.clean.databinding.ActivityMainBinding
import com.example.clean.domain.model.UserDetailModel
import com.example.clean.presentation.view.adapter.UserAdapter
import com.example.clean.presentation.viewmodel.UserViewModel
import com.example.clean.utils.ViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserAdapter
    private val userList = mutableListOf<UserDetailModel>()

    private val viewModel: UserViewModel by viewModel()

    var currentPage = 1
    val itemsPerPage = 6

    var isLastPage = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        initViewModel()
        loadMore()

        viewModel.loadUsers(page = currentPage)
    }

    private fun initRecyclerView(){
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager

        adapter = UserAdapter(userList)
        binding.recyclerView.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initViewModel(){
        viewModel.userListState.observe(this) { state ->
            when (state) {
                is ViewState.Loading -> {
                    Log.d("IS_LOADING", "Loading bro")
                }
                is ViewState.Success -> {
                    val users = state.data as List<UserDetailModel>
                    Log.d("Users Data ", users.toString())
                    isLastPage = users.isEmpty()
                    adapter.addData(users)
                }
                is ViewState.Error -> {
                    val errorMessage = state.error
                    Log.d("Error", "Error Bro")
                }
            }
        }
    }

    private fun loadMore(){
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!isLastPage) {
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                        // Load more data
                        currentPage++
                        viewModel.loadUsers(page = currentPage)
                    }
                }
            }
        });
    }
}