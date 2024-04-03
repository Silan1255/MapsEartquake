package com.example.mapsearthquake.data.model

data class ClosestCity(
    val cityCode: Int,
    val distance: Double,
    val name: String,
    val population: Int
)