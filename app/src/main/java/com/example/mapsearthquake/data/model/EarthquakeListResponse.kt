package com.example.mapsearthquake.data.model


data class EarthquakeListResponse(
    var result: List<Result>,
    var status: Boolean
) {
    data class Result(
        var coordinates: List<Double>? = null,
        var date: String? = null,
        var dateStamp: String? = null,
        var depth: Double? = null,
        var hash: String? = null,
        var hash2: String? = null,
        var lat: Double? = null,
        var lng: Double? = null,
        var locationTz: String? = null,
        var mag: Double? = null,
        var rev: Any?? = null,
        var timestamp: Int? = null,
        var title: String? = null,
        var id: String? = null,
        var earthquakeId: String? = null,
        var provider: String? = null,
        var dateTime: String? = null,
        var createdAt: Int? = null,
        var locationProperties: LocationProperties? = null
    )
}