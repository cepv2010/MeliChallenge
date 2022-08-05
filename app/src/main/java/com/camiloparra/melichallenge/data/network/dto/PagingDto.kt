package com.camiloparra.melichallenge.data.network.dto

import com.google.gson.annotations.SerializedName

/**
 * Dto Receive from api that represent the paging response
 *
 * Created by Camilo Parra on 7/06/2022.
 */
data class PagingDto(
    var total: Int,
    var primaryResult: Int,
    var offset: Int,
    var limit: Int,
)