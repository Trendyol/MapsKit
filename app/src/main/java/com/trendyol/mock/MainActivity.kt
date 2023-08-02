package com.trendyol.mock

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.huawei.hms.maps.MapsInitializer
import com.trendyol.mapskit.maplibrary.Map
import com.trendyol.mapskit.maplibrary.NewLatLngZoom
import com.trendyol.mapskit.maplibrary.listeners.IOnMapReadyCallback
import com.trendyol.mapskit.maplibrary.model.LatLng
import com.trendyol.mapskit.maplibrary.model.MarkerOptions
import com.trendyol.mock.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), IOnMapReadyCallback {

    private var map: Map? = null

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpMap()
    }

    private fun hasPermissions(context: Context, vararg permissions: String): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (permission in permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false
                }
            }
        }
        return true
    }

    private fun setUpMap() {
        binding.mapView.onCreate(null)
        binding.mapView.getMapAsync(this)
    }

    override fun onMapReady(map: Map) {
        Log.e("ECCO","onmapready")
        this.map = map
        val spineTowerLatLng = LatLng(41.1109069, 29.0232166)
        map.setCompassEnabled(false)
        map.setAllGesturesEnabled(true)
        setMarker(spineTowerLatLng)

    }

    private fun setMarker(latLng: LatLng) {
        val iconRes = bitmapFromVector(R.drawable.ic_location)

        /**
        You can pass null to bitmap to use default marker icon.
         */
        val markerOptions = MarkerOptions(
            position = latLng,
            draggable = true,
            bitmap = iconRes,
            title = "anywhere"
        )
        binding.mapView.addMarker(markerOptions, latLng)!!
        binding.mapView.setOnZoomControlsListener(true)
        binding.mapView.animateCamera(NewLatLngZoom(latLng, 10F))
    }

    override fun onStart() {
        super.onStart()
        binding.mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    private fun bitmapFromVector(vectorResId: Int): Bitmap? {
        return ContextCompat.getDrawable(this.applicationContext, vectorResId)?.run {
            setBounds(0, 0, intrinsicWidth, intrinsicHeight)
            val bitmap =
                Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
            draw(Canvas(bitmap))
            return bitmap
        }
    }
}