package uvg.plataformas.content.ejerciciosClase.navigationAvanzado.login

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.lab11.data.AppContainer
import com.example.lab11.navigationAvanzado.ui.LoginViewModel
import uvg.plataformas.content.ejerciciosClase.navigationAvanzado.MainRoutes

fun NavGraphBuilder.loginScreen(onLoginClick: () -> Unit,appContainer: AppContainer) {
    composable<MainRoutes.Login> {
        val factory = LoginViewModel.Factory(appContainer.userPreferencesRepository)
        val loginViewModel: LoginViewModel = viewModel(factory = factory)

        LoginScreen(
            viewModel = loginViewModel,
            onLoginClick = onLoginClick,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        )
    }
}