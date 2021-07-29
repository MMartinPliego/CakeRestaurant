package com.orumgames.cakesrestaurants.data.repository

import com.orumgames.cakesrestaurants.data.local.dao.CakeDao
import com.orumgames.cakesrestaurants.data.mappers.toDomain
import com.orumgames.cakesrestaurants.data.mappers.toEntity
import com.orumgames.cakesrestaurants.data.remote.api.RemoteApi
import com.orumgames.cakesrestaurants.domain.model.Cake
import com.orumgames.cakesrestaurants.domain.repository.CakeRepository
import javax.inject.Inject

class CakeRepositoryImpl @Inject constructor(
    private val dao: CakeDao,
    private val api: RemoteApi
) : CakeRepository {
    override suspend fun findAll(): List<Cake>? {
        var items = dao.findAll()
        if(items.isNullOrEmpty()) {
            items = api.getCakeList().let { res ->
                if(res.isSuccessful) {
                    res.body()?.distinct()?.forEach { dao.insert(it.toEntity()) }
                    dao.findAll()
                } else null
            }
        }
        return items?.sortedBy { it.title }?.map {
            it.toDomain()
        }
    }

    override suspend fun findById(id: Int): Cake? ? = dao.findCakeById(id)?.toDomain()
}