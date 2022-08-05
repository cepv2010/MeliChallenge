package com.camiloparra.melichallenge.data.adapter

import com.camiloparra.melichallenge.data.network.dto.ItemResponseDto
import com.camiloparra.melichallenge.data.network.dto.ItemResultDto
import com.camiloparra.melichallenge.data.network.dto.PagingDto
import com.camiloparra.melichallenge.data.network.dto.ShippingDto
import com.camiloparra.melichallenge.domain.model.ItemResult
import com.camiloparra.melichallenge.domain.model.Paging
import com.camiloparra.melichallenge.domain.model.ProductDetail
import com.camiloparra.melichallenge.util.Utils

class ProductAdapter<T>(val input: T) : ObjectTransform {

    override fun toData(): Any {
        val cast = input as ProductDetail
        val paging = PagingDto(
            total = cast.paging.total,
            offset = cast.paging.offset,
            limit = cast.paging.limit,
            primaryResult = cast.paging.primaryResult
        )
        val itemResult = cast.itemResult.map {
            ItemResultDto(
                id = it.id,
                siteId = it.siteId,
                title = it.title,
                thumbnail = it.thumbnail,
                price = it.price,
                shipping = ShippingDto(it.freeShipping)
            )
        }
        return ItemResponseDto(
            siteId = cast.siteId,
            dTimeZone = cast.dTimeZone,
            paging = paging,
            itemResult = itemResult
        )
    }

    override fun toModel(): Any {
        val util = Utils()
        val cast = input as ItemResponseDto
        val paging = Paging(
            total = cast.paging.total,
            offset = cast.paging.offset,
            limit = cast.paging.limit,
            primaryResult = cast.paging.primaryResult
        )
        val itemResult = cast.itemResult.map {
            ItemResult(
                id = it.id,
                siteId = it.siteId,
                title = it.title,
                thumbnail = it.thumbnail,
                price = it.price,
                priceFormatted = util.setFormatPrice(it.price),
                freeShipping = it.shipping.freeShipping
            )
        }
        return ProductDetail(
            siteId = cast.siteId,
            dTimeZone = cast.dTimeZone,
            paging = paging,
            itemResult = itemResult
        )
    }
}