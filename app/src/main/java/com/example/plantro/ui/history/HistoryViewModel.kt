package com.example.plantro.ui.favorite

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantro.data.pref.UserRepository
import com.example.plantro.data.remote.response.OutputHistory
import com.example.plantro.data.remote.retrofit.ApiConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException


class HistoryViewModel(private val userRepository: UserRepository) : ViewModel(){

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _outputHistory = MutableStateFlow<OutputHistory?>(null)
    val outputHistory: StateFlow<OutputHistory?> = _outputHistory

    fun historyData() {
        viewModelScope.launch {
            try {
                _loading.value = true

                // Ambil token dari DataStore melalui UserRepository
                userRepository.getSession().collect { user ->
                    val token = user.token

                    if (token.isNotEmpty()) {
                        // Call the API with the token in header
                        val result = ApiConfig.getApiService().history(token = "$token")
                        _outputHistory.value = result
                        _loading.value = false
                    } else {
                        Log.d("InputViewModel", "Token is empty")
                    }
                }
            } catch (e: HttpException) {
                Log.d("HistoryViewModel", "Error: ${e.message}")
            } finally {
            }
        }
    }
}
