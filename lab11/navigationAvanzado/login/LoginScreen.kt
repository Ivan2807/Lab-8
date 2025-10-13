package uvg.plataformas.content.ejerciciosClase.navigationAvanzado.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab11.navigationAvanzado.ui.LoginViewModel

/**
 * Pantalla de Login conectada a un ViewModel.
 *
 * @param viewModel El ViewModel que gestiona el estado y la lógica de negocio.
 * @param onLoginClick La acción de navegación que se ejecuta cuando el login es exitoso.
 */
@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onLoginClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val username by viewModel.username.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bienvenido",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        OutlinedTextField(
            value = username,
            onValueChange = { viewModel.onUsernameChange(it) },
            label = { Text("Ingresa tu nombre") },
            singleLine = true,
            enabled = isLoading
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.login(onLoginClick) },
            enabled = username.isNotBlank() && isLoading
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    strokeWidth = 2.dp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            } else {
                Text("Iniciar Sesión")
            }
        }
    }
}