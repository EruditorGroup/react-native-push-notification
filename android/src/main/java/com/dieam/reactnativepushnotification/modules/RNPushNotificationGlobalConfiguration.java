package com.dieam.reactnativepushnotification.modules;

import java.util.concurrent.atomic.AtomicReference;

class RNPushNotificationGlobalConfiguration {

    private static RNPushNotificationGlobalConfiguration instance = null;
    private AtomicReference<Boolean> notificationsEnabledAtomicReference =
            new AtomicReference<Boolean>(true);


    private RNPushNotificationGlobalConfiguration() {
        // Empty
    }

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new RNPushNotificationGlobalConfiguration();
        }
    }

    public static RNPushNotificationGlobalConfiguration getInstance() {
        if (instance == null) createInstance();
        return instance;
    }

    public void setNotificationsEnabled(boolean enabled) {
        notificationsEnabledAtomicReference.set(enabled);
    }

    public boolean areNotificationsEnabled() {
        return notificationsEnabledAtomicReference.get();
    }
}
