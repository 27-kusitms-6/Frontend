package com.kustims.a6six.data.model.util

sealed class RequestState<out T> {
    object Idle : RequestState<Nothing>()
    object Loading : RequestState<Nothing>()
    data class Success<T>(val data: T) : RequestState<T>()
    data class Error(val t: Throwable) : RequestState<Nothing>()

}