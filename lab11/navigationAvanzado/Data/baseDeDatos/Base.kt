package com.example.lab10.navigationAvanzado.Data.baseDeDatos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "personajes")
data class Personajes(
    @PrimaryKey val id: Int,
    val nombre: String,
    val estado: String,
    val especie: String,
    val genero: String,
    val imagen: String
)

@Entity(tableName = "lugar")
data class Lugar(
    @PrimaryKey val id: Int,
    val nombre: String,
    val tipo: String,
    val dimension: String
)