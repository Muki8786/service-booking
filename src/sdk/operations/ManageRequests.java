package sdk.operations;

import sdk.customer.Customer;
import sdk.garage.Garage;
import sdk.garageOperations.ManageGarageNotifications;
import sdk.modules.Book;
import sdk.modules.BookingStatus;
import sdk.modules.Notification;
import sdk.modules.Request;

public class ManageRequests {

    public void addRequestInGarage(Request request)
    {
        Garage garage = request.getGarage();
        garage.addRequest(request);
    }
    public void addRequestInCustomer(Request request)
    {
        Customer customer = request.getCustomer();
        customer.addRequestInList(request);
    }

    public void removeRequestInGarage(Request request)
    {
        Garage garage = request.getGarage();
        garage.removeRequest(request);
    }

    public void removeRequestInCustomer(Request request)
    {
        Customer customer = request.getCustomer();
        customer.removeRequestFromList(request);
    }

    public void addRequestInGarageOldList(Request request)
    {
        Garage garage = request.getGarage();
        garage.addRequestInOldList(request);
    }

    public void addRequestInCustomerOldList(Request request)
    {
        Customer customer = request.getCustomer();
        customer.addRequestInOldList(request);
    }

    public void removeRequestInOldList(Request request)
    {
        Garage garage = request.getGarage();
        garage.removeRequestInOldList(request);
    }

    public void acceptGarageRequest(Request request)
    {
        if(request.isRedo()) {
            request.acceptRequest();
            //request.getCustomer().getBookingFromCompletedList(request.getBookingId()).setBookingStatus(BookingStatus.INITIATED);
            request.getRedoBook().setBookingStatus(BookingStatus.INITIATED);
            Request customerRequest = new Request("Redo booking request", request.getCustomer(), request.getBookingId(), request.getGarage(),request.getRedoBook(),false);
            customerRequest.setRedo(true);
            addRequestInCustomer(customerRequest);
            Notification notification = new Notification("Redo booking request","Your booking, ID - " +
                    request.getBookingId() +" can be redone . Open My requests.","Important");
            ManageNotifications manageNotifications = new ManageNotifications(request.getCustomer());
            manageNotifications.addNotification(notification);
            return;
        }

        if(request.isAccepted() == 1) {
                // Already accepted
            }
            else {
                request.acceptRequest();
                request.getCustomer().getBooking(request.getBookingId()).setBookingStatus(BookingStatus.ACCEPTED);
                request.getGarage().addCurrentBooking(request.getCustomer().getBooking(request.getBookingId()));// Add booking to current bookings in garage


                Notification notification = new Notification("BOOKING ACCEPTED","Your booking, ID - " +
                        request.getBookingId() +" is accepted by the "+ request.getGarage().getName()+ " Garage ","Important");
                ManageNotifications manageNotifications = new ManageNotifications(request.getCustomer());
                manageNotifications.addNotification(notification);

            }

    }



    public void rejectGarageRequest(Request request) // If the customer cancels the booking , The request has to removed
    {
        if(request.isRedo()) {
            request.rejectRequest();
            //request.getCustomer().getBookingFromCompletedList(request.getBookingId()).setBookingStatus(BookingStatus.REJECTED);
            request.getRedoBook().setBookingStatus(BookingStatus.REJECTED);
            return;
        }
        if(request.isAccepted() == -1)
        {
            // Already rejected
        }
        else {
            request.rejectRequest();
            request.getCustomer().getBooking(request.getBookingId()).setBookingStatus(BookingStatus.REJECTED);
            Notification notification = new Notification("BOOKING REJECTED","Your booking, ID - " +
                    request.getBookingId() +" is rejected by the "+ request.getGarage().getName()+ " Garage ","Important");
            ManageNotifications manageNotifications = new ManageNotifications(request.getCustomer());
            manageNotifications.addNotification(notification);
        }
    }

    public void acceptCustomerRequest(Request request)
    {
            request.acceptRequest();
            //request.getCustomer().getBookingFromCompletedList(request.getBookingId()).setBookingStatus(BookingStatus.ACCEPTED);

            request.getRedoBook().setBookingStatus(BookingStatus.ACCEPTED);

            request.getCustomer().addBookingToBookingList(request.getRedoBook());
            //request.getCustomer().removeBookingFromCompletedList(request.getCustomer().getBooking(request.getBookingId()));
            request.getGarage().addCurrentBooking(request.getRedoBook());// Add booking to current bookings in garage
            Notification notification = new Notification("REDO SERVICE ACCEPTED","You have accepted the  Redo service request ","Important");
            ManageNotifications manageNotifications = new ManageNotifications(request.getCustomer());
            manageNotifications.addNotification(notification);
            Notification notification1 = new Notification("REDO SERVICE ACCEPTED","The redo service for booking Id - "+request.getBookingId()+" was accepted by the customer. Please proceed with the service.","Important");
            ManageGarageNotifications manageGarageNotifications = new ManageGarageNotifications(request.getGarage());
            manageGarageNotifications.addNotification(notification1);
    }



    public void rejectCustomerRequest(Request request) // If the customer cancels the booking , The request has to be removed
    {
            request.rejectRequest();
            //request.getCustomer().getBookingFromCompletedList(request.getBookingId()).setBookingStatus(BookingStatus.REJECTED);
            request.getRedoBook().setBookingStatus(BookingStatus.REJECTED);
            Notification notification = new Notification("REDO BOOKING REJECTED","The booking, ID - " +
                    request.getBookingId() +" is rejected by the customer ","Important");
            ManageGarageNotifications manageGarageNotifications = new ManageGarageNotifications(request.getGarage());
            manageGarageNotifications.addNotification(notification);

    }

    public void removeRequestFromGarage(Book book)
    {
        Garage garage = book.getGarage();
        garage.removeRequestFromRequestList(book);
    }



}
