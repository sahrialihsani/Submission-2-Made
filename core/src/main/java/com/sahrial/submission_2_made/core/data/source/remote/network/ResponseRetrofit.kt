package com.sahrial.submission_2_made.core.data.source.remote.network


sealed class ResponseRetrofit<out R> {
    data class Success<out T>(val data: T) : ResponseRetrofit<T>()
    data class Error(val errorMessage: String) : ResponseRetrofit<Nothing>()
    object Empty : ResponseRetrofit<Nothing>()
}