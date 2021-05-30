package com.della.della_mobile.services

import android.content.Context
import android.util.Log
import com.della.della_mobile.database.DatabaseManager
import com.della.della_mobile.models.Client
import com.della.della_mobile.utils.AndroidUtils
import com.della.della_mobile.utils.HttpHelper
import com.della.della_mobile.utils.JsonParser.parserJson
import com.della.della_mobile.utils.Response

object ClientsService {
    val HOST = "http://jhonpython.pythonanywhere.com"
    val dao = DatabaseManager.getClientDAO()

    fun clientExist(client: Client): Boolean {
        return dao.getById(client.id) != null
    }

    fun saveOffline(client: Client) : Boolean {
        if (! clientExist(client)) {
            dao.insert(client)
        }

        return true
    }

    fun getClients(): List<Client> {
        if (!AndroidUtils.isInternetAvailable()) {
            return dao.findAll()
        }

        val url = "$HOST/clientes"
        val json = HttpHelper.get(url)
        val clients: ArrayList<Client> = parserJson(json)

        clients.forEach {
            saveOffline(it)
        }

        return clients
    }

    fun create(client: Client): Response {
        val json = HttpHelper.post("$HOST/clientes", client.toJson())
        return parserJson(json)
    }

    fun delete(client: Client): Response {
        dao.delete(client)

        if (!AndroidUtils.isInternetAvailable()) {
            return Response(status = "OK", msg = "Dados excluídos localmente")
        }

        val url = "$HOST/clientes/${client.id}"
        val json = HttpHelper.delete(url)
        return parserJson(json)
    }
}