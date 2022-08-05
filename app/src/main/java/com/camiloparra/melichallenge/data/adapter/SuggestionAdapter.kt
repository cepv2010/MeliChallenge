package com.camiloparra.melichallenge.data.adapter

import com.camiloparra.melichallenge.data.local.entity.SuggestionEntity
import com.camiloparra.melichallenge.domain.model.Suggestion

class SuggestionAdapter<T> (private val input: T): ObjectTransform {

    override fun toData(): Any {
        val cast = input as Suggestion
        return SuggestionEntity(suggest = cast.suggest)
    }

    override fun toModel(): Any {
        val cast = input as SuggestionEntity
        return Suggestion(suggest = cast.suggest)
    }
}