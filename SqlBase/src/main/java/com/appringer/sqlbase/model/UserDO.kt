package com.appringer.sqlbase.model

import com.appringer.sqlbase.BuildConfig
import com.google.gson.annotations.SerializedName

open class UserDO  {

    var countryCode: String = if(BuildConfig.DEBUG) "+91" else "+90"
    var uid:String? = null
    var mobileNumber:String? = null
    var name:String? = null
    var email:String? = null
    var id:Int? = null
    var token:String? = null
    var push_token:String? = null

    fun getPhone() = "$countryCode${mobileNumber?:""}"
}