package com.dieam.reactnativepushnotification.modules;

import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.core.content.res.ResourcesCompat;

class RNPushNotificationConfig {
    private static final String KEY_CHANNEL_NAME = "com.dieam.reactnativepushnotification.notification_channel_name";
    private static final String KEY_CHANNEL_DESCRIPTION = "com.dieam.reactnativepushnotification.notification_channel_description";
    private static final String KEY_NOTIFICATION_COLOR = "com.dieam.reactnativepushnotification.notification_color";

    private static Bundle metadata;
    private Context context;

    public RNPushNotificationConfig(Context context) {
        this.context = context;
        if (metadata == null) {
            try {
                ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
                metadata = applicationInfo.metaData;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                Log.e(RNPushNotification.LOG_TAG, "Error reading application meta, falling back to defaults");
                metadata = new Bundle();
            }
        }
        try {
            final NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            configChannels(notificationManager);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getChannelName() {
        try {
            return metadata.getString(KEY_CHANNEL_NAME);
        } catch (Exception e) {
            Log.w(RNPushNotification.LOG_TAG, "Unable to find " + KEY_CHANNEL_NAME + " in manifest. Falling back to default");
        }
        // Default
        return "rn-push-notification-channel";
    }

    public String getChannelDescription() {
        try {
            return metadata.getString(KEY_CHANNEL_DESCRIPTION);
        } catch (Exception e) {
            Log.w(RNPushNotification.LOG_TAG, "Unable to find " + KEY_CHANNEL_DESCRIPTION + " in manifest. Falling back to default");
        }
        // Default
        return "";
    }

    public int getNotificationColor() {
        try {
            int resourceId = metadata.getInt(KEY_NOTIFICATION_COLOR);
            return ResourcesCompat.getColor(context.getResources(), resourceId, null);
        } catch (Exception e) {
            Log.w(RNPushNotification.LOG_TAG, "Unable to find " + KEY_NOTIFICATION_COLOR + " in manifest. Falling back to default");
        }
        // Default
        return -1;
    }

    private void configChannels(NotificationManager manager) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O)
            return;
        if (manager == null)
            return;

        Bundle bundle = new Bundle();

        int importance = NotificationManager.IMPORTANCE_HIGH;
        final String importanceString = bundle.getString("importance");

        if (importanceString != null) {
            switch (importanceString.toLowerCase()) {
                case "default":
                    importance = NotificationManager.IMPORTANCE_DEFAULT;
                    break;
                case "max":
                    importance = NotificationManager.IMPORTANCE_MAX;
                    break;
                case "high":
                    importance = NotificationManager.IMPORTANCE_HIGH;
                    break;
                case "low":
                    importance = NotificationManager.IMPORTANCE_LOW;
                    break;
                case "min":
                    importance = NotificationManager.IMPORTANCE_MIN;
                    break;
                case "none":
                    importance = NotificationManager.IMPORTANCE_NONE;
                    break;
                case "unspecified":
                    importance = NotificationManager.IMPORTANCE_UNSPECIFIED;
                    break;
                default:
                    importance = NotificationManager.IMPORTANCE_HIGH;
            }
        }
        final RNPushNotificationChannelConfig[] configs = RNPushNotificationChannelConfig.values();
        for (final RNPushNotificationChannelConfig config : configs) {
            NotificationChannel channel = new NotificationChannel(config.getId(), config.getChannelName(), importance);
            final NotificationChannelGroup group = new NotificationChannelGroup(config.getId(), config.getChannelName());

            channel.setDescription(config.getChannelDescription());
            channel.enableLights(true);
            channel.enableVibration(true);

            manager.createNotificationChannelGroup(group);
            manager.createNotificationChannel(channel);
        }
    }
}