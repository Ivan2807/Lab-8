package com.example.lab10.navigationAvanzado.loggedFlow

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute // 1. ASEGÚRATE DE TENER ESTA IMPORTACIÓN
import com.example.lab10.navigationAvanzado.loggedFlow.Lugar.LocationsScreen
import com.example.lab10.navigationAvanzado.loggedFlow.Lugar.LocationsViewModel
import com.example.lab10.navigationAvanzado.loggedFlow.LugarPerfil.LocationProfileScreen
import com.example.lab10.navigationAvanzado.loggedFlow.LugarPerfil.LocationProfileViewModel
import com.example.lab10.navigationAvanzado.loggedFlow.personajes.PersonajesPantallas
import com.example.lab10.navigationAvanzado.loggedFlow.personajes.PersonajesViewModel
import com.example.lab10.navigationAvanzado.loggedFlow.PersonajesPerfil.CharacterProfileScreen
import uvg.plataformas.lab10.Lab10Routes
import uvg.plataformas.lab10.loggedFlow.LoggedRoutes

fun NavGraphBuilder.loggedFlowScreen(navController: NavHostController) {
    composable<Lab10Routes.LoggedFlow> {
        LoggedFlowContent(
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun LoggedFlowContent(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination

    Scaffold(
        modifier = modifier,
        topBar = {
            TopBar()
        },
        bottomBar = {
            BottomBar(
                onNavigate = { route ->
                    navController.navigate(route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                currentDestination = currentDestination
            )
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = LoggedRoutes.Characters,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable<LoggedRoutes.Characters> {
                val viewModel: PersonajesViewModel = viewModel()
                PersonajesPantallas(
                    viewModel = viewModel,
                    onCharacterClick = { characterId ->
                        navController.navigate(LoggedRoutes.CharacterProfile(characterId))
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }
            composable<LoggedRoutes.CharacterProfile> { backStackEntry ->
                val characterId = backStackEntry.toRoute<LoggedRoutes.CharacterProfile>().characterId
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry(LoggedRoutes.Characters)
                }
                val viewModel: PersonajesViewModel = viewModel(viewModelStoreOwner = parentEntry)

                CharacterProfileScreen(
                    characterId = characterId,
                    viewModel = viewModel,
                    modifier = Modifier.fillMaxSize()
                )
            }

            composable<LoggedRoutes.Locations> {
                val viewModel: LocationsViewModel = viewModel()
                LocationsScreen(
                    viewModel = viewModel,
                    onLocationClick = { locationId ->
                        navController.navigate(LoggedRoutes.LocationProfile(locationId))
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }

            composable<LoggedRoutes.LocationProfile> {
                val viewModel: LocationProfileViewModel = viewModel()
                LocationProfileScreen(
                    viewModel = viewModel,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}


@Composable
private fun BottomBar(
    onNavigate: (LoggedRoutes) -> Unit,
    currentDestination: NavDestination?,
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer
    ) {
        bottomNavItems.forEach { item ->
            val isSelected = when (item.destination) {
                is LoggedRoutes.Characters ->
                    currentDestination?.route?.startsWith("Characters") == true
                is LoggedRoutes.Locations ->
                    currentDestination?.route?.startsWith("Locations") == true
                else -> false
            }

            NavigationBarItem(
                selected = isSelected,
                onClick = { onNavigate(item.destination) },
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                label = { Text(item.title) }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar() {
    TopAppBar(
        title = {
            Text("Laboratorio 10")
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    )
}


private val bottomNavItems = listOf(
    NavigationEntry.Characters,
    NavigationEntry.Locations,
)

sealed class NavigationEntry(
    var title: String,
    var icon: ImageVector,
    var destination: LoggedRoutes,
) {
    data object Characters : NavigationEntry(
        title = "Personajes",
        icon = Icons.Default.Person,
        destination = LoggedRoutes.Characters,
    )

    data object Locations : NavigationEntry(
        title = "Ubicaciones",
        icon = Icons.Default.LocationOn,
        destination = LoggedRoutes.Locations,
    )
}
