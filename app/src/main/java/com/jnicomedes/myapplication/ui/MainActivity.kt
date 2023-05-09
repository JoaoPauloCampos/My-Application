package com.jnicomedes.myapplication.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jnicomedes.myapplication.ui.theme.MyComposeApplicationTheme
import com.jnicomedes.myapplication.ui.weather.ListScreen
import com.jnicomedes.myapplication.ui.weather.WeatherScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyComposeApplicationTheme {
                MainScreen()
            }
        }
    }

    @Composable
    fun MainScreen() {
        val navController = rememberNavController()
        Navigation(navController)
    }
}

@Composable
fun Navigation(navController: NavHostController, destination: String = "home") {
    NavHost(navController, startDestination = destination) {
        composable("home") { WeatherScreen { navController.navigate("list") } }
        composable("list") { ListScreen { navController.navigate("home2") } }
        composable("home2") { WeatherScreen { navController.navigate("list") } }
    }
}