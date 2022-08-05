package com.camiloparra.melichallenge.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.camiloparra.melichallenge.data.local.entity.SuggestionEntity

/**
 * Created by Camilo Parra on 10/06/2022.
 */
@Database(
    entities = [SuggestionEntity::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun suggestionDao(): SuggestionDao
}