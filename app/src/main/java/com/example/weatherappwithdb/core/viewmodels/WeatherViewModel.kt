package com.example.weatherappwithdb.core.viewmodels

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
    private val repository: WeatherRepository
): ViewModel() {

    private val _weather = MutableStateFlow<NetworkResponse<WeatherData>?>(null)
    val weather: StateFlow<NetworkResponse<WeatherData>?> = _weather
    fun getData(city: String){
        viewModelScope.launch {
            _weather.value = repository.getWeatherByCity(city)
        }
    }
}