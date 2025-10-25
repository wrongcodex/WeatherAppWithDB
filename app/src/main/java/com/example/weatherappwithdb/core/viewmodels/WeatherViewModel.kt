package com.example.weatherappwithdb.core.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherappwithdb.core.apis.weatherApi.NetworkResponse
import com.example.weatherappwithdb.core.models.weatherApiModel.WeatherData
import com.example.weatherappwithdb.core.repositories.WeatherRepository
import com.example.weatherappwithdb.core.repositories.WeatherRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class WeatherViewModel(
    private val repository : WeatherRepositoryImpl
): ViewModel() {


    private val _weather = MutableStateFlow<NetworkResponse<WeatherData>>(NetworkResponse.Loading)
    val weather: StateFlow<NetworkResponse<WeatherData>> = _weather
    fun getData(city: String){
        viewModelScope.launch {
            _weather.value = repository.getWeatherByCity(city)
            Log.d("abcd", "getDataVM: ${_weather.value}")
        }
    }
}