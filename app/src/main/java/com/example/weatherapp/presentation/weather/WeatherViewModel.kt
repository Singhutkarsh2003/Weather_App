package com.example.weatherapp.presentation.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.model.City
import com.example.weatherapp.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel  @Inject constructor(
    private val repository: WeatherRepository
): ViewModel(){

    private val _state = MutableStateFlow(WeatherUiState())

    val state: StateFlow<WeatherUiState> = _state.asStateFlow()

    private  val cityCache = mutableMapOf<String, List<City>>()

    fun searchCities(query: String){

        if(query.length <3){
            _state.value = _state.value.copy(cities = emptyList())
            return
        }
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                val cities = if(cityCache.containsKey(query)){
                    cityCache[query]?:emptyList()
                }else{
                    val result = repository.searchCities(query)
                    cityCache[query] = result
                    result
                }
                _state.value = _state.value.copy(
                    isLoading = false,
                    cities = cities,
                    error = null
                )
            }catch (e: Exception){
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun loadWeather(city: City){
        viewModelScope.launch {
            _state.value = _state.value.copy(
                isLoading = true
            )
            try {
                val weather = repository.getWeather(
                    latitude = city.latitude,
                    longitude = city.longitude,
                    cityName = city.name
                )
                _state.value = _state.value.copy(
                    isLoading = false,
                    weather = weather,
                    error = null
                )
            }catch (e: Exception){
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

}