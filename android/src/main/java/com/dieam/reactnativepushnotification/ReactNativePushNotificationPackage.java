package com.dieam.reactnativepushnotification;

import com.dieam.reactnativepushnotification.helpers.RNPushNotificationDisplayedCallback;
import com.dieam.reactnativepushnotification.helpers.RNSilentPushNotificationReceivedCallback;
import com.dieam.reactnativepushnotification.modules.RNPushNotification;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.Collections;
import java.util.List;

public class ReactNativePushNotificationPackage implements ReactPackage {

    private RNPushNotificationDisplayedCallback mRNPushNotificationDisplayedCallback;
    private RNSilentPushNotificationReceivedCallback mRNSilentPushNotificationReceivedCallback;

    public ReactNativePushNotificationPackage(
            RNPushNotificationDisplayedCallback rnPushNotificationDisplayedCallback,
            RNSilentPushNotificationReceivedCallback rnSilentPushNotificationReceivedCallback
    ) {
        mRNPushNotificationDisplayedCallback = rnPushNotificationDisplayedCallback;
        mRNSilentPushNotificationReceivedCallback = rnSilentPushNotificationReceivedCallback;
    }

    @Override
    public List<NativeModule> createNativeModules(
            ReactApplicationContext reactContext) {
        return Collections.<NativeModule>singletonList(new RNPushNotification(
                reactContext,
                mRNPushNotificationDisplayedCallback,
                mRNSilentPushNotificationReceivedCallback
        ));
    }

    public List<Class<? extends JavaScriptModule>> createJSModules() {
        return Collections.emptyList();
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }
}


