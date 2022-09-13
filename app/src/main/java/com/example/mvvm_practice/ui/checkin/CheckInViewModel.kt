package com.example.mvvm_practice.ui.checkin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mvvm_practice.core.BaseViewModel
import com.example.mvvm_practice.models.response.CheckInResponse
import com.example.mvvm_practice.repositories.ICheckInRepository
import com.example.mvvm_practice.util.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CheckInViewModel @Inject constructor(private val iCheckInRepository: ICheckInRepository): BaseViewModel() {

    private val _handleCheckInResponse: MutableLiveData<ApiResult<CheckInResponse>> by lazy { MutableLiveData<ApiResult<CheckInResponse>>() }
    val handleCheckInResponse get() = _handleCheckInResponse

    fun doCheckIn(){
        _handleCheckInResponse.value = ApiResult.Loading(true)
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val result = iCheckInRepository.doCheckIn()
                withContext(Dispatchers.Main){
                    _handleCheckInResponse.value = result
                    _handleCheckInResponse.value = ApiResult.Loading(false)
                }
            }
        }
    }
}