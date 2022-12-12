package com.trendyol.mapskit.maplibrary

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import com.trendyol.mapskit.maplibrary.listeners.IMapsLifeCycle
import com.trendyol.mapskit.maplibrary.listeners.IOnCameraIdleListener
import com.trendyol.mapskit.maplibrary.listeners.IOnCameraMoveStartedListener
import com.trendyol.mapskit.maplibrary.listeners.IOnMapClickListener
import com.trendyol.mapskit.maplibrary.listeners.IOnMapLoadedCallback
import com.trendyol.mapskit.maplibrary.listeners.IOnMapReadyCallback
import com.trendyol.mapskit.maplibrary.listeners.IOnMarkerClickListener
import com.trendyol.mapskit.maplibrary.model.CameraPosition
import com.trendyol.mapskit.maplibrary.model.Marker
import com.trendyol.mapskit.maplibrary.model.MarkerOptions

class MapView : FrameLayout, Map, IMapsLifeCycle {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val availableService = AvailableServiceProvider.getAvailableService(context)

    private val mapOperation: Map = if (availableService == AvailableService.GOOGLE) {
        GoogleMapsOperations(context)
    } else {
        HuaweiMapsOperations(context)
    }

    init {
        val view: View? = mapOperation.getMapView()
        addView(view)
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_UP -> parent.requestDisallowInterceptTouchEvent(false)
            MotionEvent.ACTION_DOWN -> parent.requestDisallowInterceptTouchEvent(true)
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onCreate(bundle: Bundle?) {
        var mapViewBundle = bundle?.getBundle(BUNDLE_KEY)
        if (mapViewBundle == null) {
            mapViewBundle = Bundle()
            bundle?.putBundle(BUNDLE_KEY, mapViewBundle)
        }
        mapOperation.onCreate(mapViewBundle)
    }

    override fun getMapView(): View? {
        return mapOperation.getMapView()
    }

    override fun getMapAsync(onMapReadyCallback: IOnMapReadyCallback) {
        mapOperation.getMapAsync(onMapReadyCallback)
    }

    override fun setCompassEnabled(isCompassEnabled: Boolean) {
        mapOperation.setCompassEnabled(isCompassEnabled)
    }

    override fun setAllGesturesEnabled(allGesturesEnabled: Boolean) {
        mapOperation.setAllGesturesEnabled(allGesturesEnabled)
    }

    override fun setMyLocationButtonEnabled(isMyLocationButtonEnabled: Boolean) {
        mapOperation.setMyLocationButtonEnabled(isMyLocationButtonEnabled)
    }

    override fun setMyLocationEnabled(isMyLocationEnabled: Boolean) {
        mapOperation.setMyLocationEnabled(isMyLocationEnabled)
    }

    override fun setMinZoomPreference(zoomLevel: Float) {
        mapOperation.setMinZoomPreference(zoomLevel)
    }

    override fun setOnMapClickListener(onMapClickListener: IOnMapClickListener) {
        mapOperation.setOnMapClickListener(onMapClickListener)
    }

    override fun animateCamera(cameraUpdate: CameraUpdate, duration: Int?) {
        mapOperation.animateCamera(cameraUpdate, duration)
    }

    override fun moveCamera(cameraUpdate: CameraUpdate) {
        mapOperation.moveCamera(cameraUpdate)
    }

    override fun getCameraPosition(): CameraPosition {
        return mapOperation.getCameraPosition()
    }

    override fun setOnMarkerClickListener(onMarkerClickListener: IOnMarkerClickListener) {
        mapOperation.setOnMarkerClickListener(onMarkerClickListener)
    }

    override fun setOnMapLoadedCallback(onMapLoadedListener: IOnMapLoadedCallback) {
        mapOperation.setOnMapLoadedCallback(onMapLoadedListener)
    }

    override fun setOnCameraIdleListener(onCameraIdleListener: IOnCameraIdleListener) {
        mapOperation.setOnCameraIdleListener(onCameraIdleListener)
    }

    override fun setOnCameraMoveStartedListener(onCameraMoveStartedListener: IOnCameraMoveStartedListener) {
        mapOperation.setOnCameraMoveStartedListener(onCameraMoveStartedListener)
    }

    override fun setLiteMode(isLiteModeEnabled: Boolean) {
        mapOperation.setLiteMode(isLiteModeEnabled)
    }

    override fun addMarker(markerOptions: MarkerOptions, tag: Any?): Marker? {
        return mapOperation.addMarker(markerOptions, tag)
    }

    override fun clear() {
        mapOperation.clear()
    }

    override fun onSaveInstanceState(bundle: Bundle) {
        (mapOperation as IMapsLifeCycle).onSaveInstanceState(bundle)
    }

    override fun onStart() {
        (mapOperation as IMapsLifeCycle).onStart()
    }

    override fun onResume() {
        (mapOperation as IMapsLifeCycle).onResume()
    }

    override fun onPause() {
        (mapOperation as IMapsLifeCycle).onPause()
    }

    override fun onStop() {
        (mapOperation as IMapsLifeCycle).onStop()
    }

    override fun onDestroy() {
        (mapOperation as IMapsLifeCycle).onDestroy()
    }

    override fun onLowMemory() {
        (mapOperation as IMapsLifeCycle).onLowMemory()
    }

    companion object {

        const val BUNDLE_KEY = "MapViewBundleKey"
    }
}