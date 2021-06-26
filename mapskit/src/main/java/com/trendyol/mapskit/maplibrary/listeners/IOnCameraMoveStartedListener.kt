package com.trendyol.mapskit.maplibrary.listeners

import com.trendyol.mapskit.maplibrary.MapCameraReason

fun interface IOnCameraMoveStartedListener {

    fun onCameraMoveStarted(reason: MapCameraReason)
}
