package uvg.plataformas.content.ejerciciosClase.navigationAvanzado

import kotlinx.serialization.Serializable

sealed interface MainRoutes {
    @Serializable
    data object Login: MainRoutes

    @Serializable
    data object LoggedFlow: MainRoutes
}