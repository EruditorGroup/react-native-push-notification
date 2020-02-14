package com.dieam.reactnativepushnotification.modules;

import android.content.Context;
import android.content.SharedPreferences;

public class RNPushNotificationSettings {

    private static final String RN_PUSH_NOTIFICATION_SETTINGS_PREFERENCE = "com.profibackoffice.reactnative.notifications";

    private final SharedPreferences sharedPreferences;

    public RNPushNotificationSettings(Context context) {
        sharedPreferences = context.getSharedPreferences(RN_PUSH_NOTIFICATION_SETTINGS_PREFERENCE, Context.MODE_PRIVATE);
    }

    public void setChannelSound(RNPushNotificationChannelConfig config, String soundUri) {
        sharedPreferences.edit()
                .putString(config.getId(), soundUri)
                .apply();
    }

    public String getChannelSound(RNPushNotificationChannelConfig config) {
        final String savedUri = sharedPreferences.getString(config.getId(), null);
        final String channelSound;
        if (savedUri != null) {
            channelSound = savedUri;
        } else {
            channelSound = config.getChannelSound();
        }
        return channelSound;
    }
}