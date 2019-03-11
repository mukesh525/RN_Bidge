package com.activitydemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.activitydemo.R;
import com.facebook.react.ReactActivity;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

public class Payment extends ReactActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
    }

    @Override
    public void onBackPressed() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result","Mukesh Main");
        WritableMap payload = Arguments.createMap();
        // Put data to map
        payload.putString("sessionId", "OnBAckEvent");
        sendEvent(getReactNativeHost().getReactInstanceManager().getCurrentReactContext(),"onSessionConnect",payload);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
        //super.onBackPressed();

    }

    private void sendEvent(ReactContext reactContext, String eventName, @Nullable WritableMap params) {
        if (reactContext.hasActiveCatalystInstance()) {
            reactContext
                    .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                    .emit(eventName, params);
        }
    }
}
