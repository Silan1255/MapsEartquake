package com.example.mapsearthquake.data.repository

import com.example.mapsearthquake.data.api.MainService
import com.example.mapsearthquake.data.model.EarthquakeListResponse
import javax.inject.Inject

class MainRepository @Inject constructor(private val mainService: MainService) {
    suspend fun getEarthquake(): EarthquakeListResponse {
        return mainService.getEarthquake()
    }
}