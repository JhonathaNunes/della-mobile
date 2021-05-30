package com.della.della_mobile.services

import android.content.Context
import android.util.Log
import com.della.della_mobile.models.Client
import com.della.della_mobile.utils.AndroidUtils
import com.della.della_mobile.utils.HttpHelper
import com.della.della_mobile.utils.JsonParser.parserJson
import com.della.della_mobile.utils.Response

object ClientsService {
    val HOST = "http://jhonpython.pythonanywhere.com"

    fun getClients(context: Context): List<Client> {
        if (!AndroidUtils.isInternetAvailable(context)) {
            return ArrayList<Client>()
        }

        val url = "$HOST/clientes"
        val json = HttpHelper.get(url)

        return parserJson(json)
    }

    fun create(client: Client): Response {
        val json = HttpHelper.post("$HOST/clientes", client.toJson())
        return parserJson(json)
    }

    fun delete(client: Client): Response {
        val url = "$HOST/clientes/${client.id}"
        val json = HttpHelper.delete(url)
        return parserJson(json)
    }
}