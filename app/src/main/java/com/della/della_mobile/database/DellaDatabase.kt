package com.della.della_mobile.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.della.della_mobile.dao.ClientDAO
import com.della.della_mobile.models.Client

@Database(entities = arrayOf(Client::class), version = 1)
abstract class DellaDatabase: RoomDatabase() {
    abstract fun clientDAO(): ClientDAO
}