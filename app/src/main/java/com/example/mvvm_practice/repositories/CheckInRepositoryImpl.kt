package com.example.mvvm_practice.repositories

import com.example.mvvm_practice.models.response.CheckInResponse
import com.example.mvvm_practice.network.NetworkApiInterface
import com.example.mvvm_practice.util.ApiResult
import com.example.mvvm_practice.util.handleApiResponse
import javax.inject.Inject

class CheckInRepositoryImpl @Inject constructor(private val networkApiInterface: NetworkApiInterface): ICheckInRepository {

    override suspend fun doCheckIn(): ApiResult<CheckInResponse> {
        return handleApiResponse { networkApiInterface.doCheckIn() }
    }
}