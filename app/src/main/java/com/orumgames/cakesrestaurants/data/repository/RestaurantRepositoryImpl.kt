package com.orumgames.cakesrestaurants.data.repository

import com.orumgames.cakesrestaurants.data.local.dao.RestaurantDao
import com.orumgames.cakesrestaurants.data.mappers.toDomain
import com.orumgames.cakesrestaurants.data.mappers.toEntity
import com.orumgames.cakesrestaurants.data.remote.api.RemoteApi
import com.orumgames.cakesrestaurants.domain.model.Restaurant
import com.orumgames.cakesrestaurants.domain.repository.RestaurantRepository
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

class RestaurantRepositoryImpl @Inject constructor(
    private val dao: RestaurantDao,
    private val api: RemoteApi
) : RestaurantRepository {
    override suspend fun findAll(): List<Restaurant>? {
        var items = dao.findAll()
        if(items.isNullOrEmpty()) {
            try {
                items = api.getRestaurantList().let { res ->
                    if(res.isSuccessful) {
                        res.body()?.distinct()?.forEach { dao.insert(it.toEntity()) }
                        dao.findAll()
                    } else null
                }
            } catch (ex: Exception) {
                Timber.e(ex)
                null
            }

        }
        return items?.sortedBy { it.title }?.map {
            it.toDomain()
        }
    }

    override suspend fun findById(id: Int): Restaurant? = dao.findRestaurantById(id)?.toDomain()
}