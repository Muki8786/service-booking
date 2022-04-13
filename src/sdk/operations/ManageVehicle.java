package sdk.operations;

import sdk.customer.Customer;
import sdk.interfaces.customer.ManageVehicleWindow;
import sdk.modules.Book;
import sdk.vehicles.Vehicle;

import java.util.ArrayList;
import java.util.List;


public class ManageVehicle {
    private final ManageVehicleWindow manageVehicleWindow;


    public ManageVehicle(ManageVehicleWindow manageVehicleWindow) {
        this.manageVehicleWindow = manageVehicleWindow;
    }

    public Vehicle getNewVehicle() //need to add an exit option while adding a new vehicle
    {
        manageVehicleWindow.display0ToExit();
        String modelName = getVehicleModelName();
        if(modelName == null)
        {
            return null;
        }

        String registerNumber =  getVehicleRegisterNumber();
        if(registerNumber == null)
        {
            return null;
        }
        String manufacturerName = getManufacturerName();
        if(manufacturerName == null)
        {
            return null;
        }
        String vehicleColour = getVehicleColour();
        if(vehicleColour == null)
        {
            return null;
        }
        boolean isTwoWheeler = true;
        int isTwoWheelerChoice = getTwoWheelerChoice();
        switch (isTwoWheelerChoice)
        {
            case 1 : isTwoWheeler = true;
            break;
            case 2 : isTwoWheeler = false;
            break;
            default:
            {
                return null;
            }
        }
        return new Vehicle(modelName,registerNumber,vehicleColour,manufacturerName,isTwoWheeler);
    }


    public String getVehicleModelName()
    {
        return manageVehicleWindow.getModelName();
    }

    public String getVehicleRegisterNumber()
    {
        return manageVehicleWindow.getRegisterNumber();
    }

    public String getManufacturerName()
    {
        return manageVehicleWindow.getManufacturerName();
    }

    public String getVehicleColour()
    {
        return manageVehicleWindow.getVehicleColour();
    }

    public List<Vehicle> getBookedVehicleList(Customer customer)
    {
        List<Vehicle> bookedVehicles = new ArrayList<>();
            for (Book book : customer.getBookingsList()) {
                if(!bookedVehicles.contains(book.getVehicle()))
                {
                    bookedVehicles.add(book.getVehicle());
                }
            }
            return bookedVehicles;
    }

    public int getTwoWheelerChoice()
    {
        return manageVehicleWindow.getTwoWheelerChoice();
    }

    public List<Vehicle> getAvailableVehicleList(Customer customer)
    {
        List<Vehicle> availableVehicles = new ArrayList<>();
        for (Vehicle vehicle : customer.getVehicleList()) {
            if (!getBookedVehicleList(customer).contains(vehicle)) {
                if(!availableVehicles.contains(vehicle))
                {
                    availableVehicles.add(vehicle);
                }
            }
        }
        return availableVehicles;
    }


}
