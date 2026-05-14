package com.example.weatherapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherapp.data.local.entity.WeatherReportEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ReportDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertReport(
        report : WeatherReportEntity
    )

    @Query(
        "SELECT * FROM weather_reports ORDER BY timestamp DESC")
    fun  getReports() : Flow<List<WeatherReportEntity>>
}