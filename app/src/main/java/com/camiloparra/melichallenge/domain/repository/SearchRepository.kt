package com.camiloparra.melichallenge.domain.repository

import com.camiloparra.melichallenge.data.local.entity.Suggestion
import com.camiloparra.melichallenge.data.network.TypeResponse
import com.camiloparra.melichallenge.data.network.dto.ItemResponse

interface SearchRepository {

    suspend fun getSearchResult(query: String, offset: Int): TypeResponse<ItemResponse?>
    suspend fun getSuggestion(): List<Suggestion>
    suspend fun insertSuggestion(suggestion: Suggestion)
}