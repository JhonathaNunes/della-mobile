package com.della.della_mobile.database

import androidx.room.Room
import com.della.della_mobile.DellaApplication
import com.della.della_mobile.dao.ClientDAO

object DatabaseManager {
    private var dbInstance: DellaDatabase
    init {
        val appContext = DellaApplication.getInstance().applicationContext
        dbInstance = Room.databaseBuilder(
            appContext,
            DellaDatabase::class.java,
            "della.sqlite"
        ).build()
    }

    fun getClientDAO(): ClientDAO {
        return dbInstance.clientDAO()
    }
}