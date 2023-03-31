package com.appringer.sqlbase.model


import com.google.gson.annotations.SerializedName

internal data class Response(
    @SerializedName("data")
    var data: Any?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("status")
    var status: String?
)