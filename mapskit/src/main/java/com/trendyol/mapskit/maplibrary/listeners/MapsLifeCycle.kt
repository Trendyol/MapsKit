package com.trendyol.mapskit.maplibrary.listeners

import android.os.Bundle

interface MapsLifeCycle {

    fun onSaveInstanceState(bundle: Bundle)
    fun onStart()
    fun onResume()
    fun onPause()
    fun onStop()
    fun onDestroy()
    fun onLowMemory()
}