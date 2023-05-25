package com.kustims.a6six.ui.viewmodelstate

sealed class HomeState<out R> {
    data class Success<out T>(val data: T): HomeState<T>()
    data class Error(val exception: Exception): HomeState<Nothing>()
}