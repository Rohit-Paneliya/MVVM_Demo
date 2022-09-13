package com.example.mvvm_practice.repositories

import com.example.mvvm_practice.models.response.CheckInResponse
import com.example.mvvm_practice.util.ApiResult

interface ICheckInRepository {

    suspend fun doCheckIn(): ApiResult<CheckInResponse>
}