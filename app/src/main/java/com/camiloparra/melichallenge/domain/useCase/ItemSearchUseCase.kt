package com.camiloparra.melichallenge.domain.useCase

import androidx.lifecycle.LiveData
import com.camiloparra.melichallenge.domain.dto.api.ItemResponse
import com.camiloparra.melichallenge.domain.entity.Suggestion

/**
 * User case relate to all item or query search
 *
 * Created by Camilo Parra on 7/06/2022.
 */
interface ItemSearchUseCase {

    /**
     * Search method that return multiple result according to the query sent
     * @param query query to search
     * @param offset section to search
     * @return
     */
    suspend fun getSearchResult(query: String, offset: Int): Pair<ItemResponse, Boolean>

}