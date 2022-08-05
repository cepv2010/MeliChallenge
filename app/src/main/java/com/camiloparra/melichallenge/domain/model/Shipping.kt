package com.camiloparra.melichallenge.domain.model

import com.google.gson.annotations.SerializedName

/**
 * Dto Receive from api that represent the shipping type
 *
 * Created by Camilo Parra on 9/06/2022.
 */
data class Shipping(
    @SerializedName("free_shipping")
    var freeShipping: Boolean
)