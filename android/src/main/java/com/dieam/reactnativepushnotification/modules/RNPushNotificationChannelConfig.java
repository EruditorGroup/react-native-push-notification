package com.dieam.reactnativepushnotification.modules;

public enum RNPushNotificationChannelConfig {

    INVITE("rn-push-notification-channel-invite", "Подходящие заказы", "", ""),
    CHATS("rn-push-notification-channel-chats", "Новые сообщения", "", "quite-impressed.mp3"),
    TRANSACTIONS("rn-push-notification-channel-transactional", "Важное", "Пуш-уведомления о заказах, где вас выбрал клиент, отчётности", ""),
    OTHER("rn-push-notification-channel-other", "Другое", "Прочте пуш-уведомления", "");

    private final String id;
    private final String channelName;
    private final String channelDescription;
    private final String channelSound;

    RNPushNotificationChannelConfig(String id, String channelName, String channelDescription, String channelSound) {
        this.id = id;
        this.channelName = channelName;
        this.channelDescription = channelDescription;
        this.channelSound = channelSound;
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