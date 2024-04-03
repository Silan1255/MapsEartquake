package com.example.mapsearthquake.data.model


import com.google.gson.annotations.SerializedName

data class EarthquakeListResponse(
    @SerializedName("result") var result: List<Result>,
    @SerializedName("status") var status: Boolean
) {
    data class Result(
        @SerializedName("coordinates") var coordinates: List<Double>,
        @SerializedName("date") var date: String,
        @SerializedName("date_stamp") var dateStamp: String,
        @SerializedName("depth") var depth: Double,
        @SerializedName("hash") var hash: String,
        @SerializedName("hash2") var hash2: String,
        @SerializedName("lat") var lat: Double,
        @SerializedName("lng") var lng: Double,
        @SerializedName("location_tz") var locationTz: String,
        @SerializedName("mag") var mag: Double,
        @SerializedName("rev") var rev: Any?,
        @SerializedName("timestamp") var timestamp: Int,
        @SerializedName("title") var title: String,
        @SerializedName("id") var id: String,
        @SerializedName("earthquake_id") var earthquakeId: String,
        @SerializedName("provider") var provider: String,
        @SerializedName("date_time") var dateTime: String,
        @SerializedName("created_at") var createdAt: Int,
        @SerializedName("location_properties") var locationProperties: LocationProperties
        )
}