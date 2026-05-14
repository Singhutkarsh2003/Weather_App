package com.example.weatherapp.presentation.weather

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherapp.presentation.navigation.Routes
import com.example.weatherapp.presentation.weather.components.EmptyView
import com.example.weatherapp.presentation.weather.components.ErrorView
import com.example.weatherapp.presentation.weather.components.LoadingView
import com.example.weatherapp.presentation.weather.components.SearchBar
import com.example.weatherapp.presentation.weather.components.SuggestionItem
import com.example.weatherapp.presentation.weather.components.WeatherCard
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@Composable
fun WeatherScreen(

    navController: NavController,

    viewModel: WeatherViewModel =
        hiltViewModel()

) {

    val state by viewModel.state.collectAsState()

    var query by remember { mutableStateOf("") }

    Column(

        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {

        Text(
            text = "WeatherSnap",
            style = MaterialTheme.typography.headlineMedium
        )
        SearchBar(
            query = query,
            onQueryChange = {query = it
                viewModel.searchCities(it)
            },
            modifier = Modifier.fillMaxWidth()
        )
        if (state.isLoading){
            LoadingView()
        }
        state.error ?.let {
            ErrorView(message = it)
        }
        if(state.cities.isEmpty() && state.weather == null &&  !state.isLoading){
            EmptyView()
        }

        LazyColumn {

            items(state.cities) { city ->

               SuggestionItem(
                   city = city,
                   onClick = {
                       viewModel.loadWeather(city)
                   }
               )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        state.weather?.let { weather ->

            WeatherCard(weather = weather)

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    val city = URLEncoder.encode(
                        weather.cityName,
                        StandardCharsets.UTF_8.toString()
                    )
                    navController.navigate(
                        "create_report/"+
                                "$city/"+
                                "${weather.temperature}/"+
                                "${weather.humidity}/"+
                                "${weather.pressure}/"+
                                "${weather.windSpeed}"
                    )
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Create Report")
            }

        }

        Button(
            onClick = {
                navController.navigate(
                    Routes.REPORTS
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {

            Text("Saved Reports")
        }
    }
}