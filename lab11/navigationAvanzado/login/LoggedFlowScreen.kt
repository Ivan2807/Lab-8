package com.example.lab11.navigationAvanzado.login


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lab11.data.UserPreferencesRepo


/**
 *
 * @param appContainer El contenedor de dependencias para pasarlo a los ViewModels internos.
 * @param onLogout La acción a ejecutar cuando el usuario cierra sesión.
 */
@Composable
fun LoggedFlowScreen(
    userPreferencesRepository: UserPreferencesRepo,
    onLogout: () -> Unit
) {
    val loggedNavController = rememberNavController()
    Scaffold(
    ) { innerPadding ->
        NavHost(
            navController = loggedNavController,
            startDestination = "character_list",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("character_list") {
                Text("Pantalla de Personajes")
        }
            composable("profile") {
                Text("Perfil")
            }
    }
}
}