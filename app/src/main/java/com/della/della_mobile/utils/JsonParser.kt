package com.della.della_mobile.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object JsonParser {
    inline fun <reified T> parserJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }
}