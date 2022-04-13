package sdk.interfaces.customer;

import sdk.garage.Garage;
import sdk.modules.Service;
import sdk.vehicles.Vehicle;


import java.util.Date;
import java.util.List;

public interface BookingWindow {
    int getVehicleChoice(List<Vehicle> vehicles);
    int getGarageChoice(List<Garage> garageList);
    int getServiceChoice(List<Service> serviceList);
    Date getDate();
    int getPickUpChoice();
    int getDropChoice();
    void printBookingSuccess(boolean success);
    int addVehicleToProceedChoice();
    int addVehicleChoice();
    void displayVehicleChoices(List<Vehicle> vehicles);
    int addServicesChoice();
    int getAddVehicleConsent();
    int getAddServiceConsent();
    String getDropAddress();
    String getPickUpAddress();
    void printContent(String content);
    void printErrorContent(String content);

}
