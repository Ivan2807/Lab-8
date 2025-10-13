package com.example.lab10.navigationAvanzado.loggedFlow.LugarPerfil

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import uvg.plataformas.lab10.data.Lugar
import uvg.plataformas.lab10.uilogin.ErrorLayout
import uvg.plataformas.lab10.uilogin.LoadingLayout

@Composable
fun LocationProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: LocationProfileViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when {
        uiState.isLoading -> {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .clickable { viewModel.onLoadingClick() }
            ) {
                LoadingLayout()
            }
        }
        uiState.hasError -> {
            ErrorLayout(
                onRetry = { viewModel.retry() },
                modifier = modifier
            )
        }
        else -> {
            uiState.location?.let { location ->
                LocationProfileContent(
                    location = location,
                    modifier = modifier
                )
            }
        }
    }
}

@Composable
fun LocationProfileContent(
    location: Lugar,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = location.name,
            style = MaterialTheme.typography.headlineMedium
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                InfoRow(label = "Tipo", value = location.type)
                InfoRow(label = "Dimensión", value = location.dimension)
            }
        }
    }
}

@Composable
fun InfoRow(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}