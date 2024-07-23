package com.app.motivationapp.data.remote



sealed class MotivationResult<out T>(
    val data : T? = null,
    val error : String? = null
){

    class Success<T>(data: T) : MotivationResult<T>(data = data)
    class Failure(error: String) : MotivationResult<Nothing>(error = error)



}