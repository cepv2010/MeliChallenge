package com.camiloparra.melichallenge.data.network.dto

import com.google.gson.annotations.SerializedName

/**
 * Dto Receive from api that represent the shipping type
 *
 * Created by Camilo Parra on 9/06/2022.
 */
data class ShippingDto(
    @SerializedName("free_shipping")
    var freeShipping: Boolean
)