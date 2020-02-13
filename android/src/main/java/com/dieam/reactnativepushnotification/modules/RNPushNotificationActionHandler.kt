package com.dieam.reactnativepushnotification.modules

import android.content.Intent

interface RNPushNotificationActionHandler {

    fun getSupportedActionTypes(): List<String>

    fun handleActionPress(intent: Intent)
}
