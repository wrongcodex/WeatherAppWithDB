package com.example.weatherappwithdb.core.apis.weatherApi

import com.example.weatherappwithdb.core.apis.weatherApi.services.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object RetrofitModule {

    private const val baseUrl = "https://api.weatherapi.com/"

    @Singleton
    @Provides
    fun provideRetrofitInstance(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //services
    @Provides
    fun provideWeatherService(retrofit: Retrofit): WeatherApi{
        return retrofit.create(WeatherApi::class.java)
    }

}