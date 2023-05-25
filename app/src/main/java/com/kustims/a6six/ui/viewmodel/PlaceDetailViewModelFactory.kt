package com.kustims.a6six.ui.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PlaceDetailViewModelFactory(
    private val application: Application,
    private val placeId: Int,
    private val accessToken: String
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlaceDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PlaceDetailViewModel(application, placeId, accessToken) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}