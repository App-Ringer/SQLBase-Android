package com.appringer.sqlbase.model


import com.google.gson.annotations.SerializedName

internal data class Request(
    @SerializedName("data")
    var data: Any? = null,
    @SerializedName("is_update")
    var isUpdate: String? = null,
    @SerializedName("tableName")
    var tableName: String? = null,
    @SerializedName("limit")
    var limit: Int? = null,
    @SerializedName("offset")
    var offset: Int? = null,
    @SerializedName("filter")
    var filter: ArrayList<FilterDO>? = null,
    @SerializedName("orderBy")
    var orderBy: ArrayList<OrderDO>? = null
)