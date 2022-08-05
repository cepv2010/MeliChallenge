package com.camiloparra.melichallenge.ui.itemSearch

import com.camiloparra.melichallenge.domain.model.ItemResult

/**
 * Created by Camilo Parra on 8/06/2022.
 */
interface OnClickListener {
    fun onItemClick(item: ItemResult, position: Int)
}