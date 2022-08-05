package com.camiloparra.melichallenge.data.adapter

interface ObjectTransform {
    fun toData(): Any
    fun toModel(): Any
}