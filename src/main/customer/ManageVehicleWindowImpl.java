package main.customer;

import main.Utils;
import sdk.interfaces.customer.ManageVehicleWindow;

import java.util.InputMismatchException;

import static main.Utils.SCANNER;

public class ManageVehicleWindowImpl implements ManageVehicleWindow {

    public void display0ToExit()
    {
        Utils.printIOToConsole(System.lineSeparator()+"Enter 0 to exit the process");
    }
    @Override
    public String getModelName() {
        String name;
        Utils.printIOToConsole(System.lineSeparator()+"Enter the vehicle model name : ");

        name = SCANNER.next();
        name += SCANNER.nextLine();
        if(name.equals("0"))
        {
            return null;
        }
        return name;
    }

    @Override
    public String getRegisterNumber() {
        String number;
        Utils.printIOToConsole(System.lineSeparator()+"Enter the vehicle's register number : ");
        //Utils.printIOToConsole(System.lineSeparator()+"Enter 0 to exit the process");
        number = SCANNER.next();
        number += SCANNER.nextLine();
        if(number.equals("0"))
        {
            return null;
        }
        return number;
    }

    @Override
    public String getManufacturerName() {
        String manufacturerName;
        Utils.printIOToConsole(System.lineSeparator()+"Enter the manufacturer name : ");
        //Utils.printIOToConsole(System.lineSeparator()+"Enter 0 to exit the process");
        manufacturerName = SCANNER.next();
        manufacturerName += SCANNER.nextLine();
        if(manufacturerName.equals("0"))
        {
            return null;
        }
        return manufacturerName;
    }

    @Override
    public String getVehicleColour() {
        String color;
        Utils.printIOToConsole(System.lineSeparator()+"Enter the vehicle color : ");
        //Utils.printIOToConsole(System.lineSeparator()+"Enter 0 to exit the process");
        color = SCANNER.next();
        color += SCANNER.nextLine();
        if(color.equals("0"))
        {
            return null;
        }
        return color;
    }

    public int getTwoWheelerChoice()
    {
        int choice = 0;
        Utils.printIOToConsole("\nPress 1 for two Wheeler.\nPress 2 for four wheeler.\nPress any other key to go back.\nEnter your choice : ");
        try {
            choice = SCANNER.nextInt();
        }
        catch (InputMismatchException inputMismatchException)
        {
            SCANNER.nextLine();
        }
        return choice;
    }

    // fuel type or cars.

}
