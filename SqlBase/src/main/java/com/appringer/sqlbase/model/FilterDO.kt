package com.appringer.sqlbase.model


import com.google.gson.annotations.SerializedName

internal data class FilterDO(
    @SerializedName("col")
    var col: String?,
    @SerializedName("columnOperator")
    var columnOperator: String?,
    @SerializedName("operator")
    var operator: String?,
    @SerializedName("values")
    var values: List<Any>?
)