package com.appringer.sqlbase.network

import android.content.Context
import com.appringer.sqlbase.config.SQLBasePreferenceHelper
import com.appringer.sqlbase.model.DeleteAccountRequest
import com.appringer.sqlbase.model.LoginRequest
import com.appringer.sqlbase.model.LoginResponse
import com.appringer.sqlbase.model.Request
import com.appringer.sqlbase.utils.GSONUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal object NetworkHelper {

    fun set(
        context: Context, request: Request,
        onSuccessListener: ((isSuccessful: Boolean, data: String) -> Unit)?,
        onFailureListener: ((Exception) -> Unit)?
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                SQLBasePreferenceHelper.getAuthToken(context)?.let {
                    val response = APIInstance.api.set(request = request, token = it)
                    onSuccessListener?.let {
                        withContext(Dispatchers.Main) {
                            it(
                                response.isSuccessful,
                                GSONUtils.toString(response.body())
                            )
                        }
                    }
                } ?: onFailureListener?.let {
                    withContext(Dispatchers.Main) {
                        it(Exception("Data error"))
                    }
                }
            } catch (e: Exception) {
                onFailureListener?.let {
                    withContext(Dispatchers.Main) {
                        it(e)
                    }

                }
            }
        }
    }

    fun login(
        context: Context, token: String, request: LoginRequest,
        onSuccessListener: ((isSuccessful: Boolean, data: String) -> Unit)?,
        onFailureListener: ((Exception) -> Unit)?
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = APIInstance.api.login(token, request)
                val authToken = GSONUtils.getObj(
                    GSONUtils.toString(response.body()),
                    LoginResponse::class.java
                ).data?.user?.token
                authToken?.let {
                    SQLBasePreferenceHelper.setAuthToken(authToken, context)
                    onSuccessListener?.let {
                        withContext(Dispatchers.Main) {
                            it(response.isSuccessful, GSONUtils.toString(response.body()))
                        }
                    }
                } ?: onFailureListener?.let {
                    withContext(Dispatchers.Main) {
                        it(Exception("Data error"))
                    }
                }
            } catch (e: Exception) {
                onFailureListener?.let {
                    withContext(Dispatchers.Main) {
                        it(e)
                    }
                }
            }
        }
    }

    fun get(
        context: Context, request: Request,
        onSuccessListener: ((isSuccessful: Boolean, data: String) -> Unit)?,
        onFailureListener: ((Exception) -> Unit)?
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                SQLBasePreferenceHelper.getAuthToken(context)?.let {
                    val response = APIInstance.api.get(request = request, token = it)
                    onSuccessListener?.let {
                        withContext(Dispatchers.Main) {
                            it(
                                response.isSuccessful,
                                GSONUtils.toString(response.body())
                            )
                        }
                    }
                } ?: onFailureListener?.let {
                    withContext(Dispatchers.Main) {
                        it(Exception("Data error"))
                    }
                }
            } catch (e: Exception) {
                onFailureListener?.let {
                    withContext(Dispatchers.Main) {
                        it(e)
                    }
                }
            }
        }
    }

    fun deleteAccount(
        request: DeleteAccountRequest,
        context: Context,
        onSuccessListener: ((isSuccessful: Boolean, data: String) -> Unit)?,
        onFailureListener: ((Exception) -> Unit)?
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                SQLBasePreferenceHelper.getAuthToken(context)?.let {
                    val response = APIInstance.api.deleteAccount(request = request, token = it)
                    onSuccessListener?.let {
                        withContext(Dispatchers.Main) {
                            it(
                                response.isSuccessful,
                                GSONUtils.toString(response.body())
                            )
                        }
                    }
                } ?: onFailureListener?.let {
                    withContext(Dispatchers.Main) {
                        it(Exception("Data error"))
                    }
                }
            } catch (e: Exception) {
                onFailureListener?.let {
                    withContext(Dispatchers.Main) {
                        it(e)
                    }
                }
            }
        }
    }

}