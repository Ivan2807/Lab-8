package com.example.lab10.navigationAvanzado.loggedFlow.PersonajesPerfil

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.lab10.navigationAvanzado.loggedFlow.personajes.PersonajesViewModel
import uvg.plataformas.lab10.data.Character
import uvg.plataformas.lab10.uilogin.ErrorLayout
import uvg.plataformas.lab10.uilogin.LoadingLayout

@Composable
fun CharacterProfileScreen(
    characterId: Int,
    modifier: Modifier = Modifier,
    viewModel: PersonajesViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(characterId) {
        viewModel.loadCharacterProfile(characterId)
    }

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
            uiState.character?.let { character ->
                CharacterProfileContent(
                    character = character,
                    modifier = modifier
                )
            }
        }
    }
}

@Composable
fun CharacterProfileContent(
    character: Character,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AsyncImage(
            model = character.image,
            contentDescription = character.name,
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Text(
            text = character.name,
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
                CharacterInfoRow(label = "Status", value = character.status)
                CharacterInfoRow(label = "Species", value = character.species)
                CharacterInfoRow(label = "Gender", value = character.gender)
            }
        }
    }
}

@Composable
fun CharacterInfoRow(
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