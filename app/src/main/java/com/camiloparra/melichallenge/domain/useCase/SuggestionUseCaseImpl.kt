package com.camiloparra.melichallenge.domain.useCase

import android.util.Log
import androidx.lifecycle.LiveData
import com.camiloparra.melichallenge.data.comm.Api.CategoryApi
import com.camiloparra.melichallenge.data.comm.BaseDataComm
import com.camiloparra.melichallenge.data.comm.ResponseHandler
import com.camiloparra.melichallenge.data.repository.SuggestionRepository
import com.camiloparra.melichallenge.domain.entity.Suggestion
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by Camilo Parra on 7/06/2022.
 */
@Singleton
class SuggestionUseCaseImpl @Inject constructor(
    private val suggestionRepository: SuggestionRepository
) : SuggestionUseCase {

    override suspend fun getSuggestion(): List<Suggestion> = suggestionRepository.getAll()

    override suspend fun insertSuggestion(suggestion: Suggestion) = suggestionRepository.insertSuggestion(suggestion)
}