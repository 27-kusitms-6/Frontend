package com.kustims.a6six.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kustims.a6six.data.model.request.RecommendationRequest
import com.kustims.a6six.data.model.response.RecommendationResponse
import com.kustims.a6six.data.network.APIS
import com.kustims.a6six.data.network.RetrofitClient
import kotlinx.coroutines.launch

class RecommendationViewModel(
    application: Application,
    category2: String,
    filters: List<String>,
    orderBy: Int,
    private val accessToken: String
) : AndroidViewModel(application) {

    private lateinit var retService: APIS
    private val _places = MutableLiveData<List<RecommendationResponse.Place>>()
    val places: LiveData<List<RecommendationResponse.Place>> = _places

    init {
        fetchPlaces(category2, filters, orderBy)
    }

    fun fetchPlaces(category2: String, filters: List<String>, orderBy: Int) {
        retService = RetrofitClient.getRetrofitInstance().create(APIS::class.java)

        val request = RecommendationRequest(
            category2 = category2,
            filters = filters,
            orderBy = orderBy
        )

        viewModelScope.launch {
            try {
                val response = retService.getRecommendationRestaurant("Bearer $accessToken", request)
                if (response.isSuccessful) {
                    val data = response.body()?.data
                    _places.postValue(data?.places)
                    Log.d("RecommendationViewModel", "Fetch places success")
                } else {
                    Log.e("RecommendationViewModel", "Fetch places failed: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("RecommendationViewModel", "Fetch places failed: ${e.message}")
            }
        }
    }
}
