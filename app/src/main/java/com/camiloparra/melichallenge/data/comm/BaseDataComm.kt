package com.camiloparra.melichallenge.data.comm

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

/**
 * Created by Camilo Parra on 7/06/2022.
 */
class BaseDataComm @Inject constructor(var context: Context) {

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.mercadolibre.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(makeInterceptor())
        .build()

    fun <T> getApi(clazz: Class<T>): T {
        return retrofit.create(clazz)
    }

    private fun makeInterceptor(): OkHttpClient {
        val logger = HttpLoggingInterceptor()
        logger.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
    }

}