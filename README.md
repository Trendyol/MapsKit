# MapsKit

[![](https://jitpack.io/v/Trendyol/MapsKit.svg)](https://jitpack.io/#Trendyol/MapsKit)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)


MapsKit is a all-in-one library that supports both HMS and GMS maps.

## Installation

Add this repository to your project-level build.gradle file.

```groovy
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

Add this dependency to your app-level build.gradle file.

```
dependencies {
        implementation 'com.github.Trendyol:MapsKit:1.2'
}
```

## Usage

You'll still have to go through these steps at [HMS MapKit Codelab](https://developer.huawei.com/consumer/en/codelab/HMSMapKit/index.html#0) and [Google Add a map to your Android app](https://developers.google.com/codelabs/maps-platform/maps-platform-101-android#0)

Place the view in XML file.

```XML
<com.trendyol.mapskit.maplibrary.MapView
    android:id="@+id/mapView"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```

Then get your Fragment/Activity ready for map initialization.

```kotlin
class MyFragment: IOnMapReadyCallback {

    private var map: Map? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
    }

    override fun onMapReady(map: Map) {
        this.map = map
    }

    private fun moveCameraToPosition(latLng: LatLng) {
        map?.animateCamera(CameraUpdate.NewLatLngZoom(latLng, ZOOM_LEVEL_STREET))
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        mapView.onPause()
        super.onPause()
    }

    override fun onStop() {
        mapView.onStop()
        super.onStop()
    }

    override fun onDestroy() {
        mapView.onDestroy()
        super.onDestroy()
    }

    override fun onSaveInstanceState(bundle: Bundle) {
        mapView.onSaveInstanceState(bundle)
    }

}
```

License
--------


    Copyright 2021 Trendyol.com

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
