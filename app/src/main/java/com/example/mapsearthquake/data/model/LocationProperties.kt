package com.example.mapsearthquake.data.model

data class LocationProperties(
    val closestCity: ClosestCity,
    val closestCities: ArrayList<ClosestCities>,
    val epiCenter: EpiCenter,
    val airPorts: ArrayList<AirPorts>
)