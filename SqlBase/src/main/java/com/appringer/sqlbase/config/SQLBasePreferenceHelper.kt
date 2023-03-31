package com.appringer.sqlbase.config

/*
 * Created by Kushaal Singla
 */
import android.annotation.SuppressLint
import android.content.Context

@SuppressLint("ApplySharedPref")
internal object SQLBasePreferenceHelper {
    private const val AUTH_TOKEN = "AUTH_TOKEN"
    private fun getPreferences(context: Context) =
        context.getSharedPreferences("my_pref", Context.MODE_PRIVATE)

    private var authToken: String? = null


    fun getAuthToken(context: Context):String? {
        val token = getPreferences(context).getString(AUTH_TOKEN, null)
        return token?.let { "Bearer $it" }
    }
    fun setAuthToken(token: String, context: Context) {
        getPreferences(context).edit().putString(AUTH_TOKEN, token).apply()
    }

    @SuppressLint("ApplySharedPref")
    fun clear(context: Context) {
        getPreferences(context).edit().clear().commit()
    }


}