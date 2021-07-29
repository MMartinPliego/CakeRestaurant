package com.orumgames.cakesrestaurants.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "restaurant",
    indices = [Index(value = ["title", "desc", "imageUrl", "address"], unique = true)]
)
data class RestaurantEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val desc: String,
    val imageUrl: String,
    val address: String
)
