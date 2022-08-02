package com.camiloparra.melichallenge.domain.useCase

import com.camiloparra.melichallenge.data.network.Status
import com.camiloparra.melichallenge.data.network.TypeResponse
import com.camiloparra.melichallenge.data.network.dto.ItemResponse
import com.camiloparra.melichallenge.domain.model.Response
import com.camiloparra.melichallenge.domain.repository.SearchRepository
import javax.inject.Inject

/**
 * User case relate to all item or query search
 *
 * Created by Camilo Parra on 7/06/2022.
 */
class ItemSearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend fun getSearchResult(query: String, offset: Int): Response<ItemResponse> {
        val resp = searchRepository.getSearchResult(query, offset)
        return when (resp.status) {
            Status.SUCCESS ->
                Response(status = true, data = resp.data ?: ItemResponse())
            else -> Response(status = true, data = ItemResponse())
        }
    }
}