package com.example.weatherappwithdb

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import coil.compose.AsyncImage
import com.example.weatherappwithdb.core.apis.weatherApi.NetworkResponse
import com.example.weatherappwithdb.core.models.weatherApiModel.WeatherData
import com.example.weatherappwithdb.core.repositories.WeatherRepository
import com.example.weatherappwithdb.core.repositories.WeatherRepositoryImpl
import com.example.weatherappwithdb.core.viewmodels.WeatherViewModel
import com.example.weatherappwithdb.core.viewmodels.WeatherViewModelFactory
import com.example.weatherappwithdb.ui.theme.WeatherAppWithDBTheme

class MainActivity : ComponentActivity() {
    private val weatherViewModel: WeatherViewModel by viewModels {
        WeatherViewModelFactory(WeatherRepositoryImpl())
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            //weatherViewModel = ViewModelProvider(this)[WeatherViewModel::class.java]
            WeatherAppWithDBTheme {
                Surface(modifier = Modifier.padding()) {
                    ScreenA(weatherViewModel)
                }
            }
        }
    }
}

//@Composable
//fun ScreenA(weatherViewModel: WeatherViewModel) {
//    val weatherResult = weatherViewModel.weather.collectAsState()
//    var city by remember { mutableStateOf("") }
//    when (val result = weatherResult.value) {
//        is NetworkResponse.Error -> {
//            Text(text = "Error: ${result.message}")
//            Log.d("TAG", "WeatherResult: Error")
//        }
//
//        is NetworkResponse.Loading -> {
//            CircularProgressIndicator()
//            Log.d("TAG", "WeatherResult: Loading")
//        }
//
//        is NetworkResponse.Success -> {
//            // Smart cast to WeatherData
//            WeatherDetails(result.data)
//            Log.d("TAG", "WeatherResult: Success")
//        }
//
//        null -> {
//            // Initial state before any search is made
//            Text(text = "Please search for location")
//        }
//    }
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(8.dp), verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceEvenly
//        ) {
//            OutlinedTextField(
//                modifier = Modifier.weight(1f),
//                value = city,
//                onValueChange = { city = it },
//                label = { Text("Search for any Location") }
//            )
//            IconButton(onClick = {
//                weatherViewModel.getData(city)
//            }) {
//                Icon(
//                    imageVector = Icons.Default.Search,
//                    contentDescription = "Search for any Location"
//                )
//            }
//        }
//    }
//
//}

@Composable
fun ScreenA(weatherViewModel: WeatherViewModel) {
    // These states remain the same
    val weatherResult by weatherViewModel.weather.collectAsState()
    var city by remember { mutableStateOf("") }

    // 1. This is now the main layout container
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = city,
                onValueChange = { city = it },
                label = { Text("Search for any Location") }
            )
            IconButton(onClick = {
                weatherViewModel.getData(city)
            }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search for any Location"
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (val result = weatherResult) {
            is NetworkResponse.Error -> {
                Text(text = "Error: ${result.message}")
            }
            is NetworkResponse.Loading -> {
                CircularProgressIndicator()
            }
            is NetworkResponse.Success -> {
                WeatherDetails(result.data)
            }
            null -> {
                Text(text = "Search a city to see the weather")
            }
        }
    }
}

@Composable
fun WeatherDetails(data: WeatherData) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Bottom
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = " Location icon",
                modifier = Modifier.size(30.dp)
            )
            Text(text = data.location.name, fontSize = 30.sp)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = data.location.country, fontSize = 18.sp, color = Gray)

        }
    }
    Spacer(modifier = Modifier.height(16.dp))
    Row (modifier = Modifier, horizontalArrangement = Arrangement.SpaceBetween) {
        Text(
            text = "${data.current.temp_c}°C",
            fontSize = 56.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        AsyncImage(
            modifier = Modifier.size(80.dp),
            model = "https:${data.current.condition.icon}",
            contentDescription = "Weather Icon"
        )
    }
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = data.current.condition.text,
        fontSize = 22.sp,
        textAlign = TextAlign.Center
    )
}
