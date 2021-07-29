package com.orumgames.cakesrestaurants.domain.usecase

import com.orumgames.cakesrestaurants.domain.repository.RestaurantRepository
import javax.inject.Inject

class GetAllRestaurantsUseCase @Inject constructor(private val restaurantRepo: RestaurantRepository){

    suspend operator fun invoke() = restaurantRepo.findAll()
}