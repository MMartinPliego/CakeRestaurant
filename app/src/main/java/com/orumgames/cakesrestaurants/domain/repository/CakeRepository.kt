package com.orumgames.cakesrestaurants.domain.repository

import com.orumgames.cakesrestaurants.domain.model.Cake

interface CakeRepository {
    suspend fun findAll() : List<Cake>?
    suspend fun findById(id: Int) : Cake?
}