package com.trendyol.mapskit.maplibrary

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresPermission
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.trendyol.mapskit.maplibrary.listeners.*
import com.trendyol.mapskit.maplibrary.model.CameraPosition
import com.trendyol.mapskit.maplibrary.model.LatLng
import com.trendyol.mapskit.maplibrary.model.Marker
import com.trendyol.mapskit.maplibrary.model.MarkerOptions

class GoogleMapsOperations(context: Context) :
    Map,
    OnMapReadyCallback,
    MapsLifeCycle {

    private var googleMap: GoogleMap? = null
    private var mapView: MapView? = null
    private var onMapReadyListener: IOnMapReadyCallback? = null
    private val cameraUpdateProvider = GoogleCameraUpdateProvider()
    private var isLiteModeEnabled: Boolean? = null

    init {
        mapView = MapView(context)
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        isLiteModeEnabled?.let { setLiteMode(it) }
        onMapReadyListener?.onMapReady(this)
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
        googleMap?.uiSettings?.isCompassEnabled = isCompassEnabled
    }

    override fun setAllGesturesEnabled(allGesturesEnabled: Boolean) {
        googleMap?.uiSettings?.setAllGesturesEnabled(allGesturesEnabled)
    }

    override fun setMyLocationButtonEnabled(isMyLocationButtonEnabled: Boolean) {
        googleMap?.uiSettings?.isMyLocationButtonEnabled = isMyLocationButtonEnabled
    }

    @RequiresPermission(ACCESS_COARSE_LOCATION)
    override fun setMyLocationEnabled(isMyLocationEnabled: Boolean) {
        googleMap?.isMyLocationEnabled = isMyLocationEnabled
    }

    override fun setMinZoomPreference(zoomLevel: Float) {
        googleMap?.setMinZoomPreference(zoomLevel)
    }

    override fun getCameraPosition(): CameraPosition {
        return CameraPosition(
            target = googleMap?.cameraPosition?.target?.toMapsKitLatLng() ?: LatLng(
                DEFAULT_LATITUDE,
                DEFAULT_LONGITUDE
            ),
            zoom = googleMap?.cameraPosition?.zoom ?: ZOOM_LEVEL_STREET
        )
    }

    override fun setOnMapClickListener(onMapClickListener: IOnMapClickListener) {
        googleMap?.setOnMapClickListener { latLng -> onMapClickListener.onMapClick(latLng.toMapsKitLatLng()) }
    }

    override fun animateCamera(cameraUpdate: CameraUpdate, duration: Int?) {
        val googleCameraUpdate = cameraUpdateProvider.provide(cameraUpdate)
        if (duration == null) {
            googleMap?.animateCamera(googleCameraUpdate)
        } else {
            googleMap?.animateCamera(googleCameraUpdate, duration, null)
        }
    }

    override fun moveCamera(cameraUpdate: CameraUpdate) {
        val googleCameraUpdate = cameraUpdateProvider.provide(cameraUpdate)
        googleMap?.moveCamera(googleCameraUpdate)
    }

    override fun setOnMarkerClickListener(onMarkerClickListener: IOnMarkerClickListener) {
        googleMap?.setOnMarkerClickListener {
            onMarkerClickListener.onMarkerClick(it.toMapsKitMarker())
        }
    }

    override fun setOnMapLoadedCallback(onMapLoadedListener: IOnMapLoadedCallback) {
        googleMap?.setOnMapLoadedCallback {
            onMapLoadedListener.onMapLoaded()
        }
    }

    override fun setOnCameraIdleListener(onCameraIdleListener: IOnCameraIdleListener) {
        googleMap?.setOnCameraIdleListener {
            onCameraIdleListener.onCameraIdle()
        }
    }

    override fun setOnCameraMoveStartedListener(onCameraMoveStartedListener: IOnCameraMoveStartedListener) {
        googleMap?.setOnCameraMoveStartedListener {
            val reason = MapCameraReason.of(it)
            onCameraMoveStartedListener.onCameraMoveStarted(reason)
        }
    }

    override fun addMarker(markerOptions: MarkerOptions, tag: Any?): Marker? {
        val googleMarker =
            googleMap?.addMarker(markerOptions.toGoogleMarkerOptions()) ?: return null
        googleMarker.tag = tag
        googleMarker.title = markerOptions.title
        return googleMarker.toMapsKitMarker()
    }

    override fun setLiteMode(isLiteModeEnabled: Boolean) {
        if (googleMap != null) {
            val options = GoogleMapOptions().liteMode(isLiteModeEnabled)
            googleMap?.mapType = options.mapType
        } else {
            this.isLiteModeEnabled = isLiteModeEnabled
        }
    }

    override fun setOnZoomControlsListener(isZoomControlsEnabled: Boolean) {
        googleMap?.uiSettings?.isZoomControlsEnabled = isZoomControlsEnabled
    }

    override fun clear() {
        googleMap?.clear()
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

    companion object {
        const val ZOOM_LEVEL_STREET = 18F
        const val DEFAULT_LATITUDE = 41.046555
        const val DEFAULT_LONGITUDE = 29.033402
    }
}
