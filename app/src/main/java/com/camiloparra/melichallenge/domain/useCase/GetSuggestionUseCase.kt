package com.camiloparra.melichallenge.domain.useCase

import com.camiloparra.melichallenge.domain.model.Suggestion
import com.camiloparra.melichallenge.domain.repository.ProductRepository
import com.camiloparra.melichallenge.domain.repository.SuggestionRepository
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Use case related to manage all suggestions
 *
 * Created by Camilo Parra on 7/06/2022.
 */
@Singleton
class GetSuggestionUseCase @Inject constructor(
    private val suggestionRepository: SuggestionRepository
) {
    suspend fun getSuggestion(): List<Suggestion> = suggestionRepository.getSuggestion()
}