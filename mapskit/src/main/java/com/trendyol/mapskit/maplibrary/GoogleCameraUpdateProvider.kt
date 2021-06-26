package com.trendyol.mapskit.maplibrary

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.CameraUpdate as GoogleCameraUpdate

class GoogleCameraUpdateProvider {

    fun provide(cameraUpdate: CameraUpdate): GoogleCameraUpdate {
        return when (cameraUpdate) {
            is CameraUpdate.NewLatLng -> CameraUpdateFactory.newLatLng(cameraUpdate.latLng.toGoogleLatLng())
            is CameraUpdate.NewLatLngZoom -> CameraUpdateFactory.newLatLngZoom(
                cameraUpdate.latLng.toGoogleLatLng(), cameraUpdate.zoomLevel
            )
            is CameraUpdate.NewLatLngBounds -> {
                val latLngBounds = LatLngBounds.builder()
                    .apply { cameraUpdate.latLng.forEach { include(it.toGoogleLatLng()) } }
                    .build()
                CameraUpdateFactory.newLatLngBounds(latLngBounds, cameraUpdate.padding)
            }
        }
    }
}
