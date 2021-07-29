package com.orumgames.cakesrestaurants.di

import com.orumgames.cakesrestaurants.data.local.dao.CakeDao
import com.orumgames.cakesrestaurants.data.local.dao.RestaurantDao
import com.orumgames.cakesrestaurants.data.remote.api.RemoteApi
import com.orumgames.cakesrestaurants.data.repository.CakeRepositoryImpl
import com.orumgames.cakesrestaurants.data.repository.RestaurantRepositoryImpl
import com.orumgames.cakesrestaurants.domain.repository.CakeRepository
import com.orumgames.cakesrestaurants.domain.repository.RestaurantRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideCakeRepo(dao: CakeDao, api: RemoteApi): CakeRepository = CakeRepositoryImpl(dao, api)

    @Provides
    fun provideRestaurantRepo(dao: RestaurantDao, api: RemoteApi): RestaurantRepository = RestaurantRepositoryImpl(dao, api)
}