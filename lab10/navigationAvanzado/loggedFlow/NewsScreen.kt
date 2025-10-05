package uvg.plataformas.content.ejerciciosClase.navigationAvanzado.loggedFlow

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun NewsScreen(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
    ) {
        Text(
            text = "News",
            modifier = Modifier.align(Alignment.Center),
            style = MaterialTheme.typography.headlineMedium
        )
    }
}