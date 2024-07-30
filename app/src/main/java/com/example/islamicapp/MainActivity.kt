package com.example.islamicapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.islamicapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding:ActivityMainBinding?=null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hideViews()

        linkNavHostWithBottomNavigation()

    }



    private fun hideViews() {
        binding.bottomNavigation.visibility = View.GONE
        binding.appBar.visibility = View.GONE
    }


    private fun linkNavHostWithBottomNavigation() {
        val navController:NavController = findNavController(R.id.nav_host_fragment)
        binding.bottomNavigation.setupWithNavController(navController)

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}