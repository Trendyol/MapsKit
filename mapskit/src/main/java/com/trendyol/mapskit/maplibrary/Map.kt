package com.trendyol.mapskit.maplibrary

import android.os.Bundle
import android.view.View
import com.trendyol.mapskit.maplibrary.listeners.IOnCameraIdleListener
import com.trendyol.mapskit.maplibrary.listeners.IOnCameraMoveStartedListener
import com.trendyol.mapskit.maplibrary.listeners.IOnMapClickListener
import com.trendyol.mapskit.maplibrary.listeners.IOnMapLoadedCallback
import com.trendyol.mapskit.maplibrary.listeners.IOnMapReadyCallback
import com.trendyol.mapskit.maplibrary.listeners.IOnMarkerClickListener
import com.trendyol.mapskit.maplibrary.model.CameraPosition
import com.trendyol.mapskit.maplibrary.model.Marker
import com.trendyol.mapskit.maplibrary.model.MarkerOptions

interface Map {

    fun onCreate(bundle: Bundle?)
    fun getMapView(): View?
    fun getMapAsync(onMapReadyCallback: IOnMapReadyCallback)
    fun setCompassEnabled(isCompassEnabled: Boolean)
    fun setAllGesturesEnabled(allGesturesEnabled: Boolean)
    fun setMyLocationButtonEnabled(isMyLocationButtonEnabled: Boolean)
    fun setMyLocationEnabled(isMyLocationEnabled: Boolean)
    fun setMinZoomPreference(zoomLevel: Float)
    fun setLiteMode(isLiteModeEnabled: Boolean)
    fun setOnMapClickListener(onMapClickListener: IOnMapClickListener)
    fun animateCamera(cameraUpdate: CameraUpdate, duration: Int? = null)
    fun moveCamera(cameraUpdate: CameraUpdate)
    fun getCameraPosition(): CameraPosition
    fun setOnMarkerClickListener(onMarkerClickListener: IOnMarkerClickListener)
    fun setOnMapLoadedCallback(onMapLoadedListener: IOnMapLoadedCallback)
    fun setOnCameraIdleListener(onCameraIdleListener: IOnCameraIdleListener)
    fun setOnCameraMoveStartedListener(onCameraMoveStartedListener: IOnCameraMoveStartedListener)
    fun addMarker(markerOptions: MarkerOptions, tag: Any?): Marker?
    fun clear()
}