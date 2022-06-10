package com.camiloparra.melichallenge.domain.useCase

import androidx.lifecycle.LiveData
import androidx.room.Transaction
import com.camiloparra.melichallenge.data.comm.Api.ItemSearchApi
import com.camiloparra.melichallenge.data.comm.BaseDataComm
import com.camiloparra.melichallenge.data.comm.ResponseHandler
import com.camiloparra.melichallenge.data.repository.SuggestionRepository
import com.camiloparra.melichallenge.domain.dto.api.ItemResponse
import com.camiloparra.melichallenge.domain.entity.Suggestion
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import java.lang.Exception
import javax.inject.Inject

/**
 * Created by Camilo Parra on 7/06/2022.
 */
class ItemSearchUseCaseImpl @Inject constructor(
    private val baseDataComm: BaseDataComm,
    private val responseHandler: ResponseHandler,
) : ItemSearchUseCase {
    override suspend fun getSearchResult(query: String, offset: Int): Pair<ItemResponse, Boolean> {
        return try {
            val api = baseDataComm.getApi(ItemSearchApi::class.java)
            val call = responseHandler.handleSuccess(api.getSearch("MCO", query, offset))
            Pair(call.data!!, true)
        } catch (e: Exception) {
            Firebase.crashlytics.recordException(e)
            Pair(ItemResponse(), false)
        }
    }


}