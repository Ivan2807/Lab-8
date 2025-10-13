
package com.example.lab10.navigationAvanzado.loggedFlow.personajes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uvg.plataformas.lab10.data.Character
import kotlin.random.Random
data class PersonajesUiState(
    val isLoading: Boolean = false,
    val characters: List<Character> = emptyList(),
    val character: Character? = null,
    val hasError: Boolean = false
)

class PersonajesViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(PersonajesUiState(isLoading = true))
    val uiState: StateFlow<PersonajesUiState> = _uiState.asStateFlow()

    init {
        loadCharacters()
    }

    fun loadCharacters() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, hasError = false) }
            delay(4000)

            if (Random.nextInt(1, 11) % 2 == 0) {

                _uiState.update {
                    it.copy(isLoading = false, characters = getSampleCharacters(), hasError = false)
                }
            } else {
                _uiState.update {
                    it.copy(isLoading = false, hasError = true)
                }
            }
        }
    }

    fun loadCharacterProfile(characterId: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, hasError = false) }
            delay(2000)

            if (Random.nextInt(1, 11) % 2 == 0) {
                val foundCharacter = _uiState.value.characters.find { it.id == characterId }
                    ?: getCharacterById(characterId)

                _uiState.update {
                    it.copy(isLoading = false, character = foundCharacter, hasError = false)
                }
            } else {
                // Error
                _uiState.update {
                    it.copy(isLoading = false, hasError = true)
                }
            }
        }
    }

    fun retry() {
        if (_uiState.value.character != null) {
            loadCharacterProfile(_uiState.value.character!!.id)
        } else {
            loadCharacters()
        }
    }
    fun onLoadingClick() {
        _uiState.update { it.copy(isLoading = false, hasError = true) }
    }
}

private fun getSampleCharacters(): List<Character> {
    return List(10) { i ->
        Character(id = i + 1, name = "Personaje ${i + 1}", status = "Alive", species = "Human", gender = "Unknown", image = "https://rickandmortyapi.com/api/character/avatar/${i + 1}.jpeg")
    }
}

private fun getCharacterById(id: Int): Character {
    return Character(id = id, name = "Personaje $id", status = "Alive", species = "Human", gender = "Unknown", image = "https://rickandmortyapi.com/api/character/avatar/$id.jpeg")
}
