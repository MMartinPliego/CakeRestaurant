package com.orumgames.cakesrestaurants.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "cake",
    indices = [Index(value = ["title", "desc", "imageUrl"], unique = true)]
)
data class CakeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val desc: String,
    val imageUrl: String
)
