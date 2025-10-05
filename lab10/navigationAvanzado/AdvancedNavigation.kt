package uvg.plataformas.content.ejerciciosClase.navigationAvanzado

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import uvg.plataformas.content.ejerciciosClase.navigationAvanzado.login.loginScreen
import uvg.plataformas.content.ui.theme.ContentTheme
import com.example.lab10.navigationAvanzado.loggedFlow.loggedFlowScreen


@Composable
fun AdvancedNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = MainRoutes.Login,
        modifier = modifier
    ) {
        loginScreen(
            onLoginClick = {
                navController.navigate(MainRoutes.LoggedFlow)
            }
        )
        
        loggedFlowScreen()
    }
}

@Preview
@Composable
fun AdvancedNavigationApp() {
    ContentTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            AdvancedNavigation(
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}