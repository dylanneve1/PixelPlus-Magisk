package com.dylanneve1.pixelplus

@Suppress("unused")
object EntryPoint {
    @JvmStatic
    fun init() {
        try {
            logDebug("Entry point: Initializing SafetyNet patches")
            BuildHooks.init()
        } catch (e: Throwable) {
            // Throwing an exception would require the JNI code to handle exceptions, so just catch
            // everything here.
            logDebug("Error in entry point", e)
        }
    }
}
