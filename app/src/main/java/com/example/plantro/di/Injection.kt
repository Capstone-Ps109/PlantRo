package com.example.plantro.di

import android.content.Context
import com.example.plantro.data.pref.UserPreference
import com.example.plantro.data.pref.UserRepository
import com.example.plantro.data.pref.dataStore

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        return UserRepository.getInstance(pref)
    }
}