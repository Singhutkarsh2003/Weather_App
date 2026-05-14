package com.example.weatherapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherapp.data.local.dao.ReportDao
import com.example.weatherapp.data.local.entity.WeatherReportEntity

@Database(
    entities = [WeatherReportEntity :: class], version = 1
)
abstract class AppDatabase  : RoomDatabase() {

    abstract  fun reportDao() : ReportDao

}