package com.trendyol.mapskit.maplibrary

import com.trendyol.mapskit.maplibrary.model.LatLng

sealed class CameraUpdate

data class NewLatLng(val latLng: LatLng) : CameraUpdate()
data class NewLatLngZoom(val latLng: LatLng, val zoomLevel: Float) : CameraUpdate()
data class NewLatLngBounds(val latLng: List<LatLng>, val padding: Int) : CameraUpdate()
