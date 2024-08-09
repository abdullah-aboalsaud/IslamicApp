package com.example.islamicapp.utils

import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.islamicapp.MainActivity
import com.example.islamicapp.R
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView

fun Fragment.hideBottomNavigationView() {
    val bottomNavigationView =
        (activity as MainActivity).findViewById<BottomNavigationView>(R.id.bottom_navigation)
    bottomNavigationView.visibility = View.GONE
}

fun Fragment.showBottomNavigationView() {
    val bottomNavigationView =
        (activity as MainActivity).findViewById<BottomNavigationView>(R.id.bottom_navigation)
    bottomNavigationView.visibility = View.VISIBLE
}

fun Fragment.hideBtnBack() {
    (activity as MainActivity).findViewById<ImageView>(R.id.btn_back).visibility = View.GONE
}

fun Fragment.showBtnBack() {
    (activity as MainActivity).findViewById<ImageView>(R.id.btn_back).visibility = View.VISIBLE
}


fun Fragment.showAppBar() {
    (activity as MainActivity).findViewById<AppBarLayout>(R.id.app_bar).visibility = View.VISIBLE
}

fun Fragment.btnBack() {
    (activity as MainActivity).findViewById<ImageView>(R.id.btn_back).setOnClickListener {
        findNavController().navigateUp()
    }

}