package com.camiloparra.melichallenge.data.comm.Api

import com.camiloparra.melichallenge.domain.dto.api.Category
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Camilo Parra on 7/06/2022.
 */
interface CategoryApi {

    @GET("/sites/{SITE_ID}/categories")
    suspend fun getCategories(@Path("SITE_ID") siteId: String): List<Category>



}