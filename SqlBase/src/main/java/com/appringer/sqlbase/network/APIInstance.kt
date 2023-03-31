package com.appringer.sqlbase.network

import com.appringer.sqlbase.config.SqlBaseConfig
import com.appringer.sqlbase.constants.URLConstant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal object APIInstance {

    val api: API by lazy {
        Retrofit.Builder()
            .baseUrl(SqlBaseConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getLoggerClient().build())
            .build()
            .create(API::class.java)
    }

    private fun getLoggerClient(): OkHttpClient.Builder {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        return httpClient
    }

}