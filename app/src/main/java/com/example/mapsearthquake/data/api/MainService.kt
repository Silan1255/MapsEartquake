package com.example.mapsearthquake.data.api

import com.example.mapsearthquake.data.model.EarthquakeListResponse
import retrofit2.http.GET

interface MainService {
    @GET(ApiConstants.END_POINTS)
    suspend fun getEarthquake(): EarthquakeListResponse
}