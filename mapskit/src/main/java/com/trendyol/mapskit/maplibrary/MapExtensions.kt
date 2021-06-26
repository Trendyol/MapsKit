package com.trendyol.mapskit.maplibrary

import com.trendyol.mapskit.maplibrary.model.LatLng
import com.trendyol.mapskit.maplibrary.model.Marker
import com.trendyol.mapskit.maplibrary.model.MarkerOptions

import com.google.android.gms.maps.model.BitmapDescriptorFactory as GoogleBitmapDescription
import com.google.android.gms.maps.model.LatLng as GoogleLatLng
import com.google.android.gms.maps.model.Marker as GoogleMarker
import com.google.android.gms.maps.model.MarkerOptions as GoogleMarkerOptions
import com.huawei.hms.maps.model.BitmapDescriptorFactory as HuaweiBitmapDescription
import com.huawei.hms.maps.model.LatLng as HuaweiLatLng
import com.huawei.hms.maps.model.Marker as HuaweiMarker
import com.huawei.hms.maps.model.MarkerOptions as HuaweiMarkerOptions

fun GoogleLatLng.toMapsKitLatLng(): LatLng =
    LatLng(latitude, longitude)

fun HuaweiLatLng.toMapsKitLatLng(): LatLng =
    LatLng(latitude, longitude)

fun LatLng.toGoogleLatLng(): GoogleLatLng =
    GoogleLatLng(latitude, longitude)

fun LatLng.toHuaweiLatLng(): HuaweiLatLng =
    HuaweiLatLng(latitude, longitude)

fun MarkerOptions.toGoogleMarkerOptions(): GoogleMarkerOptions =
    GoogleMarkerOptions()
        .position(position.toGoogleLatLng())
        .draggable(draggable)
        .icon(GoogleBitmapDescription.fromBitmap(bitmap))

fun MarkerOptions.toHuaweiMarkerOptions(): HuaweiMarkerOptions =
    HuaweiMarkerOptions()
        .position(position.toHuaweiLatLng())
        .draggable(draggable)
        .icon(HuaweiBitmapDescription.fromBitmap(bitmap))

fun GoogleMarker.toMapsKitMarker(): Marker =
    Marker(
        id = id,
        snippet = snippet,
        title = title,
        position = position.toMapsKitLatLng(),
        alpha = alpha,
        rotation = rotation,
        tag = tag
    )

fun HuaweiMarker.toMapsKitMarker(): Marker =
    Marker(
        id = id,
        snippet = snippet,
        title = title,
        position = position.toMapsKitLatLng(),
        alpha = alpha,
        rotation = rotation,
        tag = tag
    )
