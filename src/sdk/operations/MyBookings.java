package sdk.operations;

import sdk.garage.Garage;
import sdk.interfaces.customer.BookingWindow;
import sdk.interfaces.customer.MyBookingsScreen;
import sdk.modules.*;
import sdk.customer.Customer;
import sdk.vehicles.Vehicle;
import sdk.garageOperations.ManageGarageNotifications;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyBookings {
    private final Customer customer;
    private final MyBookingsScreen myBookingsScreen;
    private final BookingWindow bookingWindow;
    private final List<Service> serviceList;
    private final List<Garage> garageList;
    private final ManageVehicle manageVehicle;
    private final Services services;

    MyBookings(Customer customer, MyBookingsScreen myBookingsScreen, BookingWindow bookingWindow, List<Service> serviceList,
               List<Garage> garageList, ManageVehicle manageVehicle,Services services) {
        this.customer = customer;
        this.myBookingsScreen = myBookingsScreen;
        this.bookingWindow = bookingWindow;
        this.serviceList = serviceList;
        this.garageList = garageList;
        this.manageVehicle = manageVehicle;
        this.services = services;
    }

    void manageMyBookings()
    {

        printMyBookings();
        if(customer.getBookingsList().size()==0)
        {
            return;
        }
        int bookingChoice =0;
        while (true)
        {
            bookingChoice = myBookingsScreen.getBookingChoice();
            if(bookingChoice == 0)
            {
                return;
            }
            else if(getBooking(bookingChoice) != null)
            {
                break;
            }
            else
            {
                myBookingsScreen.printInvalidInput();
            }
        }

        Book booking = getBooking(bookingChoice);
        if(booking == null) return;

        if( booking.getBill() != null && !booking.getBill().isBillPaid())
        {
            myBookingsScreen.printMyBookings(System.lineSeparator()+booking.getBill()+System.lineSeparator());
            if(myBookingsScreen.getPayNowChoice() == 1)
            {
                Payment payment = new Payment();
                payment.makePayment(booking);
                myBookingsScreen.printContent(System.lineSeparator()+"Payment completed");
                return;
            }
        }
        BookingStatus bookingStatus = booking.getBookingStatus();

        myBookingsScreen.printMyBookings(booking.toString());
        int editChoice = 0;
        while (true) {
            editChoice = myBookingsScreen.editBookingChoice();
            if(editChoice == 0)
            {
                return;
            }
            else if(editChoice == 1)
            {
                cancelBooking(booking);
                break;
            }
            else if(editChoice == 2)
            {
                editBooking(booking);
                break;
            }
            else
            {
                myBookingsScreen.printInvalidInput();
            }
        }

    }

     void printMyBookings()
    {
        if(customer.getBookingsList().size() == 0)
        {
            myBookingsScreen.printMyBookings("OOPS , Nothing to see here");
        }
        else
        {
            for(Book booking : customer.getBookingsList())
            {
            myBookingsScreen.printMyBookings(booking.toString());
            }
        }
    }

    private void cancelBooking(Book book)
    {
        BookingStatus bookingStatus = book.getBookingStatus();
        if(bookingStatus == BookingStatus.INITIATED || bookingStatus == BookingStatus.ACCEPTED)
        {
            customer.removeBookingFromBookingList(book);
            customer.addBookingToPastServices(book);
            book.getGarage().removeBookingFromList(book);
            book.getGarage().removeRequestFromRequestList(book);
            myBookingsScreen.printCancelSuccess(true);
            Notification notification = new Notification("Booking cancelled","Your booking "+book.getId()+" was cancelled by you,","Important");
            ManageNotifications manageNotifications = new ManageNotifications(customer);
            manageNotifications.addNotification(notification);
            Notification notification1 = new Notification("Request withdrawn","The booking "+book.getId()+ " Was cancelled by the customer","Important");
            ManageGarageNotifications manageGarageNotifications = new ManageGarageNotifications(book.getGarage());
            manageGarageNotifications.addNotification(notification1);
        }
        else
        {
            myBookingsScreen.printCancelSuccess(false);
        }

        // NOTE - garage side also (done)
    }


    private void editBooking(Book book)
    {
        while (true) {
            int choice = myBookingsScreen.getSingleEditOptionChoice();
            Booking booking = new Booking(customer, bookingWindow, serviceList, garageList, manageVehicle, services);
            switch (choice) {
                case 0:
                    return;
                case 1:
                    if (book.getBookingStatus() == BookingStatus.INITIATED && !book.isRedo()) {
                        editVehicle(book);
                    } else
                        displayCannotEdit();
                    break;
                case 2:
                    if ((book.getBookingStatus() == BookingStatus.INITIATED || book.getBookingStatus() == BookingStatus.ACCEPTED ||
                            book.getBookingStatus() == BookingStatus.PICKED_UP || book.getBookingStatus() == BookingStatus.REACHED_GARAGE ||
                            book.getBookingStatus() == BookingStatus.UNDER_SERVICE || book.getBookingStatus() == BookingStatus.SERVICE_COMPLETED) && !book.isRedo()) {

                        editServices(book);
                    } else
                        displayCannotEdit();
                    break;
                case 3:
                    if (book.getBookingStatus() == BookingStatus.INITIATED && !book.isRedo()) {
                        Garage newGarage = getGarage(book);
                        if (newGarage != null) {
                            //send req to garage
                            book.getGarage().removeRequestFromRequestList(book);
                            myBookingsScreen.printContent("\nThe request will be sent to " + newGarage.getName());
                            Request request = new Request("Booking Request", customer, book.getId(), newGarage, null, true);
                            ManageRequests manageRequests = new ManageRequests();
                            manageRequests.addRequestInGarage(request);
                            book.setGarage(newGarage);

                        }
                    } else displayCannotEdit();

                    break;
                case 4:
                    if (book.getBookingStatus() == BookingStatus.INITIATED) {
                        Date date = booking.getDate();
                        if (date.compareTo(book.getDate()) >= 0) {
                            book.setDate(date);
                            Notification notification = new Notification("Changes in a booking", "The Booking's (ID-" + book.getId() + ")" + " date has been edited.", "Important");
                            ManageGarageNotifications manageNotifications = new ManageGarageNotifications(book.getGarage());
                            manageNotifications.addNotification(notification);
                        } else displayCannotEdit();
                    } else
                        displayCannotEdit();

                    break;
                case 5:
                    if (book.getBookingStatus() == BookingStatus.INITIATED) {
                        boolean pickUp = booking.getPickUpChoice();
                        boolean oldPickUpChoice = book.isPickUp();
                        if(!oldPickUpChoice)
                        {
                            if(pickUp)
                            {
                                //getAddress -> if address 0 , display cannot edit
                                String address = getPickUpAddress();
                                if(address != null)
                                {
                                    book.setPickUpAddress(address);
                                    book.setPickUp(pickUp);
                                }
                            }else {
                                myBookingsScreen.printContent("\nIt is same as the existing choice");
                                break;
                            }
                        }
                        else {
                            if(!pickUp)
                            {
                                book.setPickUp(pickUp);
                                book.setPickUpAddress("-");
                            }else {
                                myBookingsScreen.printContent("\nIt is same as the existing choice");
                                break;
                            }

                        }

                        Notification notification = new Notification("Changes in a booking", "The Booking's (ID-" + book.getId() + ")" + " pick up choice has been edited.", "Important");
                        ManageGarageNotifications manageNotifications = new ManageGarageNotifications(book.getGarage());
                        manageNotifications.addNotification(notification);
                    } else
                        displayCannotEdit();
                    break;

                case 6:
                    if (book.getBookingStatus() != BookingStatus.LEFT_GARAGE || book.getBookingStatus() != BookingStatus.DROPPED || book.getBookingStatus() != BookingStatus.REACHED_DESTINATION) {
                        boolean drop = booking.getDropChoice();
                        boolean oldDropChoice = book.isDrop();
                        if(!oldDropChoice)
                        {
                            if(drop)
                            {
                                //getAddress -> if address 0 , display cannot edit
                                String address = getDropAddress();
                                if(address != null)
                                {
                                    book.setDropAddress(address);
                                    book.setDrop(drop);
                                }
                            }else {
                                myBookingsScreen.printContent("\nIt is same as the existing choice");
                                break;
                            }
                        }
                        else {
                            if(!drop)
                            {
                                book.setDrop(drop);
                                book.setDropAddress("-");
                            }else {
                                myBookingsScreen.printContent("\nIt is same as the existing choice");
                                break;
                            }

                        }
                        Notification notification = new Notification("Changes in a booking", "The Booking's (ID-" + book.getId() + ")" + " drop choice has been edited.", "Important");
                        ManageGarageNotifications manageNotifications = new ManageGarageNotifications(book.getGarage());
                        manageNotifications.addNotification(notification);
                    } else
                        displayCannotEdit();
                    break;
                case 7 ://change pickup address
                    if(book.isPickUp() && book.getBookingStatus() == BookingStatus.INITIATED)
                    {
                        String address = getPickUpAddress();
                        if(address != null)
                        {
                            book.setPickUpAddress(address);
                            Notification notification = new Notification("Changes in a booking", "The Booking's (ID-" + book.getId() + ")" + " pickUp address has been edited.", "Important");
                            ManageGarageNotifications manageNotifications = new ManageGarageNotifications(book.getGarage());
                            manageNotifications.addNotification(notification);
                        }
                    }
                    else myBookingsScreen.printContent("\nCannot edit pickUp address");
                    break;
                case 8://change drop address
                    if ( book.getBookingStatus() != BookingStatus.LEFT_GARAGE || book.getBookingStatus() != BookingStatus.DROPPED || book.getBookingStatus() != BookingStatus.REACHED_DESTINATION) {
                        if(book.isDrop())
                        {
                            String address = getDropAddress();
                            if(address != null)
                            {
                                book.setDropAddress(address);
                                Notification notification = new Notification("Changes in a booking", "The Booking's (ID-" + book.getId() + ")" + " drop address has been edited.", "Important");
                                ManageGarageNotifications manageNotifications = new ManageGarageNotifications(book.getGarage());
                                manageNotifications.addNotification(notification);
                            }
                        }else myBookingsScreen.printContent("\nCannot edit drop address");
                    }else
                        displayCannotEdit();
                        break;
                default: {
                    return;
                }

            }

        }
        }


    private Book getBooking(int bookingId)
    {
        for(Book book : customer.getBookingsList())
        {
            if(book.getId() == bookingId)
            {
                return book;
            }
        }
        return null;
    }

    private void editServices(Book book)
    {
        List<Service> serviceList = book.getServices();
        List<Service> totalServiceList = new ArrayList<>();
        List<Service> addableServices = new ArrayList<>();
        Vehicle vehicle = book.getVehicle();

        if(vehicle.isTwoWheeler())
        {
            totalServiceList = services.getBikeServiceList();
        }
        else {
            totalServiceList = services.getCarServiceList();
        }

        for(Service service : totalServiceList)
        {
            if(!serviceList.contains(service))
            {
                addableServices.add(service);
            }
        }

        for(Service service : book.getServices())
        {
            addableServices.remove(service);
        }


        if(book.getBookingStatus() == BookingStatus.UNDER_SERVICE)
        {
            myBookingsScreen.printContent("\nYou can add service(s). You cannot remove them.");
            if (book.getServices().size() == addableServices.size()) {
                myBookingsScreen.printContent("\nAll the services are added");
                displayCannotEdit();
                return;
            } else {
                int choice = myBookingsScreen.getServiceChoice(addableServices) - 1;
                if (choice == -1) {
                    return;
                } else {
                    Service service = addableServices.get(choice);
                    book.addServiceToList(service);
                    addableServices.remove(service);
                    myBookingsScreen.printContent("\nService added");
                    Notification notification = new Notification("Changes in a booking", "In the Booking (ID-" + book.getId() + ")" + " service(s) has/have been added.", "Important");
                    ManageGarageNotifications manageNotifications = new ManageGarageNotifications(book.getGarage());
                    manageNotifications.addNotification(notification);
                }
                return;
            }
        }
        else if(book.getBookingStatus() == BookingStatus.SERVICE_COMPLETED)
        {
            myBookingsScreen.printContent("\nYou can add service(s). You cannot remove them.");
            if (book.getServices().size() == addableServices.size()) {
                myBookingsScreen.printContent("\nAll the services are added");
                displayCannotEdit();
                return;
            } else {
                int choice = myBookingsScreen.getServiceChoice(addableServices) - 1;
                if (choice == -1) {
                    return;
                } else {
                    Service service = addableServices.get(choice);
                    book.addServiceToList(service);
                    addableServices.remove(service);
                    myBookingsScreen.printContent("\nService added");
                    book.setBookingStatus(BookingStatus.UNDER_SERVICE);
                    Notification notification1 = new Notification("Change in Booking Status", "In the Booking (ID-" + book.getId() + ")" + ", The booking status is changed. ", "Important");
                    ManageGarageNotifications manageNotifications1 = new ManageGarageNotifications(book.getGarage());
                    manageNotifications1.addNotification(notification1);
                    Notification notification = new Notification("Changes in a booking", "In the Booking (ID-" + book.getId() + ")" + " service(s) has/have been added.", "Important");
                    ManageGarageNotifications manageNotifications = new ManageGarageNotifications(book.getGarage());
                    manageNotifications.addNotification(notification);
                }
                return;
            }
        }



        while (true) {
            int editChoice = myBookingsScreen.getEditServiceChoice("Press 1 to add a service.\nPress 2 to remove a service.\nPress any other key to go back.\nEnter your choice : ");

            switch (editChoice) {
                case 1: // add services

                    if (book.getServices().size() == totalServiceList.size()) {
                        myBookingsScreen.printContent("\nAll the services are added");
                        displayCannotEdit();
                        break;
                    } else {
                        int choice = myBookingsScreen.getServiceChoice(addableServices) - 1;
                        if (choice == -1) {
                            break;
                        } else {
                            Service service = addableServices.get(choice);
                            book.addServiceToList(service);
                            addableServices.remove(service);
                            myBookingsScreen.printContent("\nService added");
                            Notification notification = new Notification("Changes in a booking", "The Booking's (ID-" + book.getId() + ")" + " service(s) has/have been edited.", "Important");
                            ManageGarageNotifications manageNotifications = new ManageGarageNotifications(book.getGarage());
                            manageNotifications.addNotification(notification);
                        }
                    }

                    break;
                case 2: // remove services
                    if (book.getServices().size() <= 1) {
                        myBookingsScreen.printContent("\nThere should be at least one service chosen");
                        displayCannotEdit();
                        break;
                    } else {
                        int choice = myBookingsScreen.getServiceChoice(book.getServices()) - 1;
                        if (choice == -1) {
                            break;
                        } else {
                            Service service = book.getServices().get(choice);
                            addableServices.add(service);
                            book.removeServiceFromList(service);
                            myBookingsScreen.printContent("\nService removed");
                            Notification notification = new Notification("Changes in a booking", "The Booking's (ID-" + book.getId() + ")" + " service(s) has/have been edited.", "Important");
                            ManageGarageNotifications manageNotifications = new ManageGarageNotifications(book.getGarage());
                            manageNotifications.addNotification(notification);
                        }
                    }
                    break;
                default:{
                    return;
                }
            }
        }

    }

    private void editVehicle(Book book)
    {
        Vehicle bookedVehicle = book.getVehicle();
        boolean isTwoWheelerVehicle = bookedVehicle.isTwoWheeler();
        List<Vehicle> vehicleList = new ArrayList<>();
        List<Vehicle> allVehicles = book.getCustomer().getVehicleList();
        if(isTwoWheelerVehicle)
        {
            for(Vehicle vehicle : allVehicles)
            {
                if(vehicle.isTwoWheeler())
                {
                    vehicleList.add(vehicle);
                }
            }
            vehicleList.remove(book.getVehicle());
        }
        else {
            for(Vehicle vehicle : allVehicles)
            {
                if(!vehicle.isTwoWheeler())
                {
                    vehicleList.add(vehicle);
                }
            }
            vehicleList.remove(book.getVehicle());
        }

        if (vehicleList.isEmpty()) {
            if (bookingWindow.addVehicleToProceedChoice() != 1) {
                return;
            } else {
                //Utils.printIOToConsole("\nChoose the vehicle");
                if(isTwoWheelerVehicle)
                {
                    myBookingsScreen.printContent("\nAdd a two wheeler vehicle");
                }else {
                    myBookingsScreen.printContent("\nAdd a four wheeler vehicle ");
                }

                Vehicle newVehicle = manageVehicle.getNewVehicle();
                if (newVehicle != null && (newVehicle.isTwoWheeler() == isTwoWheelerVehicle))
                {
                    customer.addVehicle(newVehicle);
                    vehicleList.add(newVehicle);
                }
                else {
                    myBookingsScreen.printErrorContent("\nVehicle cannot be added");
                    return ;
                }
            }
        }

        List<Vehicle> availableVehicles = vehicleList;


        bookingWindow.displayVehicleChoices(availableVehicles);
        int index = bookingWindow.getVehicleChoice(availableVehicles) - 1;
        if (index == -1) {
            return;
        }
        else {
             book.setVehicle(availableVehicles.get(index));
            myBookingsScreen.printContent("\nVehicle changed");
            Notification notification = new Notification("Changes in a booking","The Booking's (ID-"+book.getId()+")" + " vehicle has been edited.","Important");
            ManageGarageNotifications manageNotifications = new ManageGarageNotifications(book.getGarage());
            manageNotifications.addNotification(notification);
        }

    }



    /*private List<Vehicle> getAvailableVehicles() {
        List<Vehicle> availableVehicles = new ArrayList<>();
        List<Vehicle> bookedVehicles = new ArrayList<>();
        while (true) {
            for (Book book : customer.getBookingsList()) {
                if(!bookedVehicles.contains(book.getVehicle()))
                {
                    bookedVehicles.add(book.getVehicle());
                }
            }
            for (Vehicle vehicle : customer.getVehicleList()) {
                if (!bookedVehicles.contains(vehicle)) {
                    if(!availableVehicles.contains(vehicle))
                    {
                        availableVehicles.add(vehicle);
                    }
                }
            }
            if (availableVehicles.isEmpty()) {
                if (bookingWindow.addVehicleToProceedChoice() != 1) {
                    return null;
                } else {
                    Vehicle newVehicle = manageVehicle.getNewVehicle();
                    if(newVehicle != null)customer.addVehicle(newVehicle);
                }
            } else return availableVehicles;
        }
    }
    */

    Garage getGarage(Book book) {
        List<Garage> availableGarages = new ArrayList<>();
        for(Garage garage : garageList)
        {
            if(!garage.equals(book.getGarage()))
            {
                availableGarages.add(garage);
            }
        }

        int index = bookingWindow.getGarageChoice(availableGarages) - 1;
        if (index == -1) {
            return null;
        } else {
            return availableGarages.get(index);
        }


    }


    private void displayCannotEdit()
    {
        bookingWindow.printErrorContent(System.lineSeparator()+"Your is booking is processed. You cant edit this field now");
    }

    private String getPickUpAddress()
    {
        String address = bookingWindow.getPickUpAddress();
        if(address.equals("0"))
        {
            return null;
        }
        return address;
    }

    private String getDropAddress()
    {
        String address = bookingWindow.getDropAddress();
        if(address.equals("0"))
        {
            return null;
        }
        return address;
    }

}
