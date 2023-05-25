package com.kustims.a6six.ui.viewmodelstate

sealed class UserState<out R> {
    data class Success<out T>(val data: T): MypageState<T>()
    data class Error(val exception: Exception): MypageState<Nothing>()
}