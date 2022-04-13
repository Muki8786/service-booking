package sdk.operations;


import sdk.garageOperations.ManageGarageNotifications;
import sdk.modules.Book;
import sdk.modules.BookingStatus;
import sdk.modules.Notification;

public class Payment {

    public void makePayment(Book book)
    {
        book.setBillPaid(true);
        book.getBill().setBillPaid(true);
        book.setBookingStatus(BookingStatus.PAYMENT_RECEIVED);
        sendNotification(book);
        book.getGarage().addPaidBill(book.getBill());
        book.getGarage().removeBill(book.getBill());
    }

    private void sendNotification(Book book)
    {
        ManageNotifications manageNotifications = new ManageNotifications(book.getCustomer());
        ManageGarageNotifications manageGarageNotifications = new ManageGarageNotifications(book.getGarage());
        Notification notification = createNotification(book);
        manageNotifications.addNotification(notification);
        manageGarageNotifications.addNotification(notification);
        String content = "The total amount is -> "+book.getBill().getTotal();
        sendNotification(book,content);
    }

    private Notification createNotification(Book book)
    {
        return new Notification("Change in Booking Status","The booking status of Booking Id - "+ book.getId()+ " was changed","Important");
    }

    private Notification createNotification(Book book,String content)
    {
        return new Notification("Payment","The payment for the booking Id -> "+ book.getId()+ " is received"+System.lineSeparator()+content+System.lineSeparator()
                ,"Important");

    }

    private  void sendNotification(Book book ,String content)
    {
        ManageGarageNotifications manageGarageNotifications = new ManageGarageNotifications(book.getGarage());
        Notification notification = createNotification(book,content);
        manageGarageNotifications.addNotification(notification);
    }
}
