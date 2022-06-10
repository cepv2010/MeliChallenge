package com.camiloparra.melichallenge.data.comm

import android.content.Context
import com.camiloparra.melichallenge.R
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Camilo Parra on 7/06/2022.
 */
@Singleton
open class ResponseHandler @Inject constructor(var context: Context){
    fun <T : Any> handleSuccess(data: T): TypeResponse<T> {
        return TypeResponse.success(data)
    }
}