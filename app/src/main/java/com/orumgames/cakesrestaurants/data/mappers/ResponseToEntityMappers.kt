package com.orumgames.cakesrestaurants.data.mappers

import com.orumgames.cakesrestaurants.data.local.entity.CakeEntity
import com.orumgames.cakesrestaurants.data.local.entity.RestaurantEntity
import com.orumgames.cakesrestaurants.data.remote.model.CakeResponse
import com.orumgames.cakesrestaurants.data.remote.model.RestaurantResponse

fun CakeResponse.toEntity() = CakeEntity(
    title = this.title,
    desc = this.desc,
    imageUrl = this.imageUrl
)

fun RestaurantResponse.toEntity() = RestaurantEntity(
    title = this.title,
    desc = this.desc,
    imageUrl = this.imageUrl,
    address = this.address
)