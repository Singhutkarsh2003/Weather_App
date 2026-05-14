package com.example.weatherapp.data.local.mapper

import com.example.weatherapp.data.remote.dto.CityDto
import com.example.weatherapp.domain.model.City

fun CityDto.toCity() : City {

    return City(

        name = name ?: "",
        latitude = latitude ?: 0.0,
        longitude = longitude ?:0.0,
        country = country?: "",
        state = admin1 ?: ""

    )


}