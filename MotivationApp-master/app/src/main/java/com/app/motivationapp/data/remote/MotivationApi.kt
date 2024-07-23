package com.app.motivationapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface MotivationApi {


    @GET("random")
    suspend fun getFacts() : Motivation


    companion object {
        const val BASE_URL = "https://dummyjson.com/quotes/"
    }


}