package com.orumgames.cakesrestaurants.domain.repository

import com.orumgames.cakesrestaurants.domain.model.Restaurant

interface RestaurantRepository {
    suspend fun findAll() : List<Restaurant>?
    suspend fun findById(id: Int) : Restaurant?
}