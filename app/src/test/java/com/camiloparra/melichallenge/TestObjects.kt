package com.camiloparra.melichallenge

import com.camiloparra.melichallenge.domain.dto.api.ItemResponse
import com.camiloparra.melichallenge.domain.dto.api.ItemResult
import com.camiloparra.melichallenge.domain.dto.api.Paging
import com.camiloparra.melichallenge.domain.entity.Suggestion

/**
 * Created by Camilo Parra on 9/06/2022.
 */
class TestObjects {

    companion object {
        const val EXAMPLE_QUERY = "Computador"
        const val EXAMPLE_OFFSET = 0
        const val SITE_ID = "MCO"

        fun getListItemResponse(): ItemResponse {
            val firstResult = ItemResult()
            firstResult.id = "MCO845591032"
            firstResult.title = "Pc Torre Gamer Core I7 3770 8gb Ram Tarjeta Video R5 340 2gb"
            firstResult.siteId = SITE_ID
            firstResult.thumbnail = "http://http2.mlstatic.com/D_849233-MCO46392572591_062021-O.jpg"

            val secondResult = ItemResult()
            secondResult.id = "MCO867181759"
            secondResult.title = "Computador Portatil Huawei 15.6  Pulgadas Matebook D - Intel"
            secondResult.siteId = SITE_ID
            secondResult.thumbnail = "http://http2.mlstatic.com/D_849233-MCO46392572591_062021-O.jpg"
            val listResult = listOf<ItemResult>(firstResult, secondResult)

            val paging = Paging()
            paging.total = 517
            paging.primaryResult = 517
            paging.offset = 0
            paging.limit = 50

            val itemResponse = ItemResponse()
            itemResponse.siteId = SITE_ID
            itemResponse.dTimeZone = "GMT-05:00"
            itemResponse.query = EXAMPLE_QUERY
            itemResponse.paging = paging
            itemResponse.itemResult = listResult
            return itemResponse
        }

        fun getSuggestion(): List<Suggestion> {
            val firstSuggestion = Suggestion()
            firstSuggestion.suggest = "-"
            val secondSuggestion = Suggestion()
            secondSuggestion.suggest = "-"
            return listOf(firstSuggestion, secondSuggestion)
        }
    }
}