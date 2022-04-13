package sdk.garageOperations;


import sdk.garage.Garage;
import sdk.interfaces.garage.CurrentServicesScreen;
import sdk.interfaces.garage.ManageBillsScreen;
import sdk.modules.*;
import sdk.operations.ManageNotifications;
import sdk.operations.Payment;

import java.util.HashMap;
import java.util.List;

public class CurrentServices {
    private final Garage garage;
    private final CurrentServicesScreen currentServicesScreen;
    private final ManageBillsScreen manageBillsScreen;


    public CurrentServices(Garage garage, CurrentServicesScreen currentServicesScreen, ManageBillsScreen manageBillsScreen) {
        this.garage = garage;
        this.currentServicesScreen = currentServicesScreen;
        this.manageBillsScreen = manageBillsScreen;
    }

    public void manageCurrentServices()
    {
        List<Book> currentBookingList = garage.getCurrentBookings();
        if(currentBookingList.isEmpty())
        {
            currentServicesScreen.printErrorContent(System.lineSeparator()+"Oops, No services at the moment");
            return;
        }
        Book book = getBookingFromList(currentBookingList);
        if(book == null)
        {
            return;
        }
        changeTheBookingStatus(book);
        // Complete the booking -> Bills the total amount
    }

    private Book getBookingFromList(List<Book> currentBookingList)
    {
        currentServicesScreen.displayCurrentServices(currentBookingList);
        int choice = currentServicesScreen.getBookNowChoice();
        if (choice > 0 && choice <= currentBookingList.size()) {
            for (Book book : currentBookingList) {
                if(book.getId() == choice)
                {
                    return book;
                }
            }
        }
        return null;
    }


    private void changeTheBookingStatus(Book book)
    {
        /*String content = System.lineSeparator()+"To change the booking Status "+System.lineSeparator()+"Press 1 to change to Picked Up"+
                System.lineSeparator()+"Press 2 to change to Reached garage"+System.lineSeparator()+"Press 3 to change to Under Service"+
                System.lineSeparator()+"Press 4 to change to service completed"+System.lineSeparator()+"Press 5 to change to Bill generated"+
                System.lineSeparator()+"Press 6 to change to Payment completed"+System.lineSeparator()+"Press 7 to change to left garage"+
                System.lineSeparator()+"Press 8 to change to Reached destination"+System.lineSeparator()+"Press 9 to change to Dropped"+
                System.lineSeparator()+"Press 10 to change to Completed"+System.lineSeparator()+"Press 11 to change to Cancelled"+System.lineSeparator()+"Press any other key to go back"+
                System.lineSeparator()+"Enter Your choice : ";
         */
        //int choice = currentServicesScreen.getChangeBookingStatusChoice(content);

        BookingStatus bookingStatus = book.getBookingStatus();

        HashMap<BookingStatus, List<BookingStatus>> bookingStatusHashMap = new sdk.Utils().getBookingStatusMap();
        List<BookingStatus> changeableBookingStatus = bookingStatusHashMap.get(bookingStatus);
        if(!book.isPickUp())
        {
            changeableBookingStatus.remove(BookingStatus.PICKED_UP);
        }
        if(!book.isDrop())
        {
            changeableBookingStatus.remove(BookingStatus.LEFT_GARAGE);
            changeableBookingStatus.remove(BookingStatus.REACHED_DESTINATION);
            changeableBookingStatus.remove(BookingStatus.DROPPED);
        }
        currentServicesScreen.printContent(bookingStatus.name());
        if(bookingStatus == BookingStatus.COMPLETED)
        {
            return;
        }
        if(book.isRedo() && book.getBookingStatus() == BookingStatus.INITIATED)
        {
            return;
        }
        if(book.isRedo() && book.getBookingStatus() == BookingStatus.SERVICE_COMPLETED)
        {
            changeableBookingStatus.remove(BookingStatus.BILL_GENERATED);
        }
        else if(!book.isRedo() && book.getBookingStatus() == BookingStatus.SERVICE_COMPLETED)
        {
            changeableBookingStatus.remove(BookingStatus.COMPLETED);
        }
        int choice =0;
        choice = currentServicesScreen.getBookingStatusChoice(changeableBookingStatus) -1;
        if(choice == -1)
        {
            return;
        }
        else {
            bookingStatus = changeableBookingStatus.get(choice);
        }


        switch (bookingStatus)
        {
            case PICKED_UP:
                book.setBookingStatus(BookingStatus.PICKED_UP);
                currentServicesScreen.displayStatusChanged();
                sendNotification(book);
            break;
            case REACHED_GARAGE:
                book.setBookingStatus(BookingStatus.REACHED_GARAGE);
                currentServicesScreen.displayStatusChanged();
                sendNotification(book);

                break;
            case UNDER_SERVICE:
                book.setBookingStatus(BookingStatus.UNDER_SERVICE);
                currentServicesScreen.displayStatusChanged();
                sendNotification(book);
                break;
            case SERVICE_COMPLETED:
                book.setBookingStatus(BookingStatus.SERVICE_COMPLETED);
                currentServicesScreen.displayStatusChanged();
                sendNotification(book);

                /*if(!book.isRedo()) {
                    //Billing
                    Bills bills = new Bills(manageBillsScreen, garage);
                    Bill bill = bills.generateBill(book);
                    sendNotification(book, bill.toString());
                    book.setBookingStatus(BookingStatus.BILL_GENERATED);
                    currentServicesScreen.displayStatusChanged();
                    sendNotification(book);
                }

                 */
                break;
            case BILL_GENERATED :
                book.setBookingStatus(BookingStatus.BILL_GENERATED);
                Bills bills = new Bills(manageBillsScreen, garage);
                Bill bill = bills.generateBill(book);
                sendNotification(book, bill.toString());
                currentServicesScreen.displayStatusChanged();
                sendNotification(book);
                break;
            case PAYMENT_RECEIVED:
                book.setBookingStatus(BookingStatus.PAYMENT_RECEIVED);
                Payment payment = new Payment();
                payment.makePayment(book);
                currentServicesScreen.displayStatusChanged();
                sendNotification(book);
                break;
            case LEFT_GARAGE:
                book.setBookingStatus(BookingStatus.LEFT_GARAGE);
                currentServicesScreen.displayStatusChanged();
                sendNotification(book);
                break;
            case REACHED_DESTINATION:
                book.setBookingStatus(BookingStatus.REACHED_DESTINATION);
                currentServicesScreen.displayStatusChanged();
                sendNotification(book);
                break;
            case DROPPED:
                book.setBookingStatus(BookingStatus.DROPPED);
                currentServicesScreen.displayStatusChanged();
                sendNotification(book);
                break;
            case COMPLETED:

                book.setBookingStatus(BookingStatus.COMPLETED);// Prepare Bill instances with the book details and add it in the List<Bills> in the garage and customer
                currentServicesScreen.displayStatusChanged();//Notify the customer and the Garage about the status changed
                sendNotification(book);
                manageCompletedBooking(book);

                break;
            case CANCELLED:
                book.setBookingStatus(BookingStatus.CANCELLED);
                currentServicesScreen.displayStatusChanged();
                sendNotification(book);
                break;
            default:{
                currentServicesScreen.printErrorContent(System.lineSeparator()+"Invalid Choice");
            }
        }

    }

