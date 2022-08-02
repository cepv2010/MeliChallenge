package com.camiloparra.melichallenge.domain.useCase

import com.camiloparra.melichallenge.data.local.SuggestionDao
import com.camiloparra.melichallenge.data.local.entity.Suggestion
import com.camiloparra.melichallenge.domain.repository.SearchRepository
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Use case related to manage all suggestions
 *
 * Created by Camilo Parra on 7/06/2022.
 */
@Singleton
class SuggestionUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {

    suspend fun getSuggestion(): List<Suggestion> = searchRepository.getSuggestion()

    suspend fun insertSuggestion(suggestion: Suggestion) =
        searchRepository.insertSuggestion(suggestion)
}