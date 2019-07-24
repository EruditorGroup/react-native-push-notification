package com.dieam.reactnativepushnotification.helpers;

import android.content.Context;

import javax.annotation.Nullable;

@Deprecated
public interface RemotePushNotificationHandlerEventListenerProvider {
    @Nullable
    RemotePushNotificationHandlerEventListener provide(Context context);
}
