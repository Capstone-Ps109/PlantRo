package com.example.plantro.ui.HomeActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.plantro.data.pref.UserModel
import com.example.plantro.data.pref.UserRepository
import kotlinx.coroutines.launch

class HomeActivityViewModel (private val repository: UserRepository) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading = _isLoading

    init {
        getSession()
    }

    // Retrieve session data
    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }

    // Logout user and clear session
    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }
}