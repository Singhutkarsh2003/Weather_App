package com.example.weatherapp.presentation.savedreports

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.model.WeatherReport
import com.example.weatherapp.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SavedReportsViewModel @Inject constructor(

    private val repository: WeatherRepository

) : ViewModel() {

    private val _reports = MutableStateFlow<List<WeatherReport>>(emptyList())

    val reports:
            StateFlow<List<WeatherReport>> =
        _reports.asStateFlow()

    init {

        getReports()
    }

    private fun getReports() {

        viewModelScope.launch {

            repository.getReports()
                .collect {

                    _reports.value = it
                }
        }
    }
}