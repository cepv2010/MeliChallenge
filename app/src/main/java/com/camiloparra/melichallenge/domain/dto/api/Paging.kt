package com.camiloparra.melichallenge.domain.dto.api

import com.google.gson.annotations.SerializedName

/**
 * Dto Receive from api that represent the paging response
 *
 * Created by Camilo Parra on 7/06/2022.
 */
class Paging {
    var total: Int = 0
    @SerializedName("primary_results")
    var primaryResult: Int = 0
    var offset: Int = 0
    var limit: Int = 0
}