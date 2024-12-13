package com.example.plantro.ui.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantro.data.pref.UserRepository
import com.example.plantro.data.remote.response.RegisterResponse
import com.example.plantro.data.remote.retrofit.ApiConfig2
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class SignUpViewModel (private val userRepository: UserRepository) : ViewModel() {

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _registerResult = MutableStateFlow<String?>(null)
    val registerResult = _registerResult

    fun register(name: String, email: String, password: String) {
        viewModelScope.launch {
            try {
                _loading.value = true
                val requestBody = mapOf(
                    "name" to name,
                    "email" to email,
                    "password" to password
                )
                val result = ApiConfig2.getApiService().register(requestBody)
                _registerResult.value = result.message
            } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()?.string()
                val errorResponse = Gson().fromJson(errorBody, RegisterResponse::class.java)
                Log.d("SignupViewModel", "Registration failed: ${e.message}")
                _registerResult.value = errorResponse.message
            } finally {
                _loading.value = false
            }
        }
    }
}
