package uvg.plataformas.lab10

import kotlinx.serialization.Serializable

sealed interface Lab10Routes {
    @Serializable
    data object Login: Lab10Routes

    @Serializable
    data object LoggedFlow: Lab10Routes
    @Serializable
    data object Splash : Lab10Routes

}