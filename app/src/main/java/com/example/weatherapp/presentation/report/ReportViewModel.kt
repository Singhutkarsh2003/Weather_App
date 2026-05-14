package com.example.weatherapp.presentation.report

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.model.WeatherReport
import com.example.weatherapp.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReportViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    fun saveReport(
        report: WeatherReport
    ) {
        viewModelScope.launch {
            repository.saveReport(report)
        }
    }
}