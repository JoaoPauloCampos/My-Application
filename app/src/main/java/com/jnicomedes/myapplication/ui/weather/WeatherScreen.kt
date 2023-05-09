package com.jnicomedes.myapplication.ui.weather

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.jnicomedes.myapplication.data.domain.model.Weather
import org.koin.androidx.compose.koinViewModel

@Composable
fun WeatherScreen(viewModel: WeatherViewModel = koinViewModel(), openList: () -> Unit) {
    val weatherState: WeatherState by remember { viewModel.weatherState }
    when (val state = weatherState) {
        is WeatherState.Loading -> Loading()
        is WeatherState.Error -> WeatherError(state.errorMessage)
        is WeatherState.Success -> WeatherLayout(weather = state.data, openList)
    }
}

@Composable
fun Loading() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun WeatherError(message: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = message)
    }
}

@Composable
fun WeatherLayout(weather: Weather, openList: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Transparent
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = weather.title,
                fontWeight = FontWeight.Bold,
                style = TextStyle(fontSize = 24.sp),
                modifier = Modifier.padding(16.dp)
            )
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Row(modifier = Modifier.wrapContentSize()) {
                    Image(
                        painter = rememberAsyncImagePainter(weather.image),
                        contentDescription = null,
                        modifier = Modifier
                            .size(60.dp)
                            .clickable {
                                openList.invoke()
                            }
                    )
                    Text(
                        text = weather.currentTemp,
                        style = TextStyle(fontSize = 45.sp),
                    )
                }
                Text(
                    text = weather.condition,
                    style = TextStyle(fontSize = 24.sp)
                )
                Text(
                    text = "${weather.minTemp} ${weather.maxTemp}",
                    style = TextStyle(fontSize = 16.sp)
                )
            }
        }
    }
}