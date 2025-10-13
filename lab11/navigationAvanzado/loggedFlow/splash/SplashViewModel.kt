package com.example.lab10.navigationAvanzado.loggedFlow.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.lab11.data.UserPreferencesRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


data class SplashUiState(
    val isLoggedIn: Boolean? = null
)

class SplashViewModel(
    private val userPreferencesRepository: UserPreferencesRepo
) : ViewModel() {

    private val _uiState = MutableStateFlow(SplashUiState())
    val uiState = _uiState.asStateFlow()

    init {
        checkUserLoginStatus()
    }

    private fun checkUserLoginStatus() {
        viewModelScope.launch {
            val userName = userPreferencesRepository.userName.first()

            _uiState.value = SplashUiState(isLoggedIn = !userName.isNullOrBlank())
        }
    }
    //----------------------

    val userName = userPreferencesRepository.userName

    companion object {

        fun Factory(
            userPreferencesRepository: UserPreferencesRepo
        ): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(SplashViewModel::class.java)) {
                        return SplashViewModel(userPreferencesRepository) as T
                    }
                    throw IllegalArgumentException("Unknown ViewModel class")
                }
            }
        }
    }
}

