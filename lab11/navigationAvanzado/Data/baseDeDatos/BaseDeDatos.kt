package com.example.lab10.navigationAvanzado.Data.baseDeDatos

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
//Ivan Morataya
@Database(
    entities = [CharacterEntity::class, LocationEntity::class],
    version = 1,
    exportSchema = false
)
abstract class Lab10Database : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun locationDao(): LocationDao

    companion object {
        @Volatile
        private var INSTANCE: Lab10Database? = null

        fun getDatabase(context: Context): Lab10Database {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    Lab10Database::class.java,
                    "lab10_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}