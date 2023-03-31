package com.appringer.sqlbase.model


import com.google.gson.annotations.SerializedName

internal data class OrderDO(
    @SerializedName("col")
    var col: String?,
    @SerializedName("operator")
    var operator: String?
)