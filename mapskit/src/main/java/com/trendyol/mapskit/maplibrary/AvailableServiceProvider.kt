package com.trendyol.mapskit.maplibrary

import android.content.Context
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.huawei.hms.api.HuaweiApiAvailability

object AvailableServiceProvider {

    private var availableService: AvailableService? = null

    /**
     * Returns available service.
     *
     * If both Google and Huawei services are available or both API's are not available
     * will return Google.
     *
     * @param context will be used to check available API.
     */
    fun getAvailableService(context: Context): AvailableService {
        return availableService ?: when (ConnectionResult.SUCCESS) {
            GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) -> AvailableService.GOOGLE
            HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(context) -> AvailableService.HUAWEI
            else -> AvailableService.GOOGLE
        }.also { availableService = it }
    }
}
