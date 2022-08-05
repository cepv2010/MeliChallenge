package com.camiloparra.melichallenge

import com.camiloparra.melichallenge.data.network.dto.ItemResponseDto
import com.camiloparra.melichallenge.data.network.dto.ItemResultDto
import com.camiloparra.melichallenge.data.network.dto.PagingDto
import com.camiloparra.melichallenge.data.local.entity.SuggestionEntity

/**
 * Created by Camilo Parra on 9/06/2022.
 */
class TestObjects {

    companion object {
        const val EXAMPLE_QUERY = "Computador"
        const val EXAMPLE_OFFSET = 0
        const val SITE_ID = "MCO"

        fun getListItemResponse(): ItemResponseDto {
            val firstResult = ItemResultDto()
            firstResult.id = "MCO845591032"
            firstResult.title = "Pc Torre Gamer Core I7 3770 8gb Ram Tarjeta Video R5 340 2gb"
            firstResult.siteId = SITE_ID
            firstResult.thumbnail = "http://http2.mlstatic.com/D_849233-MCO46392572591_062021-O.jpg"

            val secondResult = ItemResultDto()
            secondResult.id = "MCO867181759"
            secondResult.title = "Computador Portatil Huawei 15.6  Pulgadas Matebook D - Intel"
            secondResult.siteId = SITE_ID
            secondResult.thumbnail = "http://http2.mlstatic.com/D_849233-MCO46392572591_062021-O.jpg"
            val listResult = listOf<ItemResultDto>(firstResult, secondResult)

            val paging = PagingDto()
            paging.total = 517
            paging.primaryResult = 517
            paging.offset = 0
            paging.limit = 50

            val itemResponse = ItemResponseDto()
            itemResponse.siteId = SITE_ID
            itemResponse.dTimeZone = "GMT-05:00"
            itemResponse.query = EXAMPLE_QUERY
            itemResponse.paging = paging
            itemResponse.itemResult = listResult
            return itemResponse
        }

        fun getSuggestion(): List<SuggestionEntity> {
            val firstSuggestion = SuggestionEntity()
            firstSuggestion.suggest = "-"
            val secondSuggestion = SuggestionEntity()
            secondSuggestion.suggest = "-"
            return listOf(firstSuggestion, secondSuggestion)
        }
    }
}