package com.dylanneve1.pixelplus

import android.os.Build

internal object BuildHooks {
    fun init() {
        val spoofedProduct = "husky"
        val spoofedDevice = "husky"
        val spoofedModel = "Pixel 8 Pro"
        val spoofedFingerprint = "google/husky/husky:14/AP1A.240305.019.A1/11445699:user/release-keys"

        logDebug("Spoof PRODUCT prop. Set it to: $spoofedProduct")
        Build::class.java.getDeclaredField("PRODUCT").let { field ->
            field.isAccessible = true
            field.set(null, spoofedProduct)
        }
        logDebug("Spoof DEVICE prop. Set it to: $spoofedDevice")
        Build::class.java.getDeclaredField("DEVICE").let { field ->
            field.isAccessible = true
            field.set(null, spoofedDevice)
        }
        logDebug("Spoof MODEL prop. Set it to: $spoofedModel")
        Build::class.java.getDeclaredField("MODEL").let { field ->
            field.isAccessible = true
            field.set(null, spoofedModel)
        }
        logDebug("Spoof FINGERPRINT prop. Set it to: $spoofedFingerprint")
        Build::class.java.getDeclaredField("FINGERPRINT").let { field ->
            field.isAccessible = true
            field.set(null, spoofedFingerprint)
        }
    }
}
