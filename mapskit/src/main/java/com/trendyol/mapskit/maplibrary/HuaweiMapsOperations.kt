package com.trendyol.mapskit.maplibrary

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresPermission
import com.huawei.hms.maps.HuaweiMap
import com.huawei.hms.maps.HuaweiMapOptions
import com.huawei.hms.maps.MapView
import com.huawei.hms.maps.OnMapReadyCallback
import com.trendyol.mapskit.maplibrary.listeners.IMapsLifeCycle
import com.trendyol.mapskit.maplibrary.listeners.IOnCameraIdleListener
import com.trendyol.mapskit.maplibrary.listeners.IOnCameraMoveStartedListener
import com.trendyol.mapskit.maplibrary.listeners.IOnMapClickListener
import com.trendyol.mapskit.maplibrary.listeners.IOnMapLoadedCallback
import com.trendyol.mapskit.maplibrary.listeners.IOnMapReadyCallback
import com.trendyol.mapskit.maplibrary.listeners.IOnMarkerClickListener
import com.trendyol.mapskit.maplibrary.model.CameraPosition
import com.trendyol.mapskit.maplibrary.model.LatLng
import com.trendyol.mapskit.maplibrary.model.Marker
import com.trendyol.mapskit.maplibrary.model.MarkerOptions

class HuaweiMapsOperations(context: Context) :
    Map,
    OnMapReadyCallback,
    IMapsLifeCycle {

    private lateinit var huaweiMap: HuaweiMap
    private var mapView: MapView? = null
    private lateinit var onMapReadyListener: IOnMapReadyCallback
    private val cameraUpdateProvider = HuaweiCameraUpdateProvider()
    private var isLiteModeEnabled: Boolean? = null

    init {
        mapView = MapView(context)
    }

    override fun onMapReady(map: HuaweiMap) {
        huaweiMap = map
        onMapReadyListener.onMapReady(this)
    }

    override fun getMapView(): View? {
        return mapView
    }

    override fun getMapAsync(onMapReadyCallback: IOnMapReadyCallback) {
        this.onMapReadyListener = onMapReadyCallback
        mapView?.getMapAsync(this)
    }

    override fun onCreate(bundle: Bundle?) {
        mapView?.onCreate(bundle)
    }

    override fun setCompassEnabled(isCompassEnabled: Boolean) {
        huaweiMap.uiSettings?.isCompassEnabled = isCompassEnabled
    }

    override fun setAllGesturesEnabled(allGesturesEnabled: Boolean) {
        huaweiMap.uiSettings?.setAllGesturesEnabled(allGesturesEnabled)
    }

    override fun setMyLocationButtonEnabled(isMyLocationButtonEnabled: Boolean) {
        huaweiMap.uiSettings?.isMyLocationButtonEnabled = isMyLocationButtonEnabled
    }

    @RequiresPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
    override fun setMyLocationEnabled(isMyLocationEnabled: Boolean) {
        huaweiMap.isMyLocationEnabled = isMyLocationEnabled
    }

    override fun setMinZoomPreference(zoomLevel: Float) {
        huaweiMap.setMinZoomPreference(zoomLevel)
    }

    override fun setOnMapClickListener(onMapClickListener: IOnMapClickListener) {
        huaweiMap.setOnMapClickListener { latLng -> onMapClickListener.onMapClick(latLng.toMapsKitLatLng()) }
    }

    override fun animateCamera(cameraUpdate: CameraUpdate, duration: Int?) {
        val huaweiCameraUpdate = cameraUpdateProvider.provide(cameraUpdate)
        if (duration == null) {
            huaweiMap.animateCamera(huaweiCameraUpdate)
        } else {
            huaweiMap.animateCamera(huaweiCameraUpdate, duration, null)
        }
    }

    override fun moveCamera(cameraUpdate: CameraUpdate) {
        val huaweiCameraUpdate = cameraUpdateProvider.provide(cameraUpdate)
        huaweiMap.moveCamera(huaweiCameraUpdate)
    }

    override fun getCameraPosition(): CameraPosition {
        val cameraPosition = huaweiMap.cameraPosition
        return CameraPosition(
            LatLng(cameraPosition.target.latitude, cameraPosition.target.longitude), cameraPosition.zoom
        )
    }

    override fun setOnMarkerClickListener(onMarkerClickListener: IOnMarkerClickListener) {
        huaweiMap.setOnMarkerClickListener {
            onMarkerClickListener.onMarkerClick(it.toMapsKitMarker())
        }
    }

    override fun setOnMapLoadedCallback(onMapLoadedListener: IOnMapLoadedCallback) {
        huaweiMap.setOnMapLoadedCallback {
            onMapLoadedListener.onMapLoaded()
        }
    }

    override fun setOnCameraIdleListener(onCameraIdleListener: IOnCameraIdleListener) {
        huaweiMap.setOnCameraIdleListener {
            onCameraIdleListener.onCameraIdle()
        }
    }

    override fun setOnCameraMoveStartedListener(onCameraMoveStartedListener: IOnCameraMoveStartedListener) {
        huaweiMap.setOnCameraMoveStartedListener {
            val reason = MapCameraReason.of(it)
            onCameraMoveStartedListener.onCameraMoveStarted(reason)
        }
    }

    override fun addMarker(markerOptions: MarkerOptions, tag: Any?): Marker? {
        val huaweiMarker = huaweiMap.addMarker(markerOptions.toHuaweiMarkerOptions()) ?: return null
        huaweiMarker.tag = tag
        return huaweiMarker.toMapsKitMarker()
    }

    override fun setLiteMode(isLiteModeEnabled: Boolean) {
        if (::huaweiMap.isInitialized) {
            val options = HuaweiMapOptions().liteMode(isLiteModeEnabled)
            huaweiMap.mapType = options.mapType
        } else {
            this.isLiteModeEnabled = isLiteModeEnabled
        }
    }

    override fun clear() {
        huaweiMap.clear()
    }

    override fun onSaveInstanceState(bundle: Bundle) {
        mapView?.onSaveInstanceState(bundle)
    }

    override fun onStart() {
        mapView?.onStart()
    }

    override fun onResume() {
        mapView?.onResume()
    }

    override fun onPause() {
        mapView?.onPause()
    }

    override fun onStop() {
        mapView?.onStop()
    }

    override fun onDestroy() {
        mapView?.onDestroy()
    }

    override fun onLowMemory() {
        mapView?.onLowMemory()
    }

}