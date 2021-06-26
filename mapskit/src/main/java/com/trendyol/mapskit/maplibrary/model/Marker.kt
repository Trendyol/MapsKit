package com.trendyol.mapskit.maplibrary.model

data class Marker(
    val id: String?,
    val alpha: Float?,
    val rotation: Float?,
    val position: LatLng,
    val title: String?,
    val snippet: String?,
    val tag: Any?
)