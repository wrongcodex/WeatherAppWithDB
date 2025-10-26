package com.example.weatherappwithdb.core.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherappwithdb.core.apis.weatherApi.NetworkResponse
import com.example.weatherappwithdb.core.models.weatherApiModel.WeatherData
import com.example.weatherappwithdb.core.repositories.repo.WeatherRepository
import com.example.weatherappwithdb.core.repositories.impl.WeatherRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WeatherViewModel@Inject constructor(
    private val weatherRepository: WeatherRepository
): ViewModel() {


    private val _weather = MutableStateFlow<NetworkResponse<WeatherData>>(NetworkResponse.Loading)
    val weather: StateFlow<NetworkResponse<WeatherData>> = _weather
    fun getData(city: String){
        viewModelScope.launch {
            _weather.value = weatherRepository.getWeatherByCity(city)
            Log.d("abcd", "getDataVM: ${_weather.value}")
        }
    }
}