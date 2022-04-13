package main.customer;

import main.Utils;
import sdk.interfaces.customer.DashboardScreen;
import sdk.vehicles.Vehicle;

import java.util.InputMismatchException;
import java.util.List;

import static main.Utils.SCANNER;

public class DashboardScreenImpl implements DashboardScreen {
    @Override
    public int getDashboardChoices(String content) {
        int choice = 0;
        displayContent(content);
        try {
            choice = SCANNER.nextInt();
        }
        catch (InputMismatchException inputMismatchException)
        {
            SCANNER.nextLine();
            choice = 0;
        }
        return choice;
    }

    @Override
    public int getManageProfileChoice(String content) {

        //String name, String email, String mobile, String address
        //String password

        int choice = 0;
        displayContent(content);
        try {
            choice = SCANNER.nextInt();
        }
        catch (InputMismatchException inputMismatchException)
        {
            SCANNER.nextLine();
            choice = 0;
        }
        return choice;
    }

    @Override
    public int getManageMyVehiclesChoice(String content) {
        int choice = 0;
        displayContent(content);
        try {
            choice = SCANNER.nextInt();
        }
        catch (InputMismatchException inputMismatchException)
        {
            SCANNER.nextLine();
        }
        return choice;
    }


    @Override
    public void displayContent(String content) {
        Utils.printIOToConsole(System.lineSeparator()+content);
    }

    @Override
    public int getVehicleChoice(List<Vehicle> vehicles) {
        int choice = 0;
        while (true) {
            displayVehicleChoices(vehicles);
            Utils.printIOToConsole(System.lineSeparator() +"Enter your Choice : ");
            try {
                choice = SCANNER.nextInt();
                if(choice>=0 && choice <= vehicles.size())
                    break;
                else {
                    Utils.printErrToConsole(System.lineSeparator()+"Invalid choice");
                }
            } catch (InputMismatchException inputMismatchException) {
                Utils.printErrToConsole("Your input is mismatched");
                SCANNER.nextLine();
            }
        }
        return choice;
    }

    public void displayVehicleChoices(List<Vehicle> vehicles)
    {
        Utils.printIOToConsole(System.lineSeparator()+"Press 0 to exit booking");
        int count = 1;
        for(Vehicle vehicle : vehicles)
        {
            Utils.printIOToConsole(System.lineSeparator()+"Press "+ count + " for "+ vehicle.getModelName() + "   "
                    + vehicle.getVehicleNumber());
            count++;
        }
    }
}
