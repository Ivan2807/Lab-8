package com.example.lab10.navigationAvanzado.loggedFlow.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.collectAsState

@Composable
fun SplashScreen(
    onNavigateToLogin: () -> Unit,
    onNavigateToHome: () -> Unit,
    viewModel: SplashViewModel
) {
    val userNameState by viewModel.userName.collectAsState(initial = Unit)
    LaunchedEffect(key1 = userNameState) {
        if (userNameState !is Unit) {
            if (userNameState == null) {
                onNavigateToLogin()
            } else {
                onNavigateToHome()
            }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}