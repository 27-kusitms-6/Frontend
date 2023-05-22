package com.kustims.a6six.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kustims.a6six.app.Base.BaseViewModel
import com.kustims.a6six.domain.model.SavePlace
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


class LikelistViewModel: ViewModel() {
    private val _itemList = MutableLiveData<List<SavePlace>>()
    val itemList: LiveData<List<SavePlace>>
        get() = _itemList

    fun onStart() {
        showItemList()
    }

    private fun showItemList() {
        _itemList.value = arrayListOf()

    }
}