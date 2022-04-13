package sdk.operations;

import sdk.customer.Customer;
import sdk.interfaces.NotificationsScreen;
import sdk.modules.Notification;

import java.util.List;

public class MyNotifications {
    private final NotificationsScreen notificationsScreen;
    private final Customer customer;

    public MyNotifications(Customer customer,NotificationsScreen notificationsScreen) {
        this.customer = customer;
        this.notificationsScreen = notificationsScreen;
    }

    public void manageNotifications()
    {
        displayNotifications();
        if(customer.getNotificationList().isEmpty())
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
                new ManageNotifications(customer).clearNotifications();
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
        List<Notification> notificationList = customer.getNotificationList();
        if(!notificationList.isEmpty()) {
            notificationsScreen.printNotifications(System.lineSeparator()+notificationList.toString());
        }
        else{
            notificationsScreen.printNotifications("No notifications");
        }
    }

}
