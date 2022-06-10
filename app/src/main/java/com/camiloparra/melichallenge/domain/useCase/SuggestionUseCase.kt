package com.camiloparra.melichallenge.domain.useCase

import androidx.lifecycle.LiveData
import com.camiloparra.melichallenge.domain.entity.Suggestion

/**
 * Use case related to manage all suggestions
 *
 * Created by Camilo Parra on 7/06/2022.
 */
interface SuggestionUseCase {

    /**
     * Method that return all suggestions saved on the local database
     * @return List of suggestions
     */
    suspend fun getSuggestion(): List<Suggestion>

    /**
     * Method that insert a new suggestion
     */
    suspend fun insertSuggestion(suggestion: Suggestion)

}