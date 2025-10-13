// RUTA: app/src/main/java/com/example/lab11/navigation/AppNavigation.kt
package com.example.lab11.navigation

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lab10.navigationAvanzado.loggedFlow.splash.LoggedFlow
import com.example.lab10.navigationAvanzado.loggedFlow.splash.Login
import com.example.lab10.navigationAvanzado.loggedFlow.splash.Splash
import com.example.lab10.navigationAvanzado.loggedFlow.splash.SplashScreen
import com.example.lab10.navigationAvanzado.loggedFlow.splash.SplashViewModel
import com.example.lab11.data.UserPreferencesRepo
import com.example.lab11.navigationAvanzado.login.LoggedFlowScreen
import com.example.lab11.navigationAvanzado.ui.LoginViewModel
import uvg.plataformas.content.ejerciciosClase.navigationAvanzado.login.LoginScreen
import kotlinx.serialization.Serializable


@Serializable
object Splash

@Serializable
object Login

@Serializable
object LoggedFlow
@Composable
fun AppNavigation(userPreferencesRepository: UserPreferencesRepo) {
    Log.d("AppNav", "AppNavigation: Composable ha sido llamado. Configurando NavHost.")
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Splash
    ) {
        composable<Splash> {
            Log.d("AppNav", "NavHost: Mostrando la ruta Splash.")
            val factory = SplashViewModel.Factory(userPreferencesRepository)
            val splashViewModel: SplashViewModel = viewModel(factory = factory)

            SplashScreen(
                viewModel = splashViewModel,

                onNavigateToLogin = {
                    navController.navigate(Login) {
                        popUpTo(Splash) { inclusive = true }
                    }
                },
                onNavigateToHome = {
                    navController.navigate(LoggedFlow) {
                        popUpTo(Splash) { inclusive = true }
                    }
                }
            )
        }

        composable<Login> {
            val factory = LoginViewModel.Factory(userPreferencesRepository)
            val loginViewModel: LoginViewModel = viewModel(factory = factory)

            LoginScreen(
                viewModel = loginViewModel,
                onLoginClick = {
                    navController.navigate(LoggedFlow) {
                        popUpTo(Login) { inclusive = true }
                    }
                },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            )
        }

        composable<LoggedFlow> {
            LoggedFlowScreen(
                userPreferencesRepository = userPreferencesRepository,
                onLogout = {
                    navController.navigate(Login) {
                        popUpTo(LoggedFlow) { inclusive = true }
                    }
                }
            )
        }
    }
}