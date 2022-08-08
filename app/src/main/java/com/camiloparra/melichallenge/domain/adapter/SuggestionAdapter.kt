package com.camiloparra.melichallenge.domain.adapter

import com.camiloparra.melichallenge.data.local.entity.SuggestionEntity
import com.camiloparra.melichallenge.domain.model.Suggestion

class SuggestionAdapter: ModelAdapter<Suggestion, SuggestionEntity> {

    override fun toModel(data: SuggestionEntity): Suggestion {
        return Suggestion(suggest = data.suggest)
    }

    override fun fromModel(model: Suggestion): SuggestionEntity {
        return SuggestionEntity(suggest = model.suggest)
    }
}