package com.example.lab10.navigationAvanzado.Data.baseDeDatos

import androidx.room.Entity
import androidx.room.PrimaryKey
import uvg.plataformas.lab10.data.Lugar

@Entity(tableName = "locations")
data class LocationEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val type: String,
    val dimension: String
)

// Funciones de mapeo
fun LocationEntity.toLugar() = Lugar(
    id = id,
    name = name,
    type = type,
    dimension = dimension
)

fun Lugar.toEntity() = LocationEntity(
    id = id,
    name = name,
    type = type,
    dimension = dimension
)