package com.appringer.sqlbase.sqlbase

import android.content.Context
import com.appringer.sqlbase.config.SQLBasePreferenceHelper
import com.appringer.sqlbase.model.DeleteAccountRequest
import com.appringer.sqlbase.model.LoginRequest
import com.appringer.sqlbase.model.Request
import com.appringer.sqlbase.network.NetworkHelper

internal class Execute(private val tableName: String?=null, private val query: Query) {
    fun set(data: Any, context: Context, onSuccessListener: ((isSuccessful: Boolean, data: String) -> Unit)?, onFailureListener: ((Exception) -> Unit)?) {
        val request = Request(data = data, tableName = tableName)
        NetworkHelper.set(context, request, onSuccessListener, onFailureListener)
    }

    fun login(token: String, request: LoginRequest, context: Context, onSuccessListener: ((isSuccessful: Boolean, data: String) -> Unit)?, onFailureListener: ((Exception) -> Unit)?) {
        NetworkHelper.login(context, token, request, onSuccessListener, onFailureListener)
    }

    fun get(context: Context, onSuccessListener: ((isSuccessful: Boolean, data: String) -> Unit)?, onFailureListener: ((Exception) -> Unit)?) {
        val request = Request(
            tableName = tableName,
            limit = query.getLimit(),
            offset = query.getOffset(),
            filter = query.getFilters(),
            orderBy = query.getOrderBy()
        )
        NetworkHelper.get(context, request, onSuccessListener, onFailureListener)
    }

    fun logout(context: Context) {
        SQLBasePreferenceHelper.clear(context)
    }

    fun deleteAccount(
        userId: Int,
        context: Context,
        onSuccessListener: ((isSuccessful: Boolean, data: String) -> Unit)?,
        onFailureListener: ((Exception) -> Unit)?
    ) {
        val request = DeleteAccountRequest(userId)
        NetworkHelper.deleteAccount(request,
            context,
            onSuccessListener,
            onFailureListener)
    }
}