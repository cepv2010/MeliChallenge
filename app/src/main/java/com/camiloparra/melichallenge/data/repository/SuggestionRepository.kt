package com.camiloparra.melichallenge.data.repository

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.camiloparra.melichallenge.domain.entity.Suggestion

/**
 * Created by Camilo Parra on 10/06/2022.
 */
@Dao
interface SuggestionRepository {
    companion object {
        const val MAX_ROW = 10
    }

    @Query("SELECT * FROM suggestion ORDER BY id DESC")
    fun getAll(): List<Suggestion>

    @Insert
    fun insert(vararg suggestion: Suggestion)

    @Query("DELETE FROM suggestion WHERE id IN (SELECT id FROM Suggestion LIMIT 1)")
    fun deleteMinRow()

    @Query("SELECT COUNT(0) FROM Suggestion")
    fun getCount(): Int

    @Transaction
    fun insertSuggestion(suggestion: Suggestion){
        val count = getCount()
        if (count == MAX_ROW) {
            deleteMinRow()
        }
        insert(suggestion)
    }
}