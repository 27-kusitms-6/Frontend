package com.kustims.a6six.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.kustims.a6six.app.Base.BaseViewModel
import com.kustims.a6six.data.model.response.GetUserInfoResponse
import com.kustims.a6six.data.util.PreferenceManager
import com.kustims.a6six.domain.repository.MypageRepository
import com.kustims.a6six.ui.viewmodelstate.MypageState
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MypageViewModel: BaseViewModel() {
    private val mypageRepository = MypageRepository()

    suspend fun getUserInfo(
        accessToken: String,
        context: CoroutineContext = Dispatchers.IO,
        start: CoroutineStart = CoroutineStart.DEFAULT,
    ): MypageState<GetUserInfoResponse> = viewModelScope.async(
        context = context,
        start = start
    ) {
        mypageRepository.getUserInfo(
            accessToken = accessToken
        )
    }.await()

    suspend fun putFilters(pm: PreferenceManager, filters:List<String>) =
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                pm.putFilters(filters)
            }
        }

    suspend fun saveUserInfo(pm: PreferenceManager, imgUrl:String?, name:String?, nickname: String?, phoneNum:String?, email:String?, birthDate: String?) =
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                pm.putInfo(imgUrl, name, nickname, email, phoneNum,birthDate)
            }
        }

    suspend fun setFilters(accessToken: String, filter: List<String>) = viewModelScope.async {
        mypageRepository.setFilter(accessToken, filter)
    }.await()
}
