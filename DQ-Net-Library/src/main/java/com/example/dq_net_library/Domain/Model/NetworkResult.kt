package com.example.dq_net_library.Domain.Model

sealed class NetworkResult<out T> {
    data class Success<T>(val data: T): NetworkResult<T>()
    data class Error(val errorResponse: ErrorResponse) : NetworkResult<Nothing>()
    object NoInternet: NetworkResult<Nothing>()
}