package com.kustims.a6six.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kustims.a6six.data.model.response.HomePlaceResponse
import com.kustims.a6six.data.network.APIS
import com.kustims.a6six.data.network.RetrofitClient
import com.kustims.a6six.data.util.PreferenceManager
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var retService: APIS
    private val _tasks = MutableLiveData<List<HomePlaceResponse.Data>>()
    val tasks: LiveData<List<HomePlaceResponse.Data>> = _tasks
    val accessToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0bHNhbHN0ajAxQGR1a3N1bmcuYWMua3IiLCJhdXRoIjoiUk9MRV9VU0VSIiwiaWF0IjoxNjg0OTEwMDMyLCJleHAiOjE2ODg1MTAwMzJ9.klAMhLWSUQL-43lzS0i4vbWI-slpPkixz6hUxG1n4Tx1xj9Kl7rDt4Ee1ccPkj1istfYNUZdWteqD-JELtX_Nw"


    init {
        fetchTasks()
    }

    private fun fetchTasks() {
//        val accessToken = preferenceManager.getString(PreferenceManager.ACCESS_TOKEN)
        //retrofit
        retService = RetrofitClient
            .getRetrofitInstance()
            .create(APIS::class.java)

        viewModelScope.launch {
            try {
                val response = retService.getHomePlaceData("Bearer $accessToken")
                if (response.isSuccessful) {
                    _tasks.value = response.body()?.data
                    Log.d("HomeViewModel API Success", "fetchTasks: ${response.body()?.data}")
                } else {
                    Log.d("HomeViewModel API Fail1", "fetchTasks: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.d("HomeViewModel API Fail2", "fetchTasks: ${e.message}")
            }
        }
    }
}
