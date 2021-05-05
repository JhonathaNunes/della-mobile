package com.della.della_mobile.services

import android.content.Context
import com.della.della_mobile.models.Client

object ClientsService {
    fun getClients(): List<Client> {
        val clients = mutableListOf<Client>()
        for (i in 1..50) {
            val client = Client(
                fullName = "Client $i",
                email = "client+$i@gmail.com"
            )

            clients.add(client)
        }

        return clients
    }
}