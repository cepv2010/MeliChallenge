package com.camiloparra.melichallenge.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Object of argument type for passing into fragments the parcelize data
 *
 * Created by Camilo Parra on 7/06/2022.
 */
@Parcelize
class ArgItemResult constructor(
    var id: String,
    var siteId: String,
    var title: String,
    var thumbnail: String,
    var price: String,
    var freeShipping: Boolean
) :
    Parcelable