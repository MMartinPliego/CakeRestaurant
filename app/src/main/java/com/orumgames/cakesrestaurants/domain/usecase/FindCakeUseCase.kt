package com.orumgames.cakesrestaurants.domain.usecase

import com.orumgames.cakesrestaurants.domain.repository.CakeRepository
import javax.inject.Inject

class FindCakeUseCase @Inject constructor(private val cakeRepo: CakeRepository) {

    suspend operator fun invoke(id: Int) = cakeRepo.findById(id)
}