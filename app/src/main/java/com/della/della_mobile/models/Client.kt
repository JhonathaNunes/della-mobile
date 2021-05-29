package com.della.della_mobile.models

import com.google.gson.GsonBuilder

class Client(
    var id: Long = 0,
    var fullName: String = "",
    var email: String = "",
    var cpf: String? = null,
    var cnpj: String? = null,
) {

    override fun toString(): String {
        return "Cliente: $fullName"
    }

    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }
}