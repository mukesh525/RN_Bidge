package com.activitydemo.modules;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.activitydemo.activity.Payment;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

public class ActivityStarterModule extends ReactContextBaseJavaModule implements ActivityEventListener {

    private static final int IMAGE_PICKER_REQUEST = 467082;

    private static final String E_ACTIVITY_DOES_NOT_EXIST = "E_ACTIVITY_DOES_NOT_EXIST";
    private static final String E_PICKER_CANCELLED = "E_PICKER_CANCELLED";
    private static final String E_FAILED_TO_SHOW_PICKER = "E_FAILED_TO_SHOW_PICKER";
    private static final String E_NO_IMAGE_DATA_FOUND = "E_NO_IMAGE_DATA_FOUND";
    private Promise mPickerPromise;


    public ActivityStarterModule(ReactApplicationContext reactContext) {
        super(reactContext);
        reactContext.addActivityEventListener(this);

    }


    private void onConnected(ReactApplicationContext reactContext) {
        // Create map for params
        WritableMap payload = Arguments.createMap();
        // Put data to map
        payload.putString("sessionId", "Muk");
        // Get EventEmitter from context and send event thanks to it
        sendEvent(reactContext,"onSessionConnect",payload);
    }




    private void sendEvent(ReactContext reactContext, String eventName, @Nullable WritableMap params) {
        if (reactContext.hasActiveCatalystInstance()) {
            reactContext
                    .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                    .emit(eventName, params);
        }
    }

    @Override
    public String getName() {
        return "ActivityStarter";
    }

    @ReactMethod
    void navigateToExample(final Promise promise) {
        WritableMap payload = Arguments.createMap();
        payload.putString("sessionId", "Muk");
        sendEvent(this.getReactApplicationContext(),"onSessionConnect",payload);
        mPickerPromise = promise;
        ReactApplicationContext context = getReactApplicationContext();
        Intent intent = new Intent(context, Payment.class);
        Activity currentActivity = getCurrentActivity();
        currentActivity.startActivityForResult(intent, IMAGE_PICKER_REQUEST);
    }

    @Override
    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
        if (requestCode == IMAGE_PICKER_REQUEST) {
            if (mPickerPromise != null) {
                if (resultCode == Activity.RESULT_CANCELED) {
                    mPickerPromise.reject(E_PICKER_CANCELLED, "Image picker was cancelled");
                } else if (resultCode == Activity.RESULT_OK) {
                    // Uri uri = data.getData();
                    String result = data.getStringExtra("result");

                    if (result == null) {
                        mPickerPromise.reject(E_NO_IMAGE_DATA_FOUND, "No image data found");
                    } else {
                        mPickerPromise.resolve(result.toString());
                    }
                }

                mPickerPromise = null;
            }
        }
    }

    @Override
    public void onNewIntent(Intent intent) {

    }
}
