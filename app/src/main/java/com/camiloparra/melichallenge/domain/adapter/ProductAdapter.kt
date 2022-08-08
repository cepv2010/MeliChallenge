package com.camiloparra.melichallenge.domain.adapter

import com.camiloparra.melichallenge.data.network.dto.ItemResponseDto
import com.camiloparra.melichallenge.data.network.dto.ItemResultDto
import com.camiloparra.melichallenge.data.network.dto.PagingDto
import com.camiloparra.melichallenge.data.network.dto.ShippingDto
import com.camiloparra.melichallenge.domain.model.ItemResult
import com.camiloparra.melichallenge.domain.model.Paging
import com.camiloparra.melichallenge.domain.model.ProductDetail
import com.camiloparra.melichallenge.util.Utils

class ProductAdapter: ModelAdapter<ProductDetail, ItemResponseDto> {

    override fun toModel(data: ItemResponseDto): ProductDetail {
        val util = Utils()
        val paging = Paging(
            total = data.paging.total,
            offset = data.paging.offset,
            limit = data.paging.limit,
            primaryResult = data.paging.primaryResult
        )
        val itemResult = data.itemResult.map {
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
            siteId = data.siteId,
            dTimeZone = data.dTimeZone,
            paging = paging,
            itemResult = itemResult
        )
    }

    override fun fromModel(model: ProductDetail): ItemResponseDto {
        val paging = PagingDto(
            total = model.paging.total,
            offset = model.paging.offset,
            limit = model.paging.limit,
            primaryResult = model.paging.primaryResult
        )
        val itemResult = model.itemResult.map {
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
            siteId = model.siteId,
            dTimeZone = model.dTimeZone,
            paging = paging,
            itemResult = itemResult
        )
    }
}