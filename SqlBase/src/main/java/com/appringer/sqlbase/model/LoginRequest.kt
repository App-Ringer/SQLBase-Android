package com.appringer.sqlbase.model


import com.google.gson.annotations.SerializedName

internal data class LoginRequest(
    @SerializedName("countryCode")
    var countryCode: String?,
    @SerializedName("mobileNumber")
    var mobileNumber: String?,
    @SerializedName("uid")
    var uid: String?
)