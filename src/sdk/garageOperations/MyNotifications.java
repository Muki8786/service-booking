package sdk.garageOperations;


import sdk.garage.Garage;
import sdk.interfaces.NotificationsScreen;
import sdk.modules.Notification;

import java.util.List;

public class MyNotifications {
    private final NotificationsScreen notificationsScreen;
    private final Garage garage;

    public MyNotifications(Garage garage,NotificationsScreen notificationsScreen) {
        this.garage = garage;
        this.notificationsScreen = notificationsScreen;
    }

    public void manageNotifications()
    {
        displayNotifications();
        if(garage.getNotificationList().isEmpty())
        {
            return;
        }
        String content = System.lineSeparator()+"Enter 0 to exit"+System.lineSeparator()+"Enter 1 to Clear notifications"
                +System.lineSeparator()+"Enter your Choice : ";
        int choice = notificationsScreen.getManageNotificationsChoice(content);
        while(true)
        {
            if(choice == 0)
            {
                return;
            }
            else if(choice == 1)
            {
                new ManageGarageNotifications(garage).clearNotifications();
                break;
            }
            else
            {
                choice = notificationsScreen.getManageNotificationsChoice(content);
            }
        }

    }

    public void displayNotifications()
    {
        List<Notification> notificationList = garage.getNotificationList();
        if(!notificationList.isEmpty()) {
            notificationsScreen.printNotifications(notificationList.toString());
        }
        else{
            notificationsScreen.printNotifications("No notifications");
        }
    }
}
