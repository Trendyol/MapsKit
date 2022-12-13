package com.trendyol.mapskit.maplibrary

import com.huawei.hms.maps.CameraUpdateFactory
import com.huawei.hms.maps.model.LatLngBounds
import com.huawei.hms.maps.CameraUpdate as HuaweiCameraUpdate

class HuaweiCameraUpdateProvider {

    fun provide(cameraUpdate: CameraUpdate): HuaweiCameraUpdate {
        return when (cameraUpdate) {
            is NewLatLng -> CameraUpdateFactory.newLatLng(cameraUpdate.latLng.toHuaweiLatLng())
            is NewLatLngZoom -> CameraUpdateFactory.newLatLngZoom(
                cameraUpdate.latLng.toHuaweiLatLng(), cameraUpdate.zoomLevel
            )
            is NewLatLngBounds -> {
                val latLngBounds = LatLngBounds.builder()
                    .apply { cameraUpdate.latLng.forEach { include(it.toHuaweiLatLng()) } }
                    .build()
                CameraUpdateFactory.newLatLngBounds(latLngBounds, cameraUpdate.padding)
            }
        }
    }
}
