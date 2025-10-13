package com.example.lab10.navigationAvanzado.loggedFlow.LugarPerfil

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uvg.plataformas.lab10.data.Lugar
import uvg.plataformas.lab10.data.LocationProfileUiState
import uvg.plataformas.lab10.loggedFlow.LoggedRoutes
import kotlin.random.Random

class LocationProfileViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val locationId: Int = savedStateHandle.toRoute<LoggedRoutes.LocationProfile>().locationId

    private val _uiState = MutableStateFlow(LocationProfileUiState())
    val uiState: StateFlow<LocationProfileUiState> = _uiState.asStateFlow()

    init {
        loadLocation()
    }

    fun loadLocation() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, hasError = false)

            delay(2000)

            val randomNumber = Random.nextInt(1, 11)

            if (randomNumber % 2 == 0) {
                val location = Lugar(
                    id = locationId,
                    name = "Earth (C-137)",
                    type = "Planet",
                    dimension = "Dimension C-137"
                )

                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    location = location,
                    hasError = false
                )
            } else {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    hasError = true
                )
            }
        }
    }

    fun onLoadingClick() {
        _uiState.value = _uiState.value.copy(
            isLoading = false,
            hasError = true
        )
    }

    fun retry() {
        loadLocation()
    }
}