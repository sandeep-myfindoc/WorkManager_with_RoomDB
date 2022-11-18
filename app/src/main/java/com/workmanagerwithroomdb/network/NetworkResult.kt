package com.workmanagerwithroomdb.network

sealed class NetworkResult<T>(var data: T? = null, var errorMessage: String? = null) {
    class Loading<T>: NetworkResult<T>()
    class Sucess<T>(data: T? = null) : NetworkResult<T>(data=data)
    class Error<T>(errorMessage: String): NetworkResult<T>(errorMessage = errorMessage)
}