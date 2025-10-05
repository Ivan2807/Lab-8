package com.example.lab10.navigationAvanzado.loggedFlow.PersonajesPerfil

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uvg.plataformas.lab10.data.Character
import uvg.plataformas.lab10.data.CharacterProfileUiState
import uvg.plataformas.lab10.loggedFlow.LoggedRoutes
import kotlin.random.Random

class CharacterProfileViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val characterId: Int = savedStateHandle.toRoute<LoggedRoutes.CharacterProfile>().characterId

    private val _uiState = MutableStateFlow(CharacterProfileUiState())
    val uiState: StateFlow<CharacterProfileUiState> = _uiState.asStateFlow()

    init {
        loadCharacter()
    }

    fun loadCharacter() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, hasError = false)

            delay(2000)

            val randomNumber = Random.nextInt(1, 11)

            if (randomNumber % 2 == 0) {
                val character = getMockCharacter(characterId)

                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    character = character,
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
        loadCharacter()
    }

    private fun getMockCharacter(id: Int): Character {
        return Character(
            id = id,
            name = "Rick Sanchez",
            status = "Alive",
            species = "Human",
            gender = "Male",
            image = "https://rickandmortyapi.com/api/character/avatar/$id.jpeg"
        )
    }
}