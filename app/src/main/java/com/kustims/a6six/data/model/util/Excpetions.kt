package com.kustims.a6six.data.model.util

object Exceptions {
    class GoogleAccountNotFoundException(
        override val message: String? = "Google Account Not Found"
    ) : Exception()

    class InvalidUserException(message: String) : Exception(message)
}