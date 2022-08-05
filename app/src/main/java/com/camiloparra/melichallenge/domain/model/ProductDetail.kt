package com.camiloparra.melichallenge.domain.model

import com.google.gson.annotations.SerializedName

/**
 * Dto Receive from api that represent general response of product items
 *
 * Created by Camilo Parra on 7/06/2022.
 */
data class ProductDetail(
    var siteId: String,
    var dTimeZone: String,
    var paging: Paging,
    var itemResult: List<ItemResult>
)