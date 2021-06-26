package com.trendyol.mapskit.maplibrary

enum class MapCameraReason(val shouldBother: Boolean) {
    GESTURE(true), API_ANIMATION(false), DEVELOPER_ANIMATION(false);

    companion object {

        fun of(value: Int): MapCameraReason = when (value) {
            1 -> GESTURE
            2 -> API_ANIMATION
            3 -> DEVELOPER_ANIMATION
            else -> throw IllegalArgumentException("Should be 1, 2 or 3")
        }
    }
}
