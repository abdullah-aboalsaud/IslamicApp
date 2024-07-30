package com.example.islamicapp.ui.hadeth

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HadethModel(
    val title: String,
    val content: String
) : Parcelable
