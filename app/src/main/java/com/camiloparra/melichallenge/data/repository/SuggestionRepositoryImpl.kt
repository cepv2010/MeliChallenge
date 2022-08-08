package com.camiloparra.melichallenge.data.repository

import com.camiloparra.melichallenge.domain.adapter.SuggestionAdapter
import com.camiloparra.melichallenge.data.local.entity.SuggestionEntity
import com.camiloparra.melichallenge.data.local.SuggestionDao
import com.camiloparra.melichallenge.domain.model.Suggestion
import com.camiloparra.melichallenge.domain.repository.SuggestionRepository
import javax.inject.Inject

class SuggestionRepositoryImpl @Inject constructor(
    private val suggestionDao: SuggestionDao
) : SuggestionRepository {

    override suspend fun getSuggestion(): List<Suggestion> =
        suggestionDao.getAll().map {
            val adapter = SuggestionAdapter()
            adapter.toModel(it)
        }

    override suspend fun insertSuggestion(suggestion: SuggestionEntity) =
        suggestionDao.insertSuggestion(suggestion)

}