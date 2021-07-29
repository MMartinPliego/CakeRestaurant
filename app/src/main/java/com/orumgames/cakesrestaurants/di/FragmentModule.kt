package com.orumgames.cakesrestaurants.di

import com.orumgames.cakesrestaurants.ui.main.MainActivity
import com.orumgames.cakesrestaurants.ui.main.cake.CakesFragment
import com.orumgames.cakesrestaurants.ui.main.restaurant.RestaurantsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    internal abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    internal abstract fun bindCakesFragment(): CakesFragment

    @ContributesAndroidInjector
    internal abstract fun bindRestaurantsFragment(): RestaurantsFragment
}