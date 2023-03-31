package com.appringer.sqlbase.network

import com.appringer.sqlbase.config.SQLBasePreferenceHelper
import com.appringer.sqlbase.constants.URLConstant
import com.appringer.sqlbase.model.LoginRequest
import com.appringer.sqlbase.model.LoginResponse
import com.appringer.sqlbase.model.Request
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST


internal interface API {

//    @POST(URLConstant.LOGIN)
//    suspend fun login(@Body query: UserDO): Response<DataSource<UserDO>>
//
//    @GET(URLConstant.GET_MASTER_DATA)
//    suspend fun  getAppMasterData(): Response<DataSource<AppMasterDO>>

    @POST(URLConstant.UPSERT)
    fun upsert(@Header("Authorization")token:String,@Body request: Request): Call<ResponseBody>

    @POST(URLConstant.UPSERT)
    suspend fun set(@Header("Authorization")token:String,@Body request: Request): Response<com.appringer.sqlbase.model.Response>

    @POST(URLConstant.GET)
    suspend fun get(@Header("Authorization")token:String,@Body request: Request): Response<com.appringer.sqlbase.model.Response>

    @POST(URLConstant.LOGIN)
    suspend fun login(@Header("Authorization")token:String,@Body request: LoginRequest): Response<LoginResponse>

}