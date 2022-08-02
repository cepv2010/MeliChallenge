package com.camiloparra.melichallenge.data.network.dto

import com.camiloparra.melichallenge.domain.model.ArgItemResult
import com.google.gson.annotations.SerializedName

/**
 * Dto Receive from api that represent the items result
 *
 * Created by Camilo Parra on 7/06/2022.
 */
class ItemResult {
    lateinit var id: String

    @SerializedName("site_id")
    lateinit var siteId: String
    lateinit var title: String
    lateinit var thumbnail: String
    var price: Int = 0
    lateinit var shipping: Shipping

    fun transformToArgs(nPrice: String, freeShipping: Boolean): ArgItemResult = ArgItemResult(id, siteId, title, thumbnail, nPrice, freeShipping)
}