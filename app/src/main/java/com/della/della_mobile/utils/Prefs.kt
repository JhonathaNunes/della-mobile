package com.della.della_mobile.utils

import android.content.SharedPreferences
import com.della.della_mobile.DellaApplication

object Prefs {
    val PREF_ID = "DELLA"

    private fun prefs(): SharedPreferences {
        val context = DellaApplication.getInstance().applicationContext
        return context.getSharedPreferences(PREF_ID, 0)
    }

    fun setBoolean(flag: String, valor: Boolean) =  prefs().edit().putBoolean(flag, valor).apply()

    fun getBoolean(flag: String) = prefs().getBoolean(flag, false)

    fun setString(flag: String, valor: String) =  prefs().edit().putString(flag, valor).apply()

    fun getString(flag: String) = prefs().getString(flag, "")
}