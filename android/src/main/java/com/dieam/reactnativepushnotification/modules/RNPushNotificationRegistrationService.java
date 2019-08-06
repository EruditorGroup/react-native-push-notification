package com.dieam.reactnativepushnotification.modules;

import android.app.IntentService;
import android.content.Intent;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class RNPushNotificationRegistrationService extends IntentService {

    private static final String TAG = "RNPushNotification";

    public RNPushNotificationRegistrationService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(
                    new OnSuccessListener<InstanceIdResult>() {
                        @Override
                        public void onSuccess(InstanceIdResult result) {
                            if (result != null) {
                                final String token = result.getToken();
                                sendRegistrationToken(token);
                            } else {
                                sendError(new IllegalStateException("Instance id result is null."));
                            }
                        }
                    });
        } catch (Exception e) {
            sendError(e);
        }
    }

    private void sendRegistrationToken(String token) {
        Intent intent = new Intent(this.getPackageName() + ".RNPushNotificationRegisteredToken");
        intent.putExtra("token", token);
        sendBroadcast(intent);
    }

    private void sendError(Exception e) {
        Intent intent = new Intent(this.getPackageName() + ".RNPushNotificationError");

        Writer writer = new StringWriter();
        e.printStackTrace(new PrintWriter(writer));
        String s = writer.toString();

        intent.putExtra("exception", e.toString() + "\n--------\n" + s);
        intent.putExtra("tag", TAG);
        sendBroadcast(intent);
    }
}