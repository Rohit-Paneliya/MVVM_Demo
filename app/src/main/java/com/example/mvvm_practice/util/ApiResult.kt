package com.example.mvvm_practice.util

sealed class ApiResult<T : Any> {
    class Loading<T: Any>(val isLoading:Boolean) : ApiResult<T>()
    class Success<T: Any>(val data: T) : ApiResult<T>()
    class Error<T: Any>(val code: Int, val message: String?) : ApiResult<T>()
    class Exception<T: Any>(val e: Throwable) : ApiResult<T>()
}