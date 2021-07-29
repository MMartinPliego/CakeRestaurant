package com.orumgames.cakesrestaurants.ui.main.cake

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.orumgames.cakesrestaurants.domain.model.Cake
import com.orumgames.cakesrestaurants.domain.usecase.GetAllCakesUseCase
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class CakesViewModel @Inject constructor(private val getAllCakesUseCase: GetAllCakesUseCase) :
    ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext = Dispatchers.IO + job

    private val _cakes = MutableLiveData<List<Cake>>().apply {
        value = emptyList()
    }
    val cakes: LiveData<List<Cake>> = _cakes

    fun getAllCakes() = launch {
        val cakes = getAllCakesUseCase.invoke()
        setCakes(cakes)
    }

    private fun setCakes(cakes: List<Cake>?) = launch(Dispatchers.Main) {
        _cakes.value = cakes
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}