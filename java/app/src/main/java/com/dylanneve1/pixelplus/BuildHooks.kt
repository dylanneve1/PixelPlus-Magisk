package com.dylanneve1.pixelplus

import android.os.Build

internal object BuildHooks {
    fun init() {
        val product = "panther"
        val device = "panther"
        val model = "Pixel 7"
        val board = "panther"

        val fields = arrayOf("PRODUCT", "DEVICE", "MODEL", "BOARD")
        val spoofs = arrayOf(product, device, model, board)

        for (i in fields.indices) {
            logDebug("Spoof ${fields[i]} prop. Set it to: ${spoofs[i]}")
            Build::class.java.getDeclaredField(fields[i]).let { field ->
                field.isAccessible = true
                field.set(null, spoofs[i])
            }
        }
    }
}
