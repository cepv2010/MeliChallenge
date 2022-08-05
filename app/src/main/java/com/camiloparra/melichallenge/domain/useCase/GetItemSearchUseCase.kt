package com.camiloparra.melichallenge.domain.useCase

import com.camiloparra.melichallenge.domain.repository.ProductRepository
import javax.inject.Inject

/**
 * User case relate to all item or query search
 *
 * Created by Camilo Parra on 7/06/2022.
 */
class GetItemSearchUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend fun getSearchResult(query: String, offset: Int): Result<Any?> =
        productRepository.getSearchResult(query, offset)
}