    private Notification createNotification(Book book)
    {
        return new Notification("Change in Booking Status","The booking status of Booking Id - "+ book.getId()+ " was changed","Important");
    }

    private Notification createNotification(Book book,String content)
    {
        return new Notification("Bill for the booking","The bill details of Booking Id - "+ book.getId()+ " is attached below"+System.lineSeparator()+content+System.lineSeparator()
                +"Please make the payment using UPI id","Important");

    }

    private  void sendNotification(Book book ,String content)
    {
        ManageNotifications manageNotifications = new ManageNotifications(book.getCustomer());
        Notification notification = createNotification(book,content);
        manageNotifications.addNotification(notification);
    }

    private void sendNotification(Book book)
    {
        ManageNotifications manageNotifications = new ManageNotifications(book.getCustomer());
        ManageGarageNotifications manageGarageNotifications = new ManageGarageNotifications(book.getGarage());
        Notification notification = createNotification(book);
        manageNotifications.addNotification(notification);
        manageGarageNotifications.addNotification(notification);
    }

    private void manageCompletedBooking(Book book)
    {
        book.getCustomer().addBookingToCompletedList(book);
        book.getCustomer().removeBookingFromBookingList(book);
        book.getGarage().deleteCurrentBooking(book);
        book.getGarage().addCompletedBooking(book);
        List<Service> serviceList = book.getServices();
        addCountInServices(serviceList);
        book.getGarage().freeEmployee(book);
        if(book.isRedo())
        {
            book.setRedoCompleted(true);
        }

    }

    private void addCountInServices(List<Service> serviceList)
    {
        for (Service service : serviceList)
        {
            service.addCountOfAvailed();
        }
    }

}
