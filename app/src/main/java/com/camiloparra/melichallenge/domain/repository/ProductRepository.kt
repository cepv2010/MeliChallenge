package com.camiloparra.melichallenge.domain.repository

interface ProductRepository {
    suspend fun getSearchResult(query: String, offset: Int): Result<Any?>
}