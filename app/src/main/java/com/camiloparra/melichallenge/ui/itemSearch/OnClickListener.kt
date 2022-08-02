package com.camiloparra.melichallenge.ui.itemSearch

import com.camiloparra.melichallenge.data.network.dto.ItemResult

/**
 * Created by Camilo Parra on 8/06/2022.
 */
interface OnClickListener {
    fun onItemClick(item: ItemResult, position: Int)
}