package com.dieam.reactnativepushnotification.helpers;

import android.content.Context;
import android.content.Intent;

public interface RemotePushNotificationHandlerEventListener {
    void onPushNotificationHandled(Context context, Intent intent);
}
