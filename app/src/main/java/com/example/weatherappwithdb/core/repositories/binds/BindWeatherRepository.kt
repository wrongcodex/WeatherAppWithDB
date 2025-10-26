package com.example.weatherappwithdb.core.repositories.binds

import com.example.weatherappwithdb.core.repositories.impl.WeatherRepositoryImpl
import com.example.weatherappwithdb.core.repositories.repo.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BindWeatherRepository {

    @Binds
    abstract fun bindWeatherRepository(weatherRepositoryImpl: WeatherRepositoryImpl): WeatherRepository
}