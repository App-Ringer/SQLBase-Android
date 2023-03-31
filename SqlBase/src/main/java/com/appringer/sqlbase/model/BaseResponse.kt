package com.appringer.sqlbase.model


import com.google.gson.annotations.SerializedName

open class BaseResponse {
    @SerializedName("message")
    var message: String? = null

    @SerializedName("status")
    var status: Int? = null
}