package sdk.interfaces;

import sdk.modules.Notification;

public interface NotificationsScreen {
    void printNotifications(String notifications);
    int getManageNotificationsChoice(String content);

}
