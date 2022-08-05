package com.camiloparra.melichallenge.data.network.dto

import com.google.gson.annotations.SerializedName

/**
 * Dto Receive from api that represent general response of product items
 *
 * Created by Camilo Parra on 7/06/2022.
 */
data class ItemResponseDto(
    @SerializedName("site_id")
    var siteId: String,
    @SerializedName("country_default_time_zone")
    var dTimeZone: String,
    var query: String = "",
    var paging: PagingDto,
    @SerializedName("results")
    var itemResult: List<ItemResultDto>
)