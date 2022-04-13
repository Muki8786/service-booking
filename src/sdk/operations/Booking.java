package sdk.operations;

import sdk.modules.Book;
import sdk.modules.Request;
import sdk.modules.Service;
import sdk.customer.Customer;
import sdk.garage.Garage;
import sdk.interfaces.customer.BookingWindow;
import sdk.vehicles.Vehicle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Booking {
    private final Customer customer;
    private final BookingWindow bookingWindow;
    private final List<Service> serviceList;
    private final List<Garage> garageList;
    private final ManageVehicle manageVehicle;
    private final Services services;

    public Booking(Customer customer, BookingWindow bookingWindow, List<Service> serviceList, List<Garage> garageList, ManageVehicle manageVehicle, Services services) {
        this.customer = customer;
        this.bookingWindow = bookingWindow;
        this.serviceList = serviceList;
        this.garageList = garageList;
        this.manageVehicle = manageVehicle;
        this.services = services;
    }

     boolean bookService() {

        Vehicle vehicle = getVehicle();
        if (vehicle == null) {
            bookingWindow.printBookingSuccess(false);
            return false;
        }

        List<Service> serviceListToBeBooked = getAvailableServices(vehicle);
        if (serviceListToBeBooked.isEmpty()) {
            bookingWindow.printBookingSuccess(false);
            return false;
        }

        //Utils.printIOToConsole("\nChoose the Garage");
        Garage garage = getGarage();
        if (garage == null) {
            bookingWindow.printBookingSuccess(false);
            return false;
        }

        Date date = getDate();
        if (date == null) {
            bookingWindow.printBookingSuccess(false);
            return false;
        }
        boolean pickUpChoice = getPickUpChoice();
        String pickUpAddress = null;
        if(pickUpChoice)
        {
            pickUpAddress = bookingWindow.getPickUpAddress();
            if(pickUpAddress.equals("0"))
            {
                pickUpAddress = null;
                pickUpChoice = false;
            }
        }
        boolean dropChoice = getDropChoice();
        String dropAddress = null;
        if(dropChoice)
        {
            dropAddress = bookingWindow.getDropAddress();
            if(dropAddress.equals("0"))
            {
                dropAddress = null;
                dropChoice = false;
            }
        }
        Book book = new Book(customer, vehicle, serviceListToBeBooked, garage, date, pickUpChoice, dropChoice,pickUpAddress,dropAddress);
        customer.addBookingToBookingList(book);
        bookingWindow.printBookingSuccess(true);
        Request request = new Request("Booking Request", customer, book.getId(), garage,null,false);
        ManageRequests manageRequests = new ManageRequests();
        manageRequests.addRequestInGarage(request);
        return true;
    }

    private Vehicle getVehicle()
    {

        List<Vehicle> vehicleList = customer.getVehicleList();

        if (vehicleList.isEmpty()) {
            if (bookingWindow.addVehicleToProceedChoice() != 1) {
                return null;
            } else {
                //Utils.printIOToConsole("\nChoose the vehicle");
                Vehicle newVehicle = manageVehicle.getNewVehicle();
                if (newVehicle != null)
                {
                    customer.addVehicle(newVehicle);
                }
                else return null;
            }
        }

        //bookingWindow.displayVehicleChoices(vehicleList);
        for(Vehicle vehicle : customer.getVehicleList())
        {
            bookingWindow.printContent(System.lineSeparator()+vehicle.toString());
        }
        addVehicle();
        List<Vehicle> availableVehicles = getAvailableVehicles();
        if(availableVehicles == null)
            return null;
        int index = bookingWindow.getVehicleChoice(availableVehicles) - 1;
        if (index == -1) {
            return null;
        } else {
            return availableVehicles.get(index);
        }

    }



    private Garage getGarage() {

        int index = bookingWindow.getGarageChoice(garageList) - 1;
        if (index == -1) {
            return null;
        } else {
            return garageList.get(index);
        }


    }

    Date getDate()
    {
        Date date = bookingWindow.getDate();
        if (date == null) {
            return null;
        } else {
            return date;
        }
    }

    boolean getPickUpChoice() {
        int choice = bookingWindow.getPickUpChoice();
        boolean pickUp;
        if (choice == 1) {
            pickUp = true;
        } else {
            pickUp = false;
        }
        return pickUp;
    }

    boolean getDropChoice() {
        int choice = bookingWindow.getDropChoice();
        boolean drop;
        if (choice == 1) {
            drop = true;
        } else {
            drop = false;
        }
        return drop;
    }


    private void addVehicle() {
        if (bookingWindow.getAddVehicleConsent() == 1) {
            Vehicle vehicle = manageVehicle.getNewVehicle();
            if (vehicle != null) {
                customer.addVehicle(vehicle);
            }
        }
    }

    private List<Vehicle> getAvailableVehicles() {
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

    private List<Service> getAvailableServices(Vehicle vehicle) {
        List<Service> serviceListToBeBooked = new ArrayList<>();

        List<Service> servicesToChooseFrom = new ArrayList<>();
        if(vehicle.isTwoWheeler())
        {
            servicesToChooseFrom = services.getBikeServiceList();
        }
        else {
            servicesToChooseFrom = services.getCarServiceList();
        }

        List<Service> availableServices = new ArrayList<>();

        for(Service service : servicesToChooseFrom)
        {
            availableServices.add(service);
        }

        while (true) {
            int choice = bookingWindow.getServiceChoice(availableServices) -1;
            if(choice == -1)
            {
                break;
            }else {
                Service service = availableServices.get(choice);
                serviceListToBeBooked.add(service);
                availableServices.remove(service);
                if(availableServices.isEmpty())
                {
                    //Utils.printIOToConsole("\nAll services are chosen");
                    break;
                }
                int addChoice = bookingWindow.getAddServiceConsent();
                if(addChoice != 1)
                {
                  break;
                }
            }
        }


        return serviceListToBeBooked;
    }
}
