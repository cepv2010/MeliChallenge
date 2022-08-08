package com.camiloparra.melichallenge.domain.adapter

interface ModelAdapter<Model, Data> {
    fun toModel(data: Data): Model
    fun fromModel(model: Model): Data
}