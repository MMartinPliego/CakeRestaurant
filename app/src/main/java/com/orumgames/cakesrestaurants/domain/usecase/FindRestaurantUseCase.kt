package com.orumgames.cakesrestaurants.domain.usecase

import com.orumgames.cakesrestaurants.domain.repository.RestaurantRepository
import javax.inject.Inject

class FindRestaurantUseCase @Inject constructor(private val restaurantRepo: RestaurantRepository) {

    suspend operator fun invoke(id: Int) = restaurantRepo.findById(id)
}