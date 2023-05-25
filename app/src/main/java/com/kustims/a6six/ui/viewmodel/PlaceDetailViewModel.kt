package com.kustims.a6six.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kustims.a6six.data.model.response.DetailPlaceResponse
import com.kustims.a6six.data.network.APIS
import com.kustims.a6six.data.network.RetrofitClient
import kotlinx.coroutines.launch

class PlaceDetailViewModel(
    application: Application,
    private val placeId: Int,
    private val accessToken: String
) : AndroidViewModel(application) {

    private lateinit var retService: APIS
    private val _place = MutableLiveData<DetailPlaceResponse.Place>()
    val place: LiveData<DetailPlaceResponse.Place> = _place
    private val _reviews = MutableLiveData<List<DetailPlaceResponse.Review>>()
    val reviews: LiveData<List<DetailPlaceResponse.Review>> = _reviews

    init {
        fetchReview()
    }

    fun fetchReview() {
        retService = RetrofitClient.getRetrofitInstance().create(APIS::class.java)

        viewModelScope.launch {
            try {
                val response = retService.getDetailPlaceData("Bearer $accessToken", placeId)
                if (response.isSuccessful) {
                    val data = response.body()?.data
                    if (data != null && data.places.isNotEmpty()) {
                        _place.postValue(data.places[0])
                        _reviews.postValue(data.places[0].reviews) // 이제 이 코드는 잘 작동할 것입니다.
                    }
                } else {
                    Log.e("PlaceDetailViewModel", "Fetch place failed: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("PlaceDetailViewModel", "Fetch place failed: ${e.message}")
            }
        }
    }
}
