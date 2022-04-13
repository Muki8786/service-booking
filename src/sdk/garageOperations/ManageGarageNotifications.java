package sdk.garageOperations;


import sdk.garage.Garage;
import sdk.modules.Notification;

public class ManageGarageNotifications {
    private final Garage garage;

    public ManageGarageNotifications(Garage garage ) {
        this.garage = garage;
    }

    public void clearNotifications()
    {
        garage.clearNotification();
        Notification.setCountToZero();
        // clear
    }

    public void addNotification(Notification notification)
    {
        /*SendMail sendMail = new SendMail();
        sendMail.sendMail(garage.getEmail(),notification.toString());

         */
        garage.addNotification(notification);
    }
}
