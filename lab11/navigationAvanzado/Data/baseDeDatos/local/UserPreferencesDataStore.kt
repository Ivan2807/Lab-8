package com.example.lab11.data

import android.content.Context
import kotlin.getValue

class AppContainer(private val context: Context) {

    val userPreferencesRepository: UserPreferencesRepo by lazy {
        UserPreferencesRepo(context)
    }

}
