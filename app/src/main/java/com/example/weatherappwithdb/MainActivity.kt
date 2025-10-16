package com.example.weatherappwithdb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import com.example.weatherappwithdb.core.apis.weatherApi.NetworkResponse
import com.example.weatherappwithdb.core.models.weatherApiModel.WeatherData
import com.example.weatherappwithdb.core.repositories.WeatherRepository
import com.example.weatherappwithdb.core.viewmodels.WeatherViewModel
import com.example.weatherappwithdb.ui.theme.WeatherAppWithDBTheme

class MainActivity : ComponentActivity() {
    private lateinit var weatherViewModel: WeatherViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            weatherViewModel = ViewModelProvider(this)[WeatherViewModel::class.java]
            WeatherAppWithDBTheme {
                ScreenA(weatherViewModel)
            }
        }
    }
}

@Composable
fun ScreenA(weatherViewModel: WeatherViewModel) {
    val weatherResult = weatherViewModel.weather.collectAsState()
    var city by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp), verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
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
    }
    when(val result = weatherResult){
        is NetworkResponse.Loading ->{
            CircularProgressIndicator()
        }
        is NetworkResponse.Error -> {
            Text(text = result.message)
        }
        is NetworkResponse.Success<WeatherData> -> {
            WeatherDetails(data = result.data)
        }
    }
}


@Composable
fun WeatherDetails(data: WeatherData) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Bottom
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = " Location icon",
                modifier = Modifier.size(40.dp)
            )
            Text(text = data.location.name, fontSize = 30.sp)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = data.location.country, fontSize = 18.sp, color = Gray)

        }
    }
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = "${data.current.temp_c} ° c", fontSize = 56.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = "${data.location.country} ° c", fontSize = 56.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
}