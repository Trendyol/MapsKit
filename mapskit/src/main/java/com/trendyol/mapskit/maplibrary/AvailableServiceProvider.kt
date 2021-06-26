package com.trendyol.mapskit.maplibrary

import android.content.Context
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.huawei.hms.api.HuaweiApiAvailability

object AvailableServiceProvider {

    private var availableService: AvailableService? = null

    fun getAvailableService(context: Context): AvailableService {
        if (availableService == null) {
            availableService = when (ConnectionResult.SUCCESS) {
                GoogleApiAvailability.getInstance()
                    .isGooglePlayServicesAvailable(
                        context
                    ) -> AvailableService.GOOGLE
                HuaweiApiAvailability.getInstance()
                    .isHuaweiMobileServicesAvailable(
                        context
                    ) -> AvailableService.HUAWEI
                else -> AvailableService.GOOGLE
            }
        }
        return availableService ?: AvailableService.GOOGLE
    }

}