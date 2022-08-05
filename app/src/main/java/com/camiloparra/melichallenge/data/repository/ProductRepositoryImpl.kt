package com.camiloparra.melichallenge.data.repository

import com.camiloparra.melichallenge.data.adapter.ProductAdapter
import com.camiloparra.melichallenge.data.network.Api.ItemSearchApi
import com.camiloparra.melichallenge.data.network.AppNetClient
import com.camiloparra.melichallenge.data.network.ResponseHandler
import com.camiloparra.melichallenge.domain.repository.ProductRepository
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import java.lang.Exception
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val appNetClient: AppNetClient,
    private val responseHandler: ResponseHandler
) : ProductRepository {

    override suspend fun getSearchResult(query: String, offset: Int): Result<Any> {
        return try {
            val api = appNetClient.getApi(ItemSearchApi::class.java)
            val resp = responseHandler.handleSuccess(api.getSearch("MCO", query, offset))
            val adapter = ProductAdapter(resp.getOrNull())
            Result.success(adapter.toModel())
        } catch (e: Exception) {
            Firebase.crashlytics.recordException(e)
            responseHandler.handleError()
        }
    }
}