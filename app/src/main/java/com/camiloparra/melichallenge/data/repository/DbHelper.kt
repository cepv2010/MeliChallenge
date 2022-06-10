package com.camiloparra.melichallenge.data.repository

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.camiloparra.melichallenge.domain.entity.Suggestion
import javax.inject.Inject

/**
 * Created by Camilo Parra on 10/06/2022.
 */
@Database(
    entities = [Suggestion::class],
    version = 1
)
abstract class DbHelper: RoomDatabase() {
    abstract fun suggestionDao(): SuggestionRepository
}