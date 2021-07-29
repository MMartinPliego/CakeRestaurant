package com.orumgames.cakesrestaurants.domain.model

data class Restaurant(
    val id: Int,
    val title: String,
    val desc: String,
    val imageUrl: String,
    val address: String
)
