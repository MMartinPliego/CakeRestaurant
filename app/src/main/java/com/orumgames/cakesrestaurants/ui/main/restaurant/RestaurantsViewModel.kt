package com.orumgames.cakesrestaurants.ui.main.restaurant

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.orumgames.cakesrestaurants.domain.model.Cake
import com.orumgames.cakesrestaurants.domain.model.Restaurant
import com.orumgames.cakesrestaurants.domain.usecase.GetAllRestaurantsUseCase
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class RestaurantsViewModel @Inject constructor(private val getAllRestaurantsUseCase: GetAllRestaurantsUseCase) :
    ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext = Dispatchers.IO + job

    private val _restaurants = MutableLiveData<List<Restaurant>>().apply {
        value = emptyList()
    }
    val restaurants: LiveData<List<Restaurant>> = _restaurants

    fun getAllRestaurants() = launch {
        val res = getAllRestaurantsUseCase.invoke()
        setRestaurants(res)
    }

    private fun setRestaurants(restaurants: List<Restaurant>?) = launch(Dispatchers.Main) {
        _restaurants.value = restaurants
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}