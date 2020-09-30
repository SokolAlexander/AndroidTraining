package com.testproj

import android.content.Intent
import com.facebook.react.bridge.*
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter

class OpenSettingsModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {
    private val reactContext: ReactContext
    override fun getName(): String {
        /**
         * return the string name of the NativeModule which represents this class in JavaScript
         * In JS access this module through NativeModules.OpenSettings
         */
        return "OpenSettings"
    }

    @ReactMethod
    fun openAppNotificationsSettings() {
        val intent = Intent("android.settings.APP_NOTIFICATION_SETTINGS") // Settings.ACTION_APP_NOTIFICATION_SETTINGS
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        //for Android 5-7
        intent.putExtra("app_package", reactContext.packageName)
        intent.putExtra("app_uid", reactContext.applicationInfo.uid)
        // for Android 8 and above
        intent.putExtra("android.provider.extra.APP_PACKAGE", reactContext.packageName) // Settings.EXTRA_APP_PACKAGE
        if (intent.resolveActivity(reactContext.packageManager) != null) {
            reactContext.startActivity(intent)
        }
    }

    @ReactMethod
    fun someMethodWithCallback(errorCallback: Callback, successCallback: Callback) {
        try { // do stuff
            successCallback.invoke("success")
        } catch (e: Error) {
            errorCallback.invoke(e.message)
        }
    }

    @ReactMethod
    fun someMethodWithPromise(promise: Promise) {
        try { // do stuff
            promise.resolve("success")
        } catch (e: Error) { // promise.reject(e.getMessage());
            promise.reject("code", e.message)
        }
    }

    init {
        this.reactContext = reactContext
    }
}