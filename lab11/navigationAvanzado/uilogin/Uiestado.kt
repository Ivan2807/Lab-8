package uvg.plataformas.lab10.data

data class LocationProfileUiState(
    val isLoading: Boolean = false,
    val location: Lugar? = null,
    val hasError: Boolean = false
)

data class LocationsListUiState(
    val isLoading: Boolean = false,
    val locations: List<Lugar> = emptyList(),
    val hasError: Boolean = false
)

data class CharacterProfileUiState(
    val isLoading: Boolean = false,
    val character: Character? = null,
    val hasError: Boolean = false
)

data class CharactersListUiState(
    val isLoading: Boolean = false,
    val characters: List<Character> = emptyList(),
    val hasError: Boolean = false
)