package com.camiloparra.melichallenge.domain.repository

import com.camiloparra.melichallenge.data.local.entity.SuggestionEntity
import com.camiloparra.melichallenge.data.network.TypeResponse
import com.camiloparra.melichallenge.data.network.dto.ItemResponseDto
import com.camiloparra.melichallenge.domain.model.Suggestion

interface SuggestionRepository {
    suspend fun getSuggestion(): List<Suggestion>
    suspend fun insertSuggestion(suggestion: SuggestionEntity)
}