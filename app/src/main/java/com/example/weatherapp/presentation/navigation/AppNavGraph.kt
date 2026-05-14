package com.example.weatherapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weatherapp.presentation.camera.CameraScreen
import com.example.weatherapp.presentation.report.CreateReportScreen
import com.example.weatherapp.presentation.savedreports.SavedReportsScreen
import com.example.weatherapp.presentation.weather.WeatherScreen



@Composable
fun AppNavGraph() {

    val navController =
        rememberNavController()

    NavHost(

        navController = navController,

        startDestination =
            Routes.WEATHER

    ) {

        composable(

            route = Routes.WEATHER

        ) {

            WeatherScreen(
                navController = navController
            )
        }

        composable(

            route =
                "create_report/" +
                        "{city}/" +
                        "{temperature}/" +
                        "{humidity}/" +
                        "{pressure}/" +
                        "{windSpeed}",

            arguments = listOf(

                navArgument("city") {

                    type = NavType.StringType
                },

                navArgument("temperature") {

                    type = NavType.StringType
                },

                navArgument("humidity") {

                    type = NavType.StringType
                },

                navArgument("pressure") {

                    type = NavType.StringType
                },

                navArgument("windSpeed") {

                    type = NavType.StringType
                }
            )

        ) { backStackEntry ->

            CreateReportScreen(

                navController =
                    navController,

                city =
                    backStackEntry.arguments
                        ?.getString("city")
                        ?: "",

                temperature =
                    backStackEntry.arguments
                        ?.getString("temperature")
                        ?: "",

                humidity =
                    backStackEntry.arguments
                        ?.getString("humidity")
                        ?: "",

                pressure =
                    backStackEntry.arguments
                        ?.getString("pressure")
                        ?: "",

                windSpeed =
                    backStackEntry.arguments
                        ?.getString("windSpeed")
                        ?: ""
            )
        }

        composable(

            route = Routes.REPORTS

        ) {

            SavedReportsScreen()
        }

        composable(

            route = Routes.CAMERA

        ) {

            CameraScreen()
        }
    }
}