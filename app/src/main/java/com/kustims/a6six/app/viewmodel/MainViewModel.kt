package com.kustims.a6six.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import timber.log.Timber

class MainViewModel @Inject constructor(
    private val getVersionUseCase: GetVersionUseCase
) : ViewModel() {

    private val _recentVersion = MutableStateFlow<String>("")
    val recentVersion : StateFlow<String> get() = _recentVersion

    fun getRecentVersion() {
        try {
            viewModelScope.launch {
                getVersionUseCase().collectLatest {
                    it.onSuccess {
                        _recentVersion.emit(it)
                    }.onFailure {
                        Timber.e(it)
                    }
                }
            }
        } catch (e :Exception) {
            Timber.e(e)
        }
    }
}