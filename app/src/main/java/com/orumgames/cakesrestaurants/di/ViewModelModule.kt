package com.orumgames.cakesrestaurants.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.orumgames.cakesrestaurants.ui.main.cake.CakesViewModel
import com.orumgames.cakesrestaurants.ui.main.restaurant.RestaurantsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(modelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CakesViewModel::class)
    abstract fun bindCakesViewModel(viewModel: CakesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RestaurantsViewModel::class)
    abstract fun bindRestaurantsViewModel(viewModel: RestaurantsViewModel): ViewModel

}