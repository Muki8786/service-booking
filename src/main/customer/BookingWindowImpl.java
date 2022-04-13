package main.customer;

import main.Utils;
import sdk.modules.Service;
import sdk.garage.Garage;
import sdk.interfaces.customer.BookingWindow;
import sdk.vehicles.Vehicle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;

import static main.Utils.SCANNER;

public class BookingWindowImpl implements BookingWindow {

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
        Utils.printIOToConsole("\nChoose the vehicle");
        Utils.printIOToConsole(System.lineSeparator()+"Press 0 to exit booking");
        int count = 1;
        for(Vehicle vehicle : vehicles)
        {
            Utils.printIOToConsole(System.lineSeparator()+"Press "+ count + " for "+ vehicle.getModelName() + "   "
            + vehicle.getVehicleNumber());
            count++;
        }
    }

    @Override
    public int getServiceChoice(List<Service> serviceList ) {
        int choice = 0;
        while (true) {
            displayServiceChoices(serviceList);
            Utils.printIOToConsole(System.lineSeparator() +"Enter your Choice : ");
            try {
                choice = SCANNER.nextInt();
                if(choice>=0 && choice <= serviceList.size())
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

    void displayServiceChoices(List<Service> services)
    {
        Utils.printIOToConsole("\nChoose the service(s)");
        Utils.printIOToConsole(System.lineSeparator()+"Press 0 to exit booking");
        int count = 1;
        for(Service service : services)
        {
            Utils.printIOToConsole(System.lineSeparator()+"Press "+ count + " for "+ service);
            count++;
        }
    }

    public int getAddServiceConsent()
    {
        Utils.printIOToConsole("\nIf you would like to add other service , Press 1 to add . \nPress any other key to skip.\nEnter your choice : ");
        int choice = 0;
        try {
            choice = SCANNER.nextInt();
        }catch (InputMismatchException inputMismatchException)
        {
            SCANNER.nextLine();
        }
        return choice;
    }


    @Override
    public int getGarageChoice(List<Garage> garageList) {
        int choice = 0;
        while (true) {
            displayGarageChoices(garageList);
            Utils.printIOToConsole(System.lineSeparator() +"Enter your Choice : ");
            try {
                choice = SCANNER.nextInt();
                if(choice>=0 && choice <= garageList.size())
                    break;
                else {
                    Utils.printErrToConsole(System.lineSeparator()+"Invalid choice.Try again.");
                }
            } catch (InputMismatchException inputMismatchException) {
                Utils.printErrToConsole("Your input is mismatched");
                SCANNER.nextLine();
            }
        }
        return choice;
    }

    void displayGarageChoices(List<Garage> garageList)
    {
        Utils.printIOToConsole(System.lineSeparator()+"Press 0 to exit booking");
        int count = 1;
        Utils.printIOToConsole("\nChoose the Garage");
        for(Garage garage: garageList)
        {
            Utils.printIOToConsole(System.lineSeparator()+"Press "+ count + " for "+ garage.getName()+"\t"+ garage.getRating().getAverageRating()+"/5");
            count++;
        }
    }



    @Override
    public Date getDate() {
        Date date;
            while (true) {
                Utils.printIOToConsole(System.lineSeparator() + "Enter the Date and time of the service booking");
                Utils.printIOToConsole("The Date and time should be in the format of dd-MMM-yyyy HH:mm:ss " +
                        "[Example : 12-mar-2022 12:12:12]");
                SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
                String sDate = SCANNER.next();
                sDate += SCANNER.nextLine();
                if(sDate.equals("0"))
                {
                    return null;
                }
                else {
                    try {
                        date = format.parse(sDate);
                        break;
                    } catch (ParseException e) {
                        Utils.printErrToConsole("The Date Should be in the given format");
                        SCANNER.nextLine();
                    }
                }
            }
            return date;
    }

    @Override
    public int getPickUpChoice() {
        int choice;

        Utils.printIOToConsole(System.lineSeparator() + "Press 1 to avail pickup facility ");
        Utils.printIOToConsole(System.lineSeparator() + "Press any other key to not avail pick up");
        Utils.printIOToConsole(System.lineSeparator() +"Enter your Choice : ");
        try {
            choice = SCANNER.nextInt();
        } catch (InputMismatchException inputMismatchException) {
            choice = 0;
            SCANNER.nextLine();
        }

        return choice;
    }

    @Override
    public int getDropChoice() {
        int choice;

        Utils.printIOToConsole(System.lineSeparator() + "Press 1 to avail drop facility ");
        Utils.printIOToConsole(System.lineSeparator() + "Press any other number to not avail drop");
        Utils.printIOToConsole(System.lineSeparator() +"Enter your Choice : ");
        try {
            choice = SCANNER.nextInt();
        } catch (InputMismatchException inputMismatchException) {
            choice = 0;
            SCANNER.nextLine();
        }

        return choice;
    }

    @Override
    public void printBookingSuccess(boolean success) {
        if(success)
        {
            Utils.printIOToConsole(System.lineSeparator()+"Your booking is completed.");
        }
        else
        {
            Utils.printErrToConsole(System.lineSeparator()+"Your booking failed.");
        }
    }

    @Override
    public int addVehicleToProceedChoice() {
        int choice = 0;
        Utils.printIOToConsole(System.lineSeparator()+"To continue with the booking you need to add a vehicle");
        Utils.printIOToConsole(System.lineSeparator()+"Press 1 to add a vehicle");
        Utils.printIOToConsole(System.lineSeparator()+"Press any other key to go back");

        try {
            choice = SCANNER.nextInt();
        }
        catch (InputMismatchException inputMismatchException)
        {
            choice= 0;
            SCANNER.nextLine();
        }
        return choice;
    }

    public int addVehicleChoice()
    {
        int choice = 0;

        Utils.printIOToConsole(System.lineSeparator()+"Press 1 to add a vehicle");
        Utils.printIOToConsole(System.lineSeparator()+"Press any other key to continue");

        try {
            choice = SCANNER.nextInt();
        }
        catch (InputMismatchException inputMismatchException)
        {
            choice= 0;
            SCANNER.nextLine();
        }
        return choice;
    }

    public int addServicesChoice()
    {
        int choice = 0;

        Utils.printIOToConsole(System.lineSeparator()+"Press 1 to add service");
        Utils.printIOToConsole(System.lineSeparator()+"Press any other key to continue");
        try {
            choice = SCANNER.nextInt();
        }
        catch (InputMismatchException inputMismatchException)
        {
            choice= 0;
            SCANNER.nextLine();
        }
        return choice;
    }

    @Override
    public int getAddVehicleConsent() {
        int choice = 0;
        Utils.printIOToConsole(System.lineSeparator()+"Press 1 to add vehicle now ");
        Utils.printIOToConsole(System.lineSeparator()+"Press any other key to skip ");
        Utils.printIOToConsole(System.lineSeparator()+"Enter your choice : ");

        try
        {
            choice = SCANNER.nextInt();
        }
        catch (InputMismatchException inputMismatchException)
        {
            choice = 0; //Can be used to validate later
            SCANNER.nextLine();
        }
        return choice;
    }

    @Override
    public String getPickUpAddress() {
        String address;

        Utils.printIOToConsole(System.lineSeparator()+"Enter the pickUp address - Separated by commas : ");
        Utils.printIOToConsole(System.lineSeparator()+"Enter 0 to Change the pickup choice");
        address = SCANNER.next();
        address += SCANNER.nextLine();

        return address;
    }

    @Override
    public String getDropAddress() {
        String address;

        Utils.printIOToConsole(System.lineSeparator()+"Enter the drop address - Separated by commas : ");
        Utils.printIOToConsole(System.lineSeparator()+"Enter 0 to Change the drop choice");
        address = SCANNER.next();
        address += SCANNER.nextLine();

        return address;
    }

    public void printContent(String content)
    {
        Utils.printIOToConsole(content);
    }

    public void printErrorContent(String content)
    {
        Utils.printErrToConsole(content);
    }

}
