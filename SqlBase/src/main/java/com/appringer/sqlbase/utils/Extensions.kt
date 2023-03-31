package com.appringer.sqlbase.utils

import com.google.gson.reflect.TypeToken

fun<T> String.toObject(obj:Class<T>):T{
    val type = object : TypeToken<T>() {}.type
    return GSONUtils.getObj(this, type)
}