package sdk.operations;

import sdk.customer.Customer;
import sdk.modules.Notification;


public class ManageNotifications {
    private final Customer customer;

    public ManageNotifications(Customer customer ) {
        this.customer = customer;
    }

    public void clearNotifications()
    {
        customer.clearNotification();
        Notification.setCountToZero();
        // clear
    }

    public void addNotification(Notification notification)
    {
        /*SendMail sendMail = new SendMail();
        sendMail.sendMail(customer.getEmail(), notification.toString());

         */
        customer.addNotification(notification);
    }

}
