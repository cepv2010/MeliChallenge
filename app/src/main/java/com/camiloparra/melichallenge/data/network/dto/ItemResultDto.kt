package com.camiloparra.melichallenge.data.network.dto

import com.camiloparra.melichallenge.domain.model.ArgItemResult
import com.google.gson.annotations.SerializedName

/**
 * Dto Receive from api that represent the items result
 *
 * Created by Camilo Parra on 7/06/2022.
 */
data class ItemResultDto(
    var id: String,
    @SerializedName("site_id")
    var siteId: String,
    var title: String,
    var thumbnail: String,
    var price: Int,
    var shipping: ShippingDto
) {
    fun transformToArgs(nPrice: String, freeShipping: Boolean): ArgItemResult =
        ArgItemResult(id, siteId, title, thumbnail, nPrice, freeShipping)
}