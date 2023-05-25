//package com.kustims.a6six.domain.repository
//
//import com.kustims.a6six.data.Constants.LIKEIT_BASE_URL
//import com.kustims.a6six.data.model.response.HomePlaceResponse
//import com.kustims.a6six.data.network.APIS
//import com.kustims.a6six.di.ServiceGenerator
//import com.kustims.a6six.ui.viewmodelstate.HomeState
//
//class HomeRepository {
//
//    suspend fun getHomePlaceData(accessToken: String): HomeState<HomePlaceResponse> {
//        val apiService = ServiceGenerator.createService(APIS::class.java, accessToken)
//        val response = apiService.getHomePlaceData()
//
//        return if (response.isSuccessful) {
//            val body = response.body()
//            if (body != null) {
//                HomeState.Success(body)
//            } else {
//                HomeState.Error(Exception("Response body is null"))
//            }
//        } else {
//            HomeState.Error(Exception("API request failed with code ${response.code()}"))
//        }
//    }
//
//}