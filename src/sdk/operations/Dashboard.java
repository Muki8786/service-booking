package sdk.operations;

import sdk.customer.Customer;
import sdk.garage.Garage;
import sdk.garageOperations.ManageGarageNotifications;
import sdk.interfaces.SignUpScreen;
import sdk.interfaces.customer.BookingWindow;
import sdk.interfaces.customer.DashboardScreen;
import sdk.modules.Book;
import sdk.modules.Notification;
import sdk.vehicles.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class Dashboard {
    //profile
    //my vehicles
    //past bookings
    // bills

    private Customer customer;
    private final DashboardScreen dashboardScreen;
    private final SignUpScreen signUpScreen;
    private final BookingWindow bookingWindow;
    private final ManageVehicle manageVehicle;

    public Dashboard(Customer customer, DashboardScreen dashboardScreen, SignUpScreen signUpScreen, BookingWindow bookingWindow, ManageVehicle manageVehicle)
    {
        this.customer = customer;
        this.dashboardScreen = dashboardScreen;
        this.signUpScreen = signUpScreen;
        this.bookingWindow = bookingWindow;
        this.manageVehicle = manageVehicle;
    }

    public void manageDashboard()
    {
        while (true) {
            String content = "\nPress 1 to view and manage your profile.\nPress 2 to view and manage My vehicles.\nPress 3 to view the past services.\nPress any other key to go back.\nEnter your choice : ";
            int choice = dashboardScreen.getDashboardChoices(content);
            switch (choice) {
                case 1:
                    manageProfile();
                    break;
                case 2:
                    manageMyVehicles();
                    break;
                case 3:
                    viewPastServices();
                    break;
                default: {
                    return;
                }
            }
        }
    }

    private void manageProfile()
    {
        while (true) {
            String content = customer.getName() + "\n" + customer.getEmail() + "\n" + customer.getMobile() + "\n" + customer.getAddress();
            dashboardScreen.displayContent(content);
            int choice = dashboardScreen.getManageProfileChoice("\nPress 1 to change your name.\nPress 2 to change your email.\nPress 3 to change your mobile number.\nPress 4 to change your address.\nPress 5 to change your password.\nPress any other key to go back.\nEnter your choice : ");
            switch (choice) {
                case 1:
                    signUpScreen.display0ToExit();
                    String existingName = customer.getName();
                    String name = signUpScreen.getName();
                    if (name.equals("0")) {
                        break;
                    }
                    if (checkSimilarString(existingName, name)) {
                        dashboardScreen.displayContent("\nThe existing name is same as the new one.");
                        break;
                    } else {
                        customer.setName(name);
                        dashboardScreen.displayContent("\nName was changed");
                        for(Book book : customer.getBookingsList()){
                            Garage garage = book.getGarage();
                            Notification notification = new Notification("Customer detail changed","The name of the customer in the booking id :"+book.getId()+" was changed to "+name,"Important");
                            ManageGarageNotifications manageGarageNotifications = new ManageGarageNotifications(garage);
                            manageGarageNotifications.addNotification(notification);
                        }

                    }
                    break;
                case 2:
                    signUpScreen.display0ToExit();
                    String existingEmail = customer.getEmail();
                    String email = signUpScreen.getEmail();
                    if (email.equals("0")) {
                        break;
                    }
                    if (checkSimilarString(email, existingEmail)) {
                        dashboardScreen.displayContent("\nThe existing email id is same as the new one.");
                        break;
                    } else {
                        customer.setEmail(email);
                        dashboardScreen.displayContent("\nemail was changed");
                        for(Book book : customer.getBookingsList()){
                            Garage garage = book.getGarage();
                            Notification notification = new Notification("Customer detail changed","The email of the customer in the booking id :"+book.getId()+" was changed to "+email,"Important");
                            ManageGarageNotifications manageGarageNotifications = new ManageGarageNotifications(garage);
                            manageGarageNotifications.addNotification(notification);
                        }
                    }
                    break;
                case 3:
                    signUpScreen.display0ToExit();
                    String existingMobile = customer.getMobile();
                    String mobile = signUpScreen.getMobile();
                    if (mobile.equals("0")) {
                        break;
                    }
                    if (checkSimilarString(mobile, existingMobile)) {
                        dashboardScreen.displayContent("\nThe existing mobile number is same as the new one.");
                        break;
                    } else {
                        customer.setMobile(mobile);
                        dashboardScreen.displayContent("\nMobile number was changed");
                        for(Book book : customer.getBookingsList()){
                            Garage garage = book.getGarage();
                            Notification notification = new Notification("Customer detail changed","The mobile number of the customer in the booking id :"+book.getId()+" was changed to "+mobile,"Important");
                            ManageGarageNotifications manageGarageNotifications = new ManageGarageNotifications(garage);
                            manageGarageNotifications.addNotification(notification);
                        }
                    }
                    break;
                case 4:
                    signUpScreen.display0ToExit();
                    String existingAddress = customer.getAddress();
                    String address = signUpScreen.getAddress();
                    if (address.equals("0")) {
                        break;
                    }
                    if (checkSimilarString(address, existingAddress)) {
                        dashboardScreen.displayContent("\nThe existing address is same as the new one.");
                        break;
                    } else {
                        customer.setAddress(address);
                        dashboardScreen.displayContent("\nAddress was changed");
                        for(Book book : customer.getBookingsList()){
                            Garage garage = book.getGarage();
                            Notification notification = new Notification("Customer detail changed","The address of the customer in the booking id :"+book.getId()+" was changed to "+address,"Important");
                            ManageGarageNotifications manageGarageNotifications = new ManageGarageNotifications(garage);
                            manageGarageNotifications.addNotification(notification);
                        }
                    }
                    break;
                case 5:
                    signUpScreen.display0ToExit();
                    String existingPassword = customer.getPassword();
                    String password = signUpScreen.getPassword();
                    if (password.equals("0")) {
                        break;
                    }
                    dashboardScreen.displayContent("\nRe-enter the password.");
                    String reEnterPassword = signUpScreen.getPassword();
                    if (!password.equals(reEnterPassword)) {
                        if (reEnterPassword.equals("0")) {
                            break;
                        }
                        dashboardScreen.displayContent("\nThe passwords does not match.");
                        break;
                    }
                    if (checkSimilarString(password, existingPassword)) {
                        dashboardScreen.displayContent("\nThe existing email id is same as the new one.");
                        break;
                    } else {
                        customer.setPassword(password);
                        dashboardScreen.displayContent("\nPassword changed successfully");
                    }
                    break;
                default:{
                    return;
                }
            }
        }

    }

    private void manageMyVehicles() {
        while (true) {
            int choice = dashboardScreen.getManageMyVehiclesChoice("\nPress 1 to view Your vehicle(s).\nPress 2 to add a vehicle.\nPress 3 to remove a vehicle.\nNOTE-You cant remove a vehicle if that vehicle is booked.\nPress any other key to go back.\nEnter your choice : ");
            //List<Vehicle> bookedVehicles = manageVehicle.getBookedVehicleList(customer);

            switch (choice) {
                case 1: //Display
                    List<Vehicle> vehicleList = customer.getVehicleList();
                    if (vehicleList.isEmpty()) {
                        dashboardScreen.displayContent("\nNo vehicles");
                    } else {
                        dashboardScreen.displayContent(vehicleList.toString());
                    }
                    break;

                case 2:// add
                    Vehicle vehicle = manageVehicle.getNewVehicle();
                    if (vehicle == null) {
                        dashboardScreen.displayContent("\n vehicle was not added");
                    } else {
                        customer.addVehicle(vehicle);
                        dashboardScreen.displayContent("\n Vehicle was  added");
                    }
                    break;
                case 3: // remove from available list
                    List<Vehicle> availableVehicles = manageVehicle.getAvailableVehicleList(customer);
                    if (!availableVehicles.isEmpty()) {
                        Vehicle vehicleToBeRemoved;
                        dashboardScreen.displayContent("\n Remove the vehicle");
                        //dashboardScreen.displayVehicleChoices(availableVehicles);
                        int vehicleChoice = dashboardScreen.getVehicleChoice(availableVehicles) - 1;
                        if (vehicleChoice == -1) {
                            vehicleToBeRemoved = null;
                            break;
                        } else {
                            vehicleToBeRemoved = availableVehicles.get(vehicleChoice);
                            customer.removeVehicle(vehicleToBeRemoved);
                            dashboardScreen.displayContent("\nVehicle was removed");
                        }
                    } else {
                        dashboardScreen.displayContent("\nNo available vehicle in the list to be removed.");
                    }
                    break;
                default:{
                    return;
                }
            }

        }
    }

    private void viewPastServices() // view Bills too
    {
        List<Book> pastBookings = customer.getPastBookingsList();
        if(!pastBookings.isEmpty())
        {
            dashboardScreen.displayContent(pastBookings.toString());
        }
        else dashboardScreen.displayContent("\nNo past Services");
    }

    private boolean checkSimilarString(String string1,String string2)
    {
        return string1.equals(string2);
    }




}
