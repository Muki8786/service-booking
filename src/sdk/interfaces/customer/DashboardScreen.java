package sdk.interfaces.customer;

import sdk.vehicles.Vehicle;

import java.util.List;

public interface DashboardScreen {
    int getDashboardChoices(String content);
    int getManageProfileChoice(String content);
    int getManageMyVehiclesChoice(String content);
    void displayContent(String content);
    void displayVehicleChoices(List<Vehicle> vehicles);
    int getVehicleChoice(List<Vehicle> vehicles);
}
