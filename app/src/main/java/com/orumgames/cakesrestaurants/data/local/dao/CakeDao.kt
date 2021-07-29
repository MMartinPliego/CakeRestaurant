package com.orumgames.cakesrestaurants.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.orumgames.cakesrestaurants.data.local.entity.CakeEntity

@Dao
interface CakeDao {

    @Query("SELECT * FROM cake WHERE id=:id")
    fun findCakeById(id: Int): CakeEntity?

    @Query("select * from cake")
    fun findAll(): List<CakeEntity>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(cake: CakeEntity): Long
}