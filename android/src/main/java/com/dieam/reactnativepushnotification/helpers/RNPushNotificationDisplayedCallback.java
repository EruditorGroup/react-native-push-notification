package com.dieam.reactnativepushnotification.helpers;

import android.content.Context;
import android.content.Intent;

public interface RNPushNotificationDisplayedCallback {
    void onPushNotificationDisplayed(Context context, Intent intent);
}
