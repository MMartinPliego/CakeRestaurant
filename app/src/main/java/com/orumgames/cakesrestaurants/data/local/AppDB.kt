package com.orumgames.cakesrestaurants.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.orumgames.cakesrestaurants.data.local.dao.CakeDao
import com.orumgames.cakesrestaurants.data.local.dao.RestaurantDao
import com.orumgames.cakesrestaurants.data.local.entity.CakeEntity
import com.orumgames.cakesrestaurants.data.local.entity.RestaurantEntity

@Database(entities = [CakeEntity::class, RestaurantEntity::class], version = 1)
abstract class AppDB : RoomDatabase() {

    abstract fun cakeDao() : CakeDao
    abstract fun restaurantDao() : RestaurantDao

}