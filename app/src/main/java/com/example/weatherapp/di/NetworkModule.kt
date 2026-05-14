package com.example.weatherapp.di

import com.example.weatherapp.data.remote.api.GeocodingApi
import com.example.weatherapp.data.remote.api.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule{

    private const val WEATHER_BASE_URL = "https://api.open-meteo.com/"

    private const val  GEO_BASE_URL = "https://geocoding-api.open-meteo.com/"

    @Provides
    @Singleton
    fun providerLoggingInterceptor(): HttpLoggingInterceptor{

        return HttpLoggingInterceptor().apply {

            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        interceptor: HttpLoggingInterceptor
    ): OkHttpClient{

        return OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideWeatherApi(client: OkHttpClient): WeatherApi {

        return Retrofit.Builder()
            .baseUrl(WEATHER_BASE_URL)
            .client(client)
            .addConverterFactory(
                GsonConverterFactory.create()
            ).build().create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGeocodingApi(client: OkHttpClient): GeocodingApi {

        return Retrofit.Builder()
            .baseUrl(GEO_BASE_URL)
            .client(client)
            .addConverterFactory(
            GsonConverterFactory.create()
        ).build().create(GeocodingApi::class.java)

    }

}