package com.example.plantro.ui.input

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantro.data.pref.UserRepository
import com.example.plantro.data.remote.response.OutputResponse
import com.example.plantro.data.remote.retrofit.ApiConfig
import com.example.plantro.data.remote.retrofit.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class InputViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _outputResponse = MutableStateFlow<OutputResponse?>(null)
    val outputResponse: StateFlow<OutputResponse?> = _outputResponse

    fun inputData(
        nitrogen: Int,
        phosphorus: Int,
        potassium: Int,
        temperature: Float,
        humidity: Float,
        pH_Value: Float,
        rainfall: Float
    ) {
        viewModelScope.launch {
            try {
                _loading.value = true

                // Ambil token dari DataStore melalui UserRepository
                userRepository.getSession().collect { user ->
                    val token = user.token

                    if (token.isNotEmpty()) {
                        // Create an InputParams object to match the API
                        val inputParams = ApiService.InputParams(
                            Nitrogen = nitrogen,
                            Phosphorus = phosphorus,
                            Potassium = potassium,
                            Temperature = temperature,
                            Humidity = humidity,
                            pH_Value = pH_Value,
                            Rainfall = rainfall
                        )

                        // Call the API with the token in header
                        val result = ApiConfig.getApiService().input(
                            token = "$token",
                            params = inputParams
                        )
                        _outputResponse.value = result
                        _loading.value = false
                    } else {
                        Log.d("InputViewModel", "Token is empty")
                    }
                }
            } catch (e: HttpException) {
                Log.d("InputViewModel", "Error: ${e.message}")
            } finally {
            }
        }
    }
}
