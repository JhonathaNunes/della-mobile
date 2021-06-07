package com.della.della_mobile.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.GsonBuilder
import java.io.Serializable

@Entity(tableName = "client")
class Client(
    @PrimaryKey
    var id: Long = 0,
    var fullName: String = "",
    var email: String = "",
    var cpf: String? = null,
    var cnpj: String? = null,
) : Serializable {

    override fun toString(): String {
        return "Cliente: $fullName"
    }

    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }
}