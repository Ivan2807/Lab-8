package uvg.plataformas.content.ejerciciosClase.navigationAvanzado.login

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import uvg.plataformas.content.ejerciciosClase.navigationAvanzado.MainRoutes

fun NavGraphBuilder.loginScreen(onLoginClick: () -> Unit) {
    composable<MainRoutes.Login> {
        LoginScreen(
            onLoginClick = onLoginClick,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        )
    }
}