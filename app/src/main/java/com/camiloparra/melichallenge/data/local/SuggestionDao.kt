package com.camiloparra.melichallenge.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.camiloparra.melichallenge.data.local.entity.SuggestionEntity

/**
 * Created by Camilo Parra on 10/06/2022.
 */
@Dao
interface SuggestionDao {
    companion object {
        const val MAX_ROW = 10
    }

    @Query("SELECT * FROM SuggestionEntity ORDER BY id DESC")
    fun getAll(): List<SuggestionEntity>

    @Insert
    fun insert(vararg suggestionEntity: SuggestionEntity)

    @Query("DELETE FROM SuggestionEntity WHERE id IN (SELECT id FROM SuggestionEntity LIMIT 1)")
    fun deleteMinRow()

    @Query("SELECT COUNT(0) FROM SuggestionEntity")
    fun getCount(): Int

    @Transaction
    fun insertSuggestion(suggestionEntity: SuggestionEntity){
        val count = getCount()
        if (count == MAX_ROW) {
            deleteMinRow()
        }
        insert(suggestionEntity)
    }
}