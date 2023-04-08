package com.appringer.sqlbase.model

import com.google.gson.annotations.SerializedName

data class DeleteAccountRequest(
    @SerializedName("user_id")
    val userId:Int
)
