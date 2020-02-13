package com.dieam.reactnativepushnotification.modules;

enum RNPushNotificationChannelConfig {

    INVITE("rn-push-notification-channel-invite", "Приглашения", ""),
    CHATS("rn-push-notification-channel-chats", "Сообщения", ""),
    OTHER("rn-push-notification-channel-transactional", "Прочие", "");

    private final String id;
    private final String channelName;
    private final String channelDescription;

    RNPushNotificationChannelConfig(String id, String channelName, String channelDescription) {
        this.id = id;
        this.channelName = channelName;
        this.channelDescription = channelDescription;
    }

    public String getId() {
        return id;
    }

    public String getChannelName() {
        return channelName;
    }

    public String getChannelDescription() {
        return channelDescription;
    }
}