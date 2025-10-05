package uvg.plataformas.lab10

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import uvg.plataformas.content.ejerciciosClase.navigationAvanzado.login.loginScreen
import com.example.lab10.navigationAvanzado.loggedFlow.loggedFlowScreen
import uvg.plataformas.content.ui.theme.ContentTheme

@Composable
fun Lab10Navigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Lab10Routes.Login,
        modifier = modifier
    ) {
        loginScreen(
            onLoginClick = {
                navController.navigate(Lab10Routes.LoggedFlow)
            }
        )

        loggedFlowScreen()
    }
}

@Preview
@Composable
fun Lab10NavigationApp() {
    ContentTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Lab10Navigation(
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}