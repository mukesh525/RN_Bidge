package com.activitydemo;

import com.activitydemo.modules.ActivityStarterModule;
import com.activitydemo.modules.ImageNewModule;
import com.activitydemo.modules.ImagePickerModule;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ActivityStarterReactPackage implements ReactPackage {
    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {

        List<NativeModule> modules = new ArrayList<>();
        modules.add(new ActivityStarterModule(reactContext));
        modules.add(new ImagePickerModule(reactContext));
        modules.add(new ImageNewModule(reactContext));
        return modules;
    }

    // UPDATE: This method was deprecated in 0.47
    // @Override
    // public List<Class<? extends JavaScriptModule>> createJSModules() {
    //     return Collections.emptyList();
    // }



    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }
}
