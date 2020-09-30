package com.testproj;

import android.app.Activity;
import android.content.Intent;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;


public class OpenSettingsModule extends ReactContextBaseJavaModule {
  private ReactContext reactContext;

  public OpenSettingsModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    /**
     * return the string name of the NativeModule which represents this class in JavaScript
     * In JS access this module through NativeModules.OpenSettings
     */
    return "OpenSettings";
  }

  @ReactMethod
  public void triggerEvent() {
      sendEvent();
  }

  public void sendEvent() {
    WritableMap params = Arguments.createMap();
    params.putString("someKey", "someValue");
    reactContext
            .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
            .emit("nativeEvent", params);
  }

  @ReactMethod
  public void openAppNotificationsSettings() {
    Intent intent = new Intent("android.settings.APP_NOTIFICATION_SETTINGS"); // Settings.ACTION_APP_NOTIFICATION_SETTINGS
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

    //for Android 5-7
    intent.putExtra("app_package", reactContext.getPackageName());
    intent.putExtra("app_uid", reactContext.getApplicationInfo().uid);

    // for Android 8 and above
    intent.putExtra("android.provider.extra.APP_PACKAGE", reactContext.getPackageName()); // Settings.EXTRA_APP_PACKAGE

    if (intent.resolveActivity(reactContext.getPackageManager()) != null) {
        reactContext.startActivity(intent);
    }
  }

  @ReactMethod
  public void someMethodWithCallback(Callback errorCallback, Callback successCallback) {
    try {
      // do stuff
      successCallback.invoke("success");
    } catch (Error e) {
      errorCallback.invoke(e.getMessage());
    }
  }

  @ReactMethod
  public void someMethodWithPromise(Promise promise) {
    try {
      // do stuff
      promise.resolve("success");
    } catch (Error e) {
      promise.reject(e.getMessage());
    }
  }
}