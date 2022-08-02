package com.camiloparra.melichallenge.domain.model

data class Response<T> (
    var status: Boolean,
    var data: T,
    var listData: List<T> = listOf()
)