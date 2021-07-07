package com.dieam.reactnativepushnotification.helpers;

import android.content.Context;
import android.content.Intent;

public interface RNSilentPushNotificationReceivedCallback {
    void onSilentPushReceived(Context context, Intent intent);
}
