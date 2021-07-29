package com.orumgames.cakesrestaurants.data.local

import android.content.Context
import androidx.room.Room
import com.orumgames.cakesrestaurants.utils.DATABASE_NAME

object DatabaseBuilder {
    private var INSTANCE: AppDB? = null

    fun getInstance(context: Context): AppDB {
        if (INSTANCE == null) {
            synchronized(AppDB::class) {
                INSTANCE = buildRoomDB(context)
            }
        }

        return INSTANCE!!
    }

    private fun buildRoomDB(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            AppDB::class.java,
            DATABASE_NAME
        ).build()
}