package com.example.lab11.navigationAvanzado.ui

import androidx.activity.result.launch
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.lab11.data.UserPreferencesRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val userPreferencesRepository: UserPreferencesRepo,
) : ViewModel() {
    private val _isLoading = MutableStateFlow(false)

    val isLoading: StateFlow<Boolean> = _isLoading
    companion object {
        fun Factory(
            userPreferencesRepository: UserPreferencesRepo,
        ): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
                        return LoginViewModel(
                            userPreferencesRepository,
                            // charactersRepository
                        ) as T
                    }
                    throw IllegalArgumentException("Unknown ViewModel class")
                }
            }
        }
    }
    private val _username = MutableStateFlow("")
    val username = _username.asStateFlow()

    fun onUsernameChange(newUsername: String) {
        _username.value = newUsername
    }
    fun login(onSuccess: () -> Unit) {
        viewModelScope.launch {
            userPreferencesRepository.saveUserName(_username.value)
            onSuccess()
        }
    }
}