package com.camiloparra.melichallenge.data.network.Api

import com.camiloparra.melichallenge.data.network.dto.ItemResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Camilo Parra on 7/06/2022.
 */
interface ItemSearchApi {

    @GET("/sites/{SITE_ID}/search")
    suspend fun getSearch(
        @Path("SITE_ID") siteId: String,
        @Query("q") query: String,
        @Query("offset") offset: Int
    ): ItemResponseDto

}