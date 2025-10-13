package com.example.lab11.navigationAvanzado.loggedFlow.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lab10.navigationAvanzado.loggedFlow.splash.SplashViewModel
import com.example.lab11.data.UserPreferencesRepo

class SplashViewModelFactory(
    private val repository: UserPreferencesRepo
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SplashViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SplashViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}