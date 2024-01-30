package dev.kdrag0n.safetynetfix

import android.os.Build

internal object BuildHooks {
    fun init() {
        val spoofedProduct = "husky"
        val spoofedDevice = "husky"
        val spoofedModel = "Pixel 8 Pro"

        logDebug("Patch PRODUCT prop. Set it to: $spoofedProduct")
        Build::class.java.getDeclaredField("PRODUCT").let { field ->
            field.isAccessible = true
            field.set(null, spoofedProduct)
        }
        logDebug("Patch DEVICE prop. Set it to: $spoofedDevice")
        Build::class.java.getDeclaredField("DEVICE").let { field ->
            field.isAccessible = true
            field.set(null, spoofedDevice)
        }
        logDebug("Patch MODEL prop. Set it to: $spoofedModel")
        Build::class.java.getDeclaredField("MODEL").let { field ->
            field.isAccessible = true
            field.set(null, spoofedModel)
        }
    }
}
