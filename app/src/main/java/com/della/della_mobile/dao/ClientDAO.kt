package com.della.della_mobile.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.della.della_mobile.models.Client

@Dao
interface ClientDAO {
    @Query("SELECT * FROM client WHERE id = :id")
    fun getById(id: Long): Client?

    @Query("SELECT * FROM client")
    fun findAll(): List<Client>

    @Insert
    fun insert(client: Client)

    @Delete
    fun delete(client: Client)
}