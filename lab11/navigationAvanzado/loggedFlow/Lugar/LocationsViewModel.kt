package com.example.lab10.navigationAvanzado.loggedFlow.Lugar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uvg.plataformas.lab10.data.Lugar
import uvg.plataformas.lab10.data.LocationsListUiState
import kotlin.random.Random

class LocationsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LocationsListUiState())
    val uiState: StateFlow<LocationsListUiState> = _uiState.asStateFlow()

    init {
        loadLocations()
    }

    fun loadLocations() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, hasError = false)

            delay(2000)

            val randomNumber = Random.nextInt(1, 11)

            if (randomNumber % 2 == 0) {
                val locations = listOf(
                    Lugar(1, "Earth (C-137)", "Planet", "Dimension C-137"),
                    Lugar(2, "Citadel of Ricks", "Space station", "Unknown"),
                    Lugar(3, "Worldender's lair", "Planet", "Unknown"),
                    Lugar(4, "Anatomy Park", "Microverse", "Dimension C-137"),
                    Lugar(5, "Interdimensional Cable", "TV", "Unknown")
                )

                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    locations = locations,
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
        loadLocations()
    }
}