package com.orumgames.cakesrestaurants.data.mappers

import com.orumgames.cakesrestaurants.data.local.entity.CakeEntity
import com.orumgames.cakesrestaurants.data.local.entity.RestaurantEntity
import com.orumgames.cakesrestaurants.domain.model.Cake
import com.orumgames.cakesrestaurants.domain.model.Restaurant

fun CakeEntity.toDomain() = Cake(
    id = this.id ?: 0,
    title = this.title,
    desc = this.desc,
    imageUrl = this.imageUrl
)

fun RestaurantEntity.toDomain() = Restaurant(
    id = this.id ?: 0,
    title = this.title,
    desc = this.desc,
    imageUrl = this.imageUrl,
    address = this.address
)