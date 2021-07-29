package com.orumgames.cakesrestaurants.di

import android.app.Application
import com.orumgames.cakesrestaurants.data.local.AppDB
import com.orumgames.cakesrestaurants.data.local.DatabaseBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application) = DatabaseBuilder.getInstance(app)

    @Singleton
    @Provides
    fun provideCakeDao(db: AppDB) = db.cakeDao()

    @Singleton
    @Provides
    fun provideRestaurantDao(db: AppDB) = db.restaurantDao()
}