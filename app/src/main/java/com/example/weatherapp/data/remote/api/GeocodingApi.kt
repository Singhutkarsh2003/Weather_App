package com.example.weatherapp.data.remote.api

import com.example.weatherapp.data.remote.dto.GeoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GeocodingApi {

    @GET("v1/search")
    suspend fun  searchCity(

        @Query("name")
        city: String

    ): GeoResponse

}