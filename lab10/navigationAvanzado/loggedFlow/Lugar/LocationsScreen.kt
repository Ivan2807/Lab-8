package com.example.lab10.navigationAvanzado.loggedFlow.Lugar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import uvg.plataformas.lab10.uilogin.ErrorLayout
import uvg.plataformas.lab10.uilogin.LoadingLayout

@Composable
fun LocationsScreen(
    onLocationClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LocationsViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when {
        uiState.isLoading -> {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .clickable { viewModel.onLoadingClick() }
            ) {
                LoadingLayout(modifier = modifier)
            }
        }
        uiState.hasError -> {
            ErrorLayout(
                onRetry = { viewModel.loadLocations() },
                modifier = modifier
            )
        }
        else -> {
            LugarContenido(
                locations = uiState.locations,
                onLocationClick = onLocationClick,
                modifier = modifier
            )
        }
    }
}