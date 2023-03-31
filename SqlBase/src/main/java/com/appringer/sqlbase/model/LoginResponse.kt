package com.appringer.sqlbase.model

import com.google.gson.annotations.SerializedName

class LoginResponse: BaseResponse() {
    @SerializedName("data")
    var data: LoginData? = null
}

data class LoginData(
    @SerializedName("user")
    var user: UserDO? = null
)