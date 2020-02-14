package com.dieam.reactnativepushnotification.modules

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.dieam.reactnativepushnotification.ReactNativePushNotificationPackage

class RNPushNotificationActionService: Service() {

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        ReactNativePushNotificationPackage.mPushNotificationActionHandler.handleActionPress(intent) {
            stopSelf()
        }
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return RNPushNotificationActionServiceBinder()
    }

    override fun onDestroy() {
        super.onDestroy()

        stopForeground(true)
        RNPushNotificationHelper(application).cancelAllScheduledNotifications()
    }

    inner class RNPushNotificationActionServiceBinder : Binder() {
        val service: RNPushNotificationActionService
            get() = this@RNPushNotificationActionService
    }
}
