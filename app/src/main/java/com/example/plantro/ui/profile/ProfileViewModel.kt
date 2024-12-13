package com.example.plantro.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.plantro.data.pref.UserRepository
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: UserRepository) : ViewModel() {
    // Mendapatkan email pengguna dari DataStore dan menyediakan LiveData

    val email: LiveData<String> = repository.getEmail().asLiveData()


    // Fungsi logout
    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }
}
