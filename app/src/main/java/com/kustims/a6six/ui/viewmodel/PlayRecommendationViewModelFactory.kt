package com.kustims.a6six.ui.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PlayRecommendationViewModelFactory(
    private val application: Application,
    private val category2: String,
    private val filters: List<String>,
    private val orderBy: Int,
    private val accessToken: String
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlayRecommendationViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PlayRecommendationViewModel(application, category2, filters, orderBy, accessToken) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}