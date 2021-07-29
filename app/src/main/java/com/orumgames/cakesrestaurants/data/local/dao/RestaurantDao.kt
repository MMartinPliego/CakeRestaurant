package com.orumgames.cakesrestaurants.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.orumgames.cakesrestaurants.data.local.entity.RestaurantEntity

@Dao
interface RestaurantDao {

    @Query("SELECT * FROM restaurant WHERE id=:id")
    fun findRestaurantById(id: Int): RestaurantEntity?

    @Query("select * from restaurant")
    fun findAll(): List<RestaurantEntity>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(restaurant: RestaurantEntity): Long
}