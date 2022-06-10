package com.camiloparra.melichallenge.data.comm

/**
 * Created by Camilo Parra on 7/06/2022.
 */
class TypeResponse<out T>(val status: Status, val data: T?) {
    companion object {
        fun <T> success(data: T?): TypeResponse<T> {
            return TypeResponse(Status.SUCCESS, data)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}