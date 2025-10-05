package uvg.plataformas.lab10.loggedFlow

import kotlinx.serialization.Serializable

sealed interface LoggedRoutes {
    @Serializable
    data object Characters : LoggedRoutes

    @Serializable
    data class CharacterProfile(val characterId: Int) : LoggedRoutes

    @Serializable
    data object Locations : LoggedRoutes

    @Serializable
    data class LocationProfile(val locationId: Int) : LoggedRoutes
}