package com.camiloparra.melichallenge.data.network

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Camilo Parra on 7/06/2022.
 */
@Singleton
open class ResponseHandler @Inject constructor(var context: Context){
    fun <T : Any> handleSuccess(data: T?): TypeResponse<T?> {
        return TypeResponse.success(data)
    }

    fun <T : Any> handleError(data: T?): TypeResponse<T?> {
        return TypeResponse.error(data)
    }
}