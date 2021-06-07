package com.della.della_mobile.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.della.della_mobile.DellaApplication

object AndroidUtils {
    fun isInternetAvailable(): Boolean {
        val conexao = DellaApplication.getInstance()
            .applicationContext
            .getSystemService(Context.CONNECTIVITY_SERVICE)  as ConnectivityManager

        val redes = conexao.allNetworks
        return redes.map{conexao.getNetworkInfo(it)}.any{it?.state == NetworkInfo.State.CONNECTED}
    }
}