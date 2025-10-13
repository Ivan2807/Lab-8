package com.example.lab10.navigationAvanzado.loggedFlow.personajes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import uvg.plataformas.lab10.uilogin.ErrorLayout
import uvg.plataformas.lab10.uilogin.LoadingLayout

@Composable
fun PersonajesPantallas(
    onCharacterClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PersonajesViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when {
        uiState.isLoading -> {
            LoadingLayout(modifier = modifier)
        }
        uiState.hasError -> {
            ErrorLayout(
                onRetry = { viewModel.loadCharacters() },
                modifier = modifier
            )
        }
        else -> {
            CharactersContent(
                characters = uiState.characters,
                onCharacterClick = onCharacterClick,
                modifier = modifier
            )
        }
    }
}
