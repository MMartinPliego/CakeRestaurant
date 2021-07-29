package com.orumgames.cakesrestaurants.data.remote.api

import com.orumgames.cakesrestaurants.data.remote.model.CakeResponse
import com.orumgames.cakesrestaurants.data.remote.model.RestaurantResponse
import retrofit2.Response
import retrofit2.http.GET

interface RemoteApi {

    @GET(value = "c51c635c877d53d0fbc7d803f23af7ea/raw/0d4454a75859e8f94834a3de257b0b69a77e0b10/cakes")
    suspend fun getCakeList() : Response<List<CakeResponse>>

    @GET(value = "eade13bf4d0a62cca5d3163e7f1956e6/raw/34b2c2c5381a0f688e4cc3e8afe1b44831bef4d0/restaurants")
    suspend fun getRestaurantList() : Response<List<RestaurantResponse>>
}