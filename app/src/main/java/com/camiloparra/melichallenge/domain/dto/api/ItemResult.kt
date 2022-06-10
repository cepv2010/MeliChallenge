package com.camiloparra.melichallenge.domain.dto.api

import android.os.Parcelable
import com.camiloparra.melichallenge.domain.dto.ArgItemResult
import com.camiloparra.melichallenge.util.Utils
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

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