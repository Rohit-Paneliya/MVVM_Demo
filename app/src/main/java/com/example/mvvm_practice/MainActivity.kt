package com.example.mvvm_practice

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_practice.databinding.ActivityMainBinding
import com.example.mvvm_practice.ui.checkin.CheckInViewModel
import com.example.mvvm_practice.util.ApiResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CheckInViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[CheckInViewModel::class.java]
        setUi()
        setObservers()
    }

    private fun setObservers() {
        viewModel.handleCheckInResponse.observe(this) {
            when (it) {
                is ApiResult.Loading -> {
                    shouldShowLoader(it.isLoading)
                }
                is ApiResult.Success -> {
                    showToast("Checked in successfully")
                    val response = it.data
                    Log.d("----------------", "$response")
                }
                is ApiResult.Exception -> {
                    //TODO handle error
                    binding.apply {
                        binding.buttonCheckIn.visibility = View.GONE
                        errorLabel.visibility = View.VISIBLE
                        errorLabel.text = it.e.message ?: "Exception occurred"
                    }
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    private fun shouldShowLoader(shouldShow: Boolean) {
        binding.progressBar.visibility = if (shouldShow) View.VISIBLE else View.GONE
        binding.buttonCheckIn.visibility = if (shouldShow) View.GONE else View.VISIBLE
    }

    private fun setUi() {
        binding.buttonCheckIn.setOnClickListener {
            viewModel.doCheckIn()
        }
    }
}
