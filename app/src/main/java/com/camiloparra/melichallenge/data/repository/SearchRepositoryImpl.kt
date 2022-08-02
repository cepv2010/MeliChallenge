package com.camiloparra.melichallenge.data.repository

import com.camiloparra.melichallenge.data.local.entity.Suggestion
import com.camiloparra.melichallenge.data.local.SuggestionDao
import com.camiloparra.melichallenge.data.network.Api.ItemSearchApi
import com.camiloparra.melichallenge.data.network.AppNetClient
import com.camiloparra.melichallenge.data.network.ResponseHandler
import com.camiloparra.melichallenge.data.network.TypeResponse
import com.camiloparra.melichallenge.data.network.dto.ItemResponse
import com.camiloparra.melichallenge.domain.repository.SearchRepository
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import java.lang.Exception
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val appNetClient: AppNetClient,
    private val responseHandler: ResponseHandler,
    private val suggestionDao: SuggestionDao
) : SearchRepository {

    override suspend fun getSearchResult(query: String, offset: Int): TypeResponse<ItemResponse?> {
        return try {
            val api = appNetClient.getApi(ItemSearchApi::class.java)
            responseHandler.handleSuccess(api.getSearch("MCO", query, offset))
        } catch (e: Exception) {
            Firebase.crashlytics.recordException(e)
            responseHandler.handleError(ItemResponse())
        }
    }

    override suspend fun getSuggestion(): List<Suggestion> = suggestionDao.getAll()

    override suspend fun insertSuggestion(suggestion: Suggestion) =
        suggestionDao.insertSuggestion(suggestion)

}