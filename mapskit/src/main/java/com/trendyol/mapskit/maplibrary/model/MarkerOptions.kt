package com.trendyol.mapskit.maplibrary.model

import android.graphics.Bitmap

data class MarkerOptions(
    val position: LatLng,
    val draggable: Boolean,
    val bitmap: Bitmap?
)
