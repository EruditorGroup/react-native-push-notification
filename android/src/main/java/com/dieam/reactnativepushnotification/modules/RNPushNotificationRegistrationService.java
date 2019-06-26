package com.dieam.reactnativepushnotification.modules;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import androidx.annotation.NonNull;

import static com.dieam.reactnativepushnotification.modules.RNPushNotification.LOG_TAG;

public class RNPushNotificationRegistrationService extends IntentService {

    private static final String TAG = "RNPushNotification";

    public RNPushNotificationRegistrationService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(
                    new OnCompleteListener<InstanceIdResult>() {
                        @Override
                        public void onComplete(@NonNull Task<InstanceIdResult> task) {
                            if (!task.isSuccessful()) {
                                sendError(new IllegalStateException("Failed to load fcm token."));
                            }
                            final InstanceIdResult result = task.getResult();
                            if (result != null) {
                                final String token = task.getResult().getToken();
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