package com.example.clean.presentation.view.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.clean.R
import com.example.clean.databinding.ActivityDashboardBinding
import com.example.clean.presentation.view.dashboard.fragment.HomeFragment
import com.example.clean.presentation.view.dashboard.fragment.ProfileFragment

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.itemHome -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.itemAccount -> {
                    replaceFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }

        binding.bottomNavigationView.selectedItemId = R.id.itemHome
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, fragment)
            .commit()
    }
}