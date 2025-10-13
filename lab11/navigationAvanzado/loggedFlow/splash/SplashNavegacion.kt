package com.example.lab10.navigationAvanzado.loggedFlow.splash

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.lab11.data.UserPreferencesRepo
import com.example.lab11.navigationAvanzado.loggedFlow.splash.SplashViewModelFactory
import uvg.plataformas.lab10.Lab10Routes

fun NavGraphBuilder.splashScreen(
    onNavigateToLogin: () -> Unit,
    onNavigateToHome: () -> Unit,
) {
    composable<Lab10Routes.Splash> {
        val context = LocalContext.current
        val viewModel: SplashViewModel = viewModel(
            factory = SplashViewModelFactory(UserPreferencesRepo(context))
        )
        SplashScreen(
            onNavigateToLogin = onNavigateToLogin,
            onNavigateToHome = onNavigateToHome,
            viewModel = viewModel,
        )
    }
}