package com.camiloparra.melichallenge.domain.model

import com.camiloparra.melichallenge.data.network.dto.ShippingDto
import com.google.gson.annotations.SerializedName

/**
 * Dto Receive from api that represent the items result
 *
 * Created by Camilo Parra on 7/06/2022.
 */
data class ItemResult(
    var id: String,
    var siteId: String,
    var title: String,
    var thumbnail: String,
    var priceFormatted: String,
    var freeShipping: Boolean,
    var price: Int,
) {
    fun transformToArgs(): ArgItemResult =
        ArgItemResult(id, siteId, title, thumbnail, priceFormatted, freeShipping)
}