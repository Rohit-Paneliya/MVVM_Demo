package com.example.mvvm_practice.network

import com.example.mvvm_practice.models.response.CheckInResponse
import retrofit2.Response
import retrofit2.http.GET

interface NetworkApiInterface {

    @GET("/check_in")
    suspend fun doCheckIn(): Response<CheckInResponse>
}