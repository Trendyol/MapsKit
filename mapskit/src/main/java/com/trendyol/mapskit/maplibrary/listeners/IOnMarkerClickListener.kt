package com.trendyol.mapskit.maplibrary.listeners

import com.trendyol.mapskit.maplibrary.model.Marker

fun interface IOnMarkerClickListener {

    fun onMarkerClick(marker: Marker): Boolean
}