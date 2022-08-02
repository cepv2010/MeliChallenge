package com.camiloparra.melichallenge.data.network.dto

import com.google.gson.annotations.SerializedName

/**
 * Dto Receive from api that represent general response of product items
 *
 * Created by Camilo Parra on 7/06/2022.
 */
class ItemResponse {
    @SerializedName("site_id")
    lateinit var siteId: String
    @SerializedName("country_default_time_zone")
    lateinit var dTimeZone: String
    lateinit var query: String
    lateinit var paging: Paging
    @SerializedName("results")
    lateinit var itemResult: List<ItemResult>
